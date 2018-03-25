package com.rached.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.rached.model.Departement;
import com.rached.model.UserStruct;
import com.rached.model.Utilisateur;

public interface UserStructRepository extends CrudRepository<UserStruct,Serializable>{
	@Query("select u from UserStruct us,Utilisateur u,Departement d WHERE u = us.utilisateur and d = us.departement"
			+ " AND u = ?1 AND d = ?2")
	UserStruct findUserStruct(Utilisateur u,Departement d);
	@Query("select us from UserStruct us WHERE us.utilisateur = ?1")
	List<UserStruct> getAllStructsOfUser(Utilisateur u);
	
	@Query("select DISTINCT(d) from Departement d,UserStruct us where us.utilisateur = ?1 AND us.departement = d AND us.dateAffectation = (select MAX(usd.dateAffectation) FROM UserStruct usd where usd.utilisateur = ?1)")
	Departement getDeptOfUser(Utilisateur u);
}
