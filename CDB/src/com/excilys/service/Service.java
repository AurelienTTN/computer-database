package com.excilys.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.excilys.mapper.Mapper;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.model.Page;
import com.excilys.persistence.Dao;


public final class Service {
	
	    private static Service instance;
	    private Dao base;
	    private Mapper mappy;
	    

	    private Service() {

	        try {
	            Thread.sleep(1000);
	        } catch (InterruptedException ex) {
	            ex.printStackTrace();
	        }
	        
	        String url = "jdbc:mysql://127.0.0.1:3306/computer-database-db";
			String name = "admincdb";
			String passwd = "qwerty1234";
					
			this.base = com.excilys.persistence.Dao.getInstance(url,name,passwd);
			this.base.connection();  
			this.mappy= new Mapper();
	    }

	    public static Service getInstance() {
	        if (instance == null) {
	            instance = new Service();
	        }
	        return instance;
	    }
	    
	    
	
	public List <Computer> getListComputer(){
		return this.base.listeComputer();
	}
	
	
	public List <Company> getListCompany(){
		return this.base.listeCompanies();
			
	}
	
	
	public Computer getOneComputer(int id){
		return this.base.oneComputer(id);
		
	} 
	
	public int getNombreTotalComputer() {
		return this.base.getNombreTotalOrdinateur();
	}
	
	public List <Computer> affichageParPage(int num_page) throws SQLException {
		int nb_max = this.base.nombreOrdinateur();
		Page page = com.excilys.model.Page.getInstance(257);
		int[] index = page.getIndex(num_page);
		return this.base.listeSpecifiquesComputers(index[0],index[1]);
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
