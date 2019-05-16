package com.app.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.app.domain.Usuario;
import com.app.services.UsuarioService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins="*")
public class UsuarioResources {
	
	@Autowired
	private UsuarioService service;
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")	
	@RequestMapping(method= RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Usuario obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Usuario> find(@PathVariable Integer id) {
			Usuario obj= service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	

}
