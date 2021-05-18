package com.excilys.ui;

import java.sql.SQLException;

import com.excilys.controlers.CompanyCtr;
import com.excilys.controlers.ComputerCtr;

public class MenuCLI {
	

	private static MenuCLI instance;
	private ComputerCtr computerCtr;
	private CompanyCtr companyCtr;
	private ChoixUtilisateur choixUtilisateur;
	
	private MenuCLI() {
		this.computerCtr = ComputerCtr.getInstance();
		this.companyCtr = CompanyCtr.getInstance();
		this.choixUtilisateur = ChoixUtilisateur.getInstance();
	}
	
	
	public static MenuCLI getInstance() {
        if (instance == null) {
            instance = new MenuCLI();
        }
        return instance;
	}

	public void afficherMenu() {
		System.out.println("Que voulez vous faire ? Tapez le numéro correspondant à votre requête \n");
		System.out.println("Afficher la liste des ordinateurs - 1 ");
		System.out.println("Afficher la liste des compagnies - 2 ");
		System.out.println("Afficher un ordinateur en particulier - 3 ");
		System.out.println("Ajouter un nouvel ordinateur - 4 ");
		System.out.println("Mettre à jour un certain ordinateur - 5 ");
		System.out.println("Effacer un ordinateur - 6\n");
		System.out.println("Affichage par pages des ordinateurs- 7\n");
		System.out.println("Faites votre choix :");
	}
	
	public void useMenu(){
		
		this.afficherMenu();
		int choix = choixUtilisateur.choixMenu();
		
		switch(choix) {
		case 1:{
			this.computerCtr.afficherListeComputers();
			break;
		}
		case 2:{
			this.companyCtr.afficherListeCompagnies();
			break;
		}
		case 3:{
			this.computerCtr.afficherUnOrdinateur();
		}
		case 4:{
			this.computerCtr.ajouterUnOrdinateur();
		}
		case 5:{
			this.computerCtr.majOrdinateur();
		}
		case 6:{
			this.computerCtr.effacerOrdinateur();
		}
		case 7:{
			this.computerCtr.afficherOrdinateurParPages();
		}
		}
		
	}
}
