package com.helpdesk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.helpdesk.model.Chamado;
import com.helpdesk.model.Pessoa;

@Repository
@Transactional
public interface ChamadoRepository extends CrudRepository<Chamado, Long> {
	
	@Query(value = "select p from Chamado p where upper(trim(p.titulo)) like %?1%")
	public List<Chamado> pesquisaPorTitulo(String nome);

}
