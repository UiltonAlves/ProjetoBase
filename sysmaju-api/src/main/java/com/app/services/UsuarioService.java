package com.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.config.security.UserSS;
import com.app.domain.Usuario;
import com.app.repositories.UsuarioRepository;
import com.app.services.exceptions.AuthorizationException;
import com.app.services.exceptions.ObjectNotFoundException;
import com.app.services.util.UserService;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Transactional
	public Usuario insert(Usuario obj) {
		UserSS user = UserService.authenticated();
		if(user == null || !user.hasRole("ROLE_ADMIN")) {
			throw new AuthorizationException("Acesso negado !");
		}
		obj.setId(null);
		obj.setPassword(pe.encode(obj.getPassword()));
		obj = usuarioRepository.save(obj);
		return obj;
	}
	
	
	
	public Usuario find(Integer id) {
		UserSS user = UserService.authenticated();
		if(user == null || !user.hasRole("ROLE_ADMIN")) {
			throw new AuthorizationException("Acesso negado !");
		}
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if(usuario == null){
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName());
		}
		System.out.println(usuario.get());
		return usuario.get();
	}
	
	public Usuario findUser(Integer id) {
		UserSS user = UserService.authenticated();
		if(user == null || !user.hasRole("ROLE_ATENDENTE") && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado !");
		}
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if(usuario == null){
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName());
		}
		return usuario.get();
	}



	public List<Usuario> findAll() {
		UserSS user = UserService.authenticated();
		if(user == null || !user.hasRole("ROLE_ADMIN")) {
			throw new AuthorizationException("Acesso negado !");
		}
		List<Usuario> lstUser = usuarioRepository.findAll();
		if(lstUser == null){
			throw new ObjectNotFoundException("Não foi encontrado nenhum usuario ! , Tipo: " + Usuario.class.getName());
		}
		return lstUser;
	}
}
