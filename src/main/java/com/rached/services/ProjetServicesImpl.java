package com.rached.services;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.rached.model.AvoirBudgProg;
import com.rached.model.Departement;
import com.rached.model.Projet;

@Service
@Qualifier("projetServicesImpl")
public class ProjetServicesImpl implements ProjetServices {

	@Autowired
	private ProjetRepository repo;
	@Autowired
	private AvoirBudgProjetRepository budgrepo;
	@Override
	public List<Projet> getAllRecords() {
		List<Projet> res = new ArrayList<Projet>();
		Iterator<Projet>it = repo.findAll().iterator();
		while(it.hasNext()) {
			res.add(it.next());
		}
		return res;
	}

	@Override
	public Projet getRecordById(Long id) {
		return repo.findOne(id);
	}

	@Override
	public Projet insertRecord(Projet elem) {
		return repo.save(elem);
	}

	@Override
	public void deleteRecord(int id) {
		Projet zp = repo.findOne(id);
		List<AvoirBudgProg>budgets = budgrepo.getAllBudgetsOfProg(zp);
		for (AvoirBudgProg avoirBudgProg : budgets) {
			budgrepo.delete(avoirBudgProg);
		}
		repo.delete(zp);	
	}

	@Override
	public Projet updateRecord(Projet elem) {
		return repo.save(elem);
	}

	@Override
	public Projet getRecordBycode(String id) {
		return repo.findOne(id);
	}

	@Override
	public void deleteRecord(Long valueOf) {
		repo.delete(valueOf);
		
	}

	@Override
	public List<Projet> getAllProjetsofdep(Departement d) {
		return repo.getAllDepsProjects(d);
	}

}
