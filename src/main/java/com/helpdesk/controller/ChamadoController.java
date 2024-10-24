package com.helpdesk.controller;

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
import com.helpdesk.model.Chamado;
import com.helpdesk.repository.ChamadoRepository;

@Controller
public class ChamadoController {
	
	
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	
	@ResponseBody
	@PostMapping(value = "**/salvarChamado")
	public ResponseEntity<Chamado> salvarChamado(@RequestBody @Valid Chamado chamado) throws ExceptionHelpDesk {

		if(chamado ==  null) {
			throw new ExceptionHelpDesk("Chamado não pode sere NULL");
		}

		chamado = chamadoRepository.save(chamado);

		return new ResponseEntity<>(chamado, HttpStatus.OK);

	}
	
	
	//testar e substituir por DTO
	@ResponseBody
	@GetMapping(value = "**/obterChamadoPorId/{id}")
	public ResponseEntity<Chamado> obterChamadoPorId(@PathVariable("id") Long id) throws ExceptionHelpDesk {

		Chamado chamado = chamadoRepository.findById(id).orElse(null);

		if(chamado == null) {
			throw new ExceptionHelpDesk("Não foi encotrado o Produto com o código: " + id);
		}

		return new ResponseEntity<>(chamado, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping(value = "**/obterChamados")
	public ResponseEntity<Chamado> obterChamados() throws ExceptionHelpDesk {

		return new ResponseEntity(chamadoRepository.findAll(), HttpStatus.OK);
	}
	
}
