package com.helpdesk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.helpdesk.model.Empresa;
import com.helpdesk.model.Endereco;
import com.helpdesk.model.dto.CepDTO;
import com.helpdesk.repository.EmpresaRepository;

@Service
public class EmpresaService implements UserDetailsService {

	@Autowired
	private EmpresaRepository empresaRepository;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public CepDTO consultaCep(String cep) {
		return new RestTemplate().getForEntity("https://viacep.com.br/ws/" + cep + "/json/", CepDTO.class).getBody();
	}
	
	public Empresa salvarEndereco(Empresa empresa) {
		
		for (Endereco element : empresa.getEndereco()) {
			element.setEmpresa(empresa);
		}
		
		empresa = empresaRepository.save(empresa); 
		
		return empresa;
	}
	
}
