package com.helpdesk.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.helpdesk.ExceptionHelpDesk;
import com.helpdesk.model.OrdemDeServico;
import com.helpdesk.repository.OrdemServicoRepository;
import com.helpdesk.service.OrdemServicoService;

@Controller
public class OrdemServicoController {
	
	
	@Autowired
	OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	OrdemServicoService ordemServicoService;
	
	@ResponseBody
	@PostMapping(value = "**/salvarOrdemDeServico")
	public ResponseEntity<OrdemDeServico> salvarChamado(@RequestBody @Valid OrdemDeServico ordemDeServico) throws ExceptionHelpDesk {

		if(ordemDeServico ==  null) {
			throw new ExceptionHelpDesk("Chamado não pode sere NULL");
		}
		
		if(ordemDeServico.getDescricao() == null) {
			throw new ExceptionHelpDesk("Descrição não pode sere NULL");
		}
		
		if(ordemDeServico.getAtividade() == null) {
			throw new ExceptionHelpDesk("Descrição não pode sere NULL");
		}


		ordemDeServico = ordemServicoService.salvarAtividade(ordemDeServico);

		return new ResponseEntity<>(ordemDeServico, HttpStatus.OK);

	}

}
