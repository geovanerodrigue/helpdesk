package com.helpdesk.enums;

public enum TipoPessoa {
	
   ADMINISTRADOR("Administrador"),
   USUARIO("Usuário"),
   TECNICO("Técnico");
	
	private String descricao;

	private TipoPessoa(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
