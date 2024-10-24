package com.helpdesk.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.helpdesk.model.Empresa;
import com.helpdesk.model.Usuario;

@Repository
@Transactional
public interface EmpresaRepository extends CrudRepository<Empresa, Long> {

}
