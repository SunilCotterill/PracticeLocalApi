package com.example.Task12;

import com.example.Task12.model.modelJson;
import com.example.Task12.repo.modelJsonRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class testController {

	@Autowired
	modelJsonRepository repo;


	// post that allows user to post a new modelJson with an appCode and version specified in the path and config specified in the request body
	@PostMapping(path = "/api/{appCode}/config/{version}", consumes = { "application/json" })
	public String addmodelJsonVersion(@PathVariable("appCode") int appCode, @PathVariable("version") double version,
			@RequestBody String input) {
		//if existing modelJson with same appCode and version exists set its config to request body
		List<modelJson> getRid=repo.findByAppCodeVersion(appCode, version);
		if(!getRid.isEmpty()) {
		modelJson toDelete=getRid.get(0);
		toDelete.setConfig(input);
		repo.save(toDelete);
		return toDelete.getConfig();
		}
		
		//if not create a new modelJson and save it to the database
		else {
		//creating the modelJson and setting its attributes
		modelJson model= new modelJson();
		model.setVersion(version);
		model.setAppCode(appCode);
		model.setConfig(input);
		
		//saves the modelJson to the dataBase
		repo.save(model);
		//returns the modelJson config
		return model.getConfig();
		}

	}

	//get that returns the config of a specified appCode and version  
	@GetMapping(path = "/api/{appCode}/config/{version}", consumes = { "application/json" })
	public String viewByAppCodeAndVersion(@PathVariable("appCode") int appCode, @PathVariable("version") double version) {
		
		//creating a list of all modelJson with the specified appCode and versions
		List<modelJson> temp = repo.findByAppCodeVersion(appCode, version);
		//assigning the first item of the list to hold and returning hold's config. This assumes there is only one appCode version combination. 
		modelJson hold = temp.get(0);
		return hold.getConfig();
		
	}

	// get that returns all modelJsons if no appCode/version is specified
	@GetMapping("/api")

	public List<modelJson> getmodelJsons() {

		return repo.findAll();

	}
	
	
	//get that returns a list of all versions available for a specified appCode
	@GetMapping("/api/{appCode}/config")
	public List<Double> viewModelJsonConfig(@PathVariable("appCode") int appCode) {
		//creating a list of all modelJson with specified appCode
		List<modelJson> temp = repo.findByAppCodeSorted(appCode);
		//creating an empty list
		List<Double> list = new ArrayList<Double>();
		//adding each modelJson in temp's version to list
		for (modelJson l : temp) {
			list.add(l.getVersion());

		}
		
		return list;
	}
}
