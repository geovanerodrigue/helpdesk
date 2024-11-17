package com.helpdesk.controller;

import java.util.Calendar;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.helpdesk.ExceptionHelpDesk;
import com.helpdesk.model.Pessoa;
import com.helpdesk.model.Usuario;
import com.helpdesk.repository.UsuarioRepository;
import com.helpdesk.service.PessoaUserService;

@Controller
public class UsuarioController {
	
	@Autowired
	private PessoaUserService pessoaUserService;
	
	private UsuarioRepository usuarioRepository;
	
	   private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	
	@ResponseBody
	@PostMapping(value = "**/salvarUsuario")
	public ResponseEntity<Usuario> salvarPessoa(@RequestBody @Valid Usuario usuario) throws ExceptionHelpDesk {

		if(usuario ==  null) {
			throw new ExceptionHelpDesk("Usuario fisica não pode ser NULL");
		}
		
		if(usuario.getEmpresa() ==  null) {
			throw new ExceptionHelpDesk("Empresa não pode ser NULL");
		}
		
		if(usuario.getLogin() ==  null) {
			throw new ExceptionHelpDesk("Login não pode ser NULL");
		}
		
		if(usuario.getPassword() ==  null) {
			throw new ExceptionHelpDesk("Senha não pode ser NULL");
		}
		
		if(usuario.getPassword() !=  null) {
            String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
            usuario.setSenha(senhaCriptografada);
		}
		
		if(usuario.getDataAtualSenha() == null) {
			usuario.setDataAtualSenha(Calendar.getInstance().getTime());
		}
		
		
		usuario = usuarioRepository.save(usuario);

		return new ResponseEntity<>(usuario, HttpStatus.OK);

	}

}
