package com.excilys.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.excilys.mapper.Mapper;
import com.excilys.model.*;
import com.excilys.persistence.Dao;


public final class Service {
	
	    private static Service instance;
	    public String value;
	    private Dao base;
	    private Mapper mappy;
	    

	    private Service(String value) {

	        try {
	            Thread.sleep(1000);
	        } catch (InterruptedException ex) {
	            ex.printStackTrace();
	        }
	        this.value = value;
	        
	        String url = "jdbc:mysql://127.0.0.1:3306/computer-database-db";
			String name = "admincdb";
			String passwd = "qwerty1234";
					
			this.base = new Dao(url,name,passwd);
			this.base.connection();  
			this.mappy= new Mapper();
	    }

	    public static Service getInstance(String value) {
	        if (instance == null) {
	            instance = new Service(value);
	        }
	        return instance;
	    }
	    
	    
	
	public List <Computer> showListComputer() throws SQLException{
		
		return this.base.ListeComputer();
		
	}
	
	public List <Company> showListCompany() throws SQLException{
		
		return this.base.ListeCompanies();
	}
	
	public Computer showOneComputer(int id)throws SQLException{
		
		return this.base.OneComputer(id);
	} 
	
	public Computer creerComputer(String name,LocalDate date1, LocalDate date2, int id_company) {
		
		return this.mappy.createComputer(name,date1,date2,id_company);
	}
	
	public void ajouterComputer(String name,LocalDate date1, LocalDate date2, int id_company) {
		Computer pc = creerComputer(name,date1,date2,id_company);
		this.base.ajouterUnComputer(pc);
	}
	
	public void changerInfoComputer(int id, int colonne, Object value) {
		this.base.updateComputer(id, colonne, value);
	}
	
	public void effacerComputer(int id) {
		this.base.deleteComputer(id);
	}
	
	
}
