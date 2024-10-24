package com.helpdesk.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "chamado")
@PrimaryKeyJoinColumn(name = "id")
public class Chamado extends Ticket {

	private static final long serialVersionUID = 1L;


}
