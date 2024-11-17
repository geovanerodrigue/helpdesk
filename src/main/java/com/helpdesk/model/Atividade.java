package com.helpdesk.model;

import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "atividade")
@PrimaryKeyJoinColumn(name = "id")
public class Atividade extends Ticket {

	private static final long serialVersionUID = 1L;

	

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "servico_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "servico_fk"))
	private OrdemDeServico ordemDeServico;

	public OrdemDeServico getOrdemDeServico() {
		return ordemDeServico;
	}

	public void setOrdemDeServico(OrdemDeServico ordemDeServico) {
		this.ordemDeServico = ordemDeServico;
	}
 
}
