package com.helpdesk.enums;

public enum StatusChamados {
	
	ABERTO("Aberto"),
	PAUSADO("Pausado"),
	ATENDIMENTO("Em atendimento"),
	FECHADO("Encerrado");
	
	private String descricao;

	private StatusChamados(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
