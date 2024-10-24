package com.helpdesk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.helpdesk.model.Pessoa;


@Repository
@Transactional
public interface PessoaRepository extends CrudRepository<Pessoa, Long>  {

	@Query(value = "select p from Pessoa p where upper(trim(p.nome)) like %?1%")
	public List<Pessoa> pesquisaPorNome(String nome);


}
