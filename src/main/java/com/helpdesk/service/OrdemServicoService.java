package com.helpdesk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.helpdesk.model.Atividade;
import com.helpdesk.model.OrdemDeServico;
import com.helpdesk.repository.OrdemServicoRepository;

@Service
public class OrdemServicoService implements UserDetailsService {

	@Autowired
	OrdemServicoRepository ordemServicoRepository;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public OrdemDeServico salvarAtividade(OrdemDeServico ordemDeServico) {
		
		for (Atividade element : ordemDeServico.getAtividade()) {
			element.setOrdemDeServico(ordemDeServico);
		}
		
		ordemDeServico = ordemServicoRepository.save(ordemDeServico); 
		
		return ordemDeServico;
	}
	
}
