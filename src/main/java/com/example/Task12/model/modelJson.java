package com.example.Task12.model;

import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Date;
import java.io.File;

//a class that is meant to model a Json file with a distinct appCode, version and config properties  
@Entity
@SequenceGenerator(name="sequence", initialValue=5, allocationSize=100)
public class modelJson {
	//Generating a sequential id for each input
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sequence")
	private int secretid;
	
	//giving each model an appCode and version
	private int appCode;
	private double version;
	
	//the string constructor meant to hold the Json text as a string
	private String config;
	
	//generating the creation/modification date for each model
	private Date dateModified =new Date();

	
	//getters setters and toString for modelJson attributes
	public int getAppCode() {
		return appCode;
	}

	public void setAppCode(int appCode) {
		this.appCode = appCode;
	}

	public double getVersion() {
		return version;
	}

	public void setVersion(double version) {
		this.version = version;
	}
	
	public String getConfig() {
		return config;
	}

	public void setConfig(String config) {
		this.config = config;
	}
	
	public int getSecretid() {
		return secretid;
	}

	
	@Override
	public String toString() {
		return "modelJson [appCode=" + appCode + ", version=" + version + ", config=" + config + ", dateModified="
				+ dateModified + "]";
	}

	
	
	
	
}
