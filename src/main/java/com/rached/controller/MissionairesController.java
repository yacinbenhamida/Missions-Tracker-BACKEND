package com.rached.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rached.model.AffectMissDep;
import com.rached.model.Grade;
import com.rached.model.Missionaire;
import com.rached.services.MissionaireServices;
import com.rached.services.Services;

@RestController
@RequestMapping("/api/missionaires")
public class MissionairesController {
	@Autowired
	@Qualifier("missionaireServicesImpl")
	private MissionaireServices implmiss;
	
	@RequestMapping(value="/getallMissionairesOfDEP/{codeDep}",method= RequestMethod.GET )
	public List<Missionaire>getAllMissionairesOfDep(@PathVariable("codeDep") String codeDep){
		return implmiss.getAllMissionairesDep(codeDep);
	}
	
	@RequestMapping(value="/allMissionairesOfDEP/{codeDep}",method= RequestMethod.GET )
	public List<AffectMissDep>getAllMissionaires(@PathVariable("codeDep") String codeDep){
		return implmiss.getMissionairesOfDepartement(codeDep);
	}
	@RequestMapping(value = "/findMissionaire/{code}", method = RequestMethod.GET)
	public Missionaire getMissionaire(@PathVariable("code") Long id) {
		return implmiss.getRecordById(Long.valueOf(id));
	}
	@RequestMapping(value = "/findMissionaireByCIN/{cin}", method = RequestMethod.GET)
	public Missionaire getMissionaireBYCIN(@PathVariable("cin") Long cin) {
		return implmiss.getMissByCIN(cin);
	}
	
	@RequestMapping(value = "/insertMissionaire", method = RequestMethod.POST )
	public AffectMissDep insertMissionaire(@RequestBody AffectMissDep elem) {
		elem.setDateAffectation(Date.valueOf(LocalDate.now()));
		Missionaire m = implmiss.insertRecord(elem.getMissionaire());
		System.out.println(m+"");
		elem.setMissionaire(implmiss.getMissByCIN(m.getCin()));
		return implmiss.insertMissionaireWithAffectation(elem);
	}
	@RequestMapping(value = "/updateAffectMissionaire", method = RequestMethod.POST)
	public AffectMissDep updateMissionaireAffectation(@RequestBody AffectMissDep elem) {
		 return implmiss.updateMissDep(elem);
	}
	
	@RequestMapping(value = "/updateMissionaire", method = RequestMethod.POST)
	public Missionaire updateMissionaire(@RequestBody Missionaire elem) {
		 return implmiss.updateRecord(elem);
	}
	@RequestMapping(value = "/deleteMissionaire/{code}/{codeAff}", method = RequestMethod.GET)
	public void deleteMissionaire(@PathVariable("code") int code,@PathVariable("codeAff") int codeAff) {
		implmiss.deleteAffectation(codeAff);
		implmiss.deleteRecord(Long.valueOf(code));
	}
}
