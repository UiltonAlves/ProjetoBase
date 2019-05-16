package com.app.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique=true)
	@NotBlank(message="Username requered")
	private String email;
	
	private String name;
	
	@NotBlank(message="Senha requered")
	private String password;
	
	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.PERSIST)
	private List<Perfil> perfis = new ArrayList<Perfil>();
	
	public Usuario() {}
	

	public Usuario(String email, String name,String password) {
		this.email = email;
		this.name = name;
		this.password = password;
	}


	@Override
	public String toString() {
		return "Usuario [id=" + id + ", email=" + email + ", name=" + name + ", password=" + password + ", perfis="
				+ perfis + "]";
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public List<Perfil> getPerfis() {
		return perfis;
	}


	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}


	public void addPerfis(Perfil perfil) {
		this.perfis.add(perfil);
	}
}
