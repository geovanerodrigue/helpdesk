package com.helpdesk.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.helpdesk.ExceptionHelpDesk;
import com.helpdesk.model.Pessoa;
import com.helpdesk.model.Usuario;
import com.helpdesk.model.dto.EmpresaDTO;
import com.helpdesk.model.dto.ObjetoMsgGeral;
import com.helpdesk.model.dto.PessoaDTO;
import com.helpdesk.repository.EmpresaRepository;
import com.helpdesk.repository.PessoaRepository;
import com.helpdesk.repository.UsuarioRepository;
import com.helpdesk.service.PessoaUserService;
import com.helpdesk.service.ServiceSendEmail;






@Controller
public class PessoaController {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private PessoaUserService pessoaUserService;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ServiceSendEmail serviceSendEmail;

	@ResponseBody
	@PostMapping(value = "**/salvarPessoa")
	public ResponseEntity<Pessoa> salvarPessoa(@RequestBody @Valid Pessoa pessoa) throws ExceptionHelpDesk {

		if(pessoa ==  null) {
			throw new ExceptionHelpDesk("Pessoa fisica não pode ser NULL");
		}
		
		if(pessoa.getEmpresa() ==  null) {
			throw new ExceptionHelpDesk("Empresa não pode ser NULL");
		}
		
		if(pessoa.getEmail() ==  null) {
			throw new ExceptionHelpDesk("Email não pode ser NULL");
		}
		
		if(pessoa.getNome() ==  null) {
			throw new ExceptionHelpDesk("Email não pode ser NULL");
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
	
	
	@ResponseBody
	@PostMapping(value = "**/recuperarSenha")
	public ResponseEntity<ObjetoMsgGeral> recuperarAcesso(@RequestBody String login) throws Exception {
		
	  Usuario usuario = usuarioRepository.findUserByLogin(login);
	  
	  if(usuario == null) {
		  return new ResponseEntity<ObjetoMsgGeral>(new ObjetoMsgGeral("Usuário não encontrado"), HttpStatus.OK);
	  }
	  
	  String senha = UUID.randomUUID().toString();
	  
	  senha = senha.substring(0, 6);
	  
	  String senhaCriptografada = new BCryptPasswordEncoder().encode(senha);
	  
	  usuarioRepository.updateSenhaUser(senhaCriptografada, login);
	  
	  StringBuilder msgEmail = new StringBuilder();
	  
	  msgEmail.append("<b>Sua nova senha: <b/>").append(senha);
	  
	  serviceSendEmail.enviarEmailHtml("Sua nova senha: ", msgEmail.toString(), usuario.getPessoa().getEmail());
	  
	  return new ResponseEntity<ObjetoMsgGeral>(new ObjetoMsgGeral("Senha enviada para o seu e-mail"), HttpStatus.OK);
		
	}


}
