package com.helpdesk.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.helpdesk.ExceptionHelpDesk;
import com.helpdesk.model.Empresa;
import com.helpdesk.repository.EmpresaRepository;
import com.helpdesk.repository.EnderecoRepository;
import com.helpdesk.service.EmpresaService;


@Controller
@RestController
public class EmpresaController {
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private EmpresaService empresaService;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	
	
	@ResponseBody
	@PostMapping(value = "**/salvarEmpresa")
	public ResponseEntity<Empresa> salvarEmpresa(@RequestBody @Valid Empresa empresa) throws ExceptionHelpDesk {

		empresa = empresaService.salvarEndereco(empresa);

		return new ResponseEntity<>(empresa, HttpStatus.OK);
	}
	
}
