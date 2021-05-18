package com.excilys.controlers;


import java.time.LocalDate;
import java.util.List;

import com.excilys.model.Computer;
import com.excilys.model.Page;
import com.excilys.service.Service;
import com.excilys.ui.ChoixUtilisateur;
import com.excilys.ui.ComputerCLI;

public class ComputerCtr {
	

	private static ComputerCtr instance;
	private Service service;
	private ComputerCLI computerCLI;
	private ChoixUtilisateur choixUtilisateur;

	
	private ComputerCtr() {
		this.service = Service.getInstance();
		this.computerCLI = ComputerCLI.getInstance();
		this.choixUtilisateur = ChoixUtilisateur.getInstance();
		
	}
	
	public static ComputerCtr getInstance() {
	       if (instance == null) {
	            instance = new ComputerCtr();
	        }
	        return instance;
	    }
	
	public void afficherListeComputers(){
		List<Computer> computers= this.service.getListComputer();
		this.computerCLI.afficherListeComputers(computers);
	}
	
	public void afficherUnOrdinateur() {
		int id = choixUtilisateur.choixOrdinateur();
		Computer computer = this.service.getOneComputer(id);
		this.computerCLI.afficherComputer(computer);
	}
	
	public void ajouterUnOrdinateur() {
		String name = choixUtilisateur.choixNom();
		LocalDate entree = choixUtilisateur.choixDateEntree();
		LocalDate out = choixUtilisateur.choixDateSortie();
		int id_company = choixUtilisateur.choixIDCompany();
		this.service.ajouterComputer(name, entree, out, id_company);
		
	}
	
	public void majOrdinateur() {
		int id = choixUtilisateur.choixOrdinateur();
		int colonne = choixUtilisateur.choixChampAChanger();
		switch(colonne){
			case 2:{
				String name = choixUtilisateur.choixNom();
				service.changerInfoComputer(id, colonne, name);
				break;
			}
			case 3:{
				LocalDate date_entry = choixUtilisateur.choixDateEntree();
				service.changerInfoComputer(id, colonne, date_entry);
				break;
			}
			case 4:{
				LocalDate date_out = choixUtilisateur.choixDateSortie();
				service.changerInfoComputer(id, colonne, date_out);
				break;
			}
			case 5:{
				int id_comp = choixUtilisateur.choixIDCompany();
				service.changerInfoComputer(id, colonne, id_comp);
				break;
			}
		}	
	}
	
	public void effacerOrdinateur() {
		int id = choixUtilisateur.choixOrdinateur();
		service.effacerComputer(id);
	}
	
	public void afficherOrdinateurParPages() {
		int max_ordinateur = this.service.getNombreTotalComputer();
		Page page = new Page(max_ordinateur);
		int choix = 1;
		List<Computer> computers= this.service.getElementPage(1,page.getNBElementParPage());
		this.computerCLI.afficherListeComputers(computers);
		
		while(choix!=0) {
			choix = choixUtilisateur.choixMenuPage();
			switch(choix) {
			case 1 :{
				if(page.getNumeroPage()!=1)
					page.getPagePrecedente();
					break;
			}
			case 2 :{
				if(page.getNumeroPage()!=(page.getMaxElement()/page.getNBElementParPage()+1)) 
					page.getPageSuivante();
				break;
			}
			case 3 :{
				int num = choixUtilisateur.choixPageSpecifique();
				page.setNumPage(num);	
				break;
			}
			}
			if(choix!=0) {
				int index_debut = page.getNumeroPage()*page.getNBElementParPage()-page.getNBElementParPage()-1;
				computers= this.service.getElementPage(index_debut,page.getNBElementParPage());
				this.computerCLI.afficherListeComputers(computers);
			}
	}
	}
}
	

