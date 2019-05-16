package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.domain.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Integer>{
	
}
