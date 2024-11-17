package com.helpdesk.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.helpdesk.model.Pessoa;
import com.helpdesk.model.Usuario;
import com.helpdesk.repository.PessoaRepository;
import com.helpdesk.repository.UsuarioRepository;





@Service
public class PessoaUserService {

	@Autowired
	PessoaRepository pessoaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ServiceSendEmail serviceSendEmail;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Pessoa salvarPessoa(Pessoa pessoa) {


			pessoa = pessoaRepository.save(pessoa);

			Usuario usuarioSave = usuarioRepository.findUserByPessoa(pessoa.getId(), pessoa.getEmail());

			if (usuarioSave == null) {

				String constraint = usuarioRepository.consultaConstraintAcesso();
				if (constraint != null) {
					jdbcTemplate.execute("begin; alter table usuarios_acesso drop constraint " + constraint +"; commit;");
				}

				usuarioSave = new Usuario();
				usuarioSave.setDataAtualSenha(Calendar.getInstance().getTime());
				usuarioSave.setEmpresa(pessoa.getEmpresa());
				usuarioSave.setPessoa(pessoa);
				usuarioSave.setLogin(pessoa.getEmail());

				String senha = pessoa.getSenhaUsuario();
				String senhaCript = new BCryptPasswordEncoder().encode(senha);

				usuarioSave.setSenha(senhaCript);
				
		
				//erro ta aqui
				usuarioSave = usuarioRepository.save(usuarioSave);

				usuarioRepository.insereAcessoUser(usuarioSave.getId());

				StringBuilder menssagemHtml = new StringBuilder();

				menssagemHtml.append("<b>Segue abaixo seus dados de acesso para a loja virtual</b><br/>");
				menssagemHtml.append("<b>Login: </b>"+pessoa.getEmail()+"<br/>");
				menssagemHtml.append("<b>Senha: </b>").append(senha).append("<br/><br/>");
				menssagemHtml.append("Obrigado!");

				try {
				  serviceSendEmail.enviarEmailHtml("Acesso Gerado para Loja Virtual", menssagemHtml.toString() , pessoa.getEmail());
				}catch (Exception e) {
					e.printStackTrace();
				}

			}

			return pessoa;
		}
	
/*
	public Usuario salvarPessoaEmpresa(Usuario usuario) {
		usuario.getEmpresa();
		
	}
	*/

}
