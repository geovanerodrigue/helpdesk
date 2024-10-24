package com.helpdesk.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.helpdesk.ExceptionHelpDesk;
import com.helpdesk.model.Pessoa;
import com.helpdesk.model.dto.EmpresaDTO;
import com.helpdesk.model.dto.PessoaDTO;
import com.helpdesk.repository.EmpresaRepository;
import com.helpdesk.repository.PessoaRepository;
import com.helpdesk.service.PessoaUserService;






@Controller
public class PessoaController {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private PessoaUserService pessoaUserService;
	
	@Autowired
	private EmpresaRepository empresaRepository;

	@ResponseBody
	@PostMapping(value = "**/salvarPessoa")
	public ResponseEntity<Pessoa> salvarPessoa(@RequestBody @Valid Pessoa pessoa) throws ExceptionHelpDesk {

		if(pessoa ==  null) {
			throw new ExceptionHelpDesk("Pessoa fisica não pode sere NULL");
		}

		pessoa = pessoaUserService.salvarPessoa(pessoa);

		return new ResponseEntity<>(pessoa, HttpStatus.OK);

	}
	
	@ResponseBody
	@GetMapping(value = "**/consultaNome/{nome}")
    public ResponseEntity<List<PessoaDTO>> consultaNomePessoa(@PathVariable("nome") String nome){
		
		List<PessoaDTO> dtos = new ArrayList<>();

		List<Pessoa> pessoa = pessoaRepository.pesquisaPorNome(nome.trim().toUpperCase());

		for(Pessoa pessoas : pessoa) {

			PessoaDTO pessoaDTO = new PessoaDTO();
			pessoaDTO.setId(pessoas.getId());
			pessoaDTO.setNome(pessoas.getNome());
			pessoaDTO.setEmail(pessoas.getEmail());
			pessoaDTO.setTelefone(pessoas.getTelefone());
			pessoaDTO.setTipoPessoa(pessoas.getTipoPessoa());
			
            if (pessoas.getEmpresa() != null) {
                EmpresaDTO empresaDTO = new EmpresaDTO();
                empresaDTO.setId(pessoas.getEmpresa().getId());
                empresaDTO.setNome(pessoas.getEmpresa().getNome());
                pessoaDTO.setEmpresa(empresaDTO);
            }
		    //pessoaDTO.setEmpresa(pessoas.getEmpresa().getId());

			dtos.add(pessoaDTO);

		}

		return new ResponseEntity<>(dtos, HttpStatus.OK);
    }


}
