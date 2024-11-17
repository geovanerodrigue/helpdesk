package com.helpdesk.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "servico")
@PrimaryKeyJoinColumn(name = "id")
public class OrdemDeServico extends Ticket {

	private static final long serialVersionUID = 1L;
	
	@Column(nullable = true)
	@OneToMany(mappedBy = "ordemDeServico", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Atividade> atividade = new ArrayList<>();
	
	@Column(nullable = true)
	private String equipamentoUtilizado;

	public List<Atividade> getAtividade() {
		return atividade;
	}

	public void setAtividade(List<Atividade> atividade) {
		this.atividade = atividade;
	}

	public String getEquipamentoUtilizado() {
		return equipamentoUtilizado;
	}

	public void setEquipamentoUtilizado(String equipamentoUtilizado) {
		this.equipamentoUtilizado = equipamentoUtilizado;
	}

}
