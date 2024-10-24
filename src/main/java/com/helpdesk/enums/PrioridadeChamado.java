package com.helpdesk.enums;

public enum PrioridadeChamado {
	
	ALTO("Alto"),
	BAIXO("Baixo"),
	MEDIO("Médio");
	
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	private PrioridadeChamado(String descricao) {
		this.descricao = descricao;
	}
	
	

}
