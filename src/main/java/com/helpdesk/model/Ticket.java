package com.helpdesk.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.helpdesk.enums.PrioridadeChamado;
import com.helpdesk.enums.StatusChamados;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SequenceGenerator(name = "seq_ticket", sequenceName = "seq_ticket", initialValue = 1, allocationSize = 1)
public abstract class Ticket implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ticket")
	private Long id;
	
	@Column(nullable = false)
	private String titulo;
	
	@Column(nullable = false)
	private Date datainicio;
	
	@Column(nullable = true)
	private Date datafinal;
	
	//corrigir mapeamento
	@Column(nullable = true)
	@OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<TextoChamado> textoChamado = new ArrayList<>();
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "descricao_id", nullable = false)
    private TextoChamado descricao;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusChamados statusChamados;
	

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private PrioridadeChamado prioridade;
	
	@ManyToOne
	@JoinColumn(name = "solicitante_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "solicitante_fk"))
	private Pessoa solicitante;
	
	@ManyToOne
	@JoinColumn(name = "tecnico_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "tecnico_fk"))
	private Pessoa tecnico;
	

	public PrioridadeChamado getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(PrioridadeChamado prioridade) {
		this.prioridade = prioridade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public TextoChamado getDescricao() {
		return descricao;
	}

	public void setDescricao(TextoChamado descricao) {
		this.descricao = descricao;
	}

	public Date getDatainicio() {
		return datainicio;
	}

	public void setDatainicio(Date datainicio) {
		this.datainicio = datainicio;
	}

	public Date getDatafinal() {
		return datafinal;
	}

	public void setDatafinal(Date datafinal) {
		this.datafinal = datafinal;
	}

	public List<TextoChamado> getTextoChamado() {
		return textoChamado;
	}

	public void setTextoChamado(List<TextoChamado> textoChamado) {
		this.textoChamado = textoChamado;
	}

	public StatusChamados getStatusChamados() {
		return statusChamados;
	}

	public void setStatusChamados(StatusChamados statusChamados) {
		this.statusChamados = statusChamados;
	}


	public Pessoa getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Pessoa solicitante) {
		this.solicitante = solicitante;
	}

	public Pessoa getTecnico() {
		return tecnico;
	}

	public void setTecnico(Pessoa tecnico) {
		this.tecnico = tecnico;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		return Objects.equals(id, other.id);
	}

}
