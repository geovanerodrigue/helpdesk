package com.helpdesk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpdesk.model.Acesso;
import com.helpdesk.repository.AcessoRepository;



@Service
public class AcessoService {

	@Autowired
	private AcessoRepository acessoRepository;

	public Acesso save(Acesso acesso) {
		return acessoRepository.save(acesso);
	}



}