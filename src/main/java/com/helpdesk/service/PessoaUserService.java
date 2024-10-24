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
		//juridica = pesssoaRepository.save(juridica);


			pessoa = pessoaRepository.save(pessoa);

			Usuario usuario = usuarioRepository.findUserByPessoa(pessoa.getId(), pessoa.getEmail());

			if (usuario == null) {

				String constraint = usuarioRepository.consultaConstraintAcesso();
				if (constraint != null) {
					jdbcTemplate.execute("begin; alter table usuarios_acesso drop constraint " + constraint +"; commit;");
				}

				usuario = new Usuario();
				usuario.setDataAtualSenha(Calendar.getInstance().getTime());
				usuario.setEmpresa(pessoa.getEmpresa());
				usuario.setPessoa(pessoa);
				usuario.setLogin(pessoa.getEmail());

				String senha = "" + Calendar.getInstance().getTimeInMillis();
				String senhaCript = new BCryptPasswordEncoder().encode(senha);

				usuario.setSenha(senhaCript);

				usuario = usuarioRepository.save(usuario);

				usuarioRepository.insereAcessoUser(usuario.getId());

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

}
