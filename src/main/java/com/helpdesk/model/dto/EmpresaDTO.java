package com.helpdesk.model.dto;

import java.io.Serializable;

public class EmpresaDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private Long id;
    private String nome;
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	

}
