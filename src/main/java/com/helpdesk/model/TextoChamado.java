package com.helpdesk.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "texto")
@SequenceGenerator(name = "seq_texto", sequenceName = "seq_texto", initialValue = 1, allocationSize = 1)
public class TextoChamado implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_texto")
	private Long id;

	@Column(nullable = false)
	private String texto;
	
    @ManyToOne
    @JoinColumn(name = "ticket_id")
	private Ticket ticket;
	
	@Column(nullable = true)
	@OneToMany(mappedBy = "textoChamado",  cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Imagens> imagens = new ArrayList<>();
	
	@Column(nullable = false)
	private Date data;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}


	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public List<Imagens> getImagens() {
		return imagens;
	}

	public void setImagens(List<Imagens> imagens) {
		this.imagens = imagens;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	
}
