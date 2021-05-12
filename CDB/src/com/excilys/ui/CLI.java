package com.excilys.ui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import com.excilys.model.Computer;
import com.excilys.persistence.Dao;

public class CLI {
	
	public void CLI() {
		
	}
	
	public void affichageUtilisateur() throws IOException {
		
		String url = "jdbc:mysql://127.0.0.1:3306/computer-database-db";
		String name = "admincdb";
		String passwd = "qwerty1234";
		int choix;
		
		boolean decision = true;
		Dao base = new Dao(url,name,passwd);
		base.connection();
		Scanner sc = new Scanner(System.in);
		
		
		do{
			System.out.println("Que voulez vous faire ? Tapez le numéro correspondant à votre requête \n");
			System.out.println("Afficher la liste des ordinateurs - 1 ");
			System.out.println("Afficher la liste des compagnies - 2 ");
			System.out.println("Afficher un ordinateur en particulier - 3 ");
			System.out.println("Ajouter un nouvel ordinateur - 4 ");
			System.out.println("Mettre à jour un certain ordinateur - 5 ");
			System.out.println("Effacer un ordinateur - 6\n");
			System.out.println("Quitter - 0\n");
			
			System.out.println("Faites votre choix :");

			choix = sc.nextInt();
			
			switch(choix) {
				case 0:{
					decision=false;
					break;
				}
				case 1:{
					System.out.println("Voici la liste des ordinateurs :");
					base.afficherListeComputer();
					break;
				}
				case 2:{
					System.out.println("Voici la liste des compagnies :");
					base.afficherListeCompagnies();
					break;
				}
				case 3:{
					System.out.println("Veuillez entrer l'id de l'ordinateur souhaité");
					Scanner scan_id = new Scanner(System.in);
					int id = scan_id.nextInt();
					base.afficherUnComputer(id);
					break;
					
				}
				case 4:{
					System.out.println("Entrez un nom d'ordinateur");
					Scanner scan_ordi = new Scanner(System.in);
					
					String name_computer = scan_ordi.nextLine();
					System.out.println("Entrez le jour d'entrée de cet ordinateur");
					int day_entry = scan_ordi.nextInt();
					System.out.println("Entrez le mois d'entrée de cet ordinateur");
					int month_entry = scan_ordi.nextInt();
					System.out.println("Entrez l'année d'entrée de cet ordinateur");
					int year_entry = scan_ordi.nextInt();
					
					System.out.println("Entrez le jour de sortie de cet ordinateur");
					int day_out = scan_ordi.nextInt();
					System.out.println("Entrez le mois de sortie de cet ordinateur");
					int month_out = scan_ordi.nextInt();
					System.out.println("Entrez l'année de sortie de cet ordinateur");
					int year_out = scan_ordi.nextInt();
					
					System.out.println("Choisissez l'id de compagnie ( compris entre 0 et 40");
					int id_ordi = scan_ordi.nextInt();
					
					LocalDate entree_pc1 = LocalDate.of(year_entry,month_entry,day_entry);
					LocalDate sortie_pc1 = LocalDate.of(year_out,month_out,day_out);
					Computer new_computer = new Computer(name_computer,id_ordi,entree_pc1,sortie_pc1);
					base.ajouterUnComputer(new_computer);
					break;
					
					
				}
				case 5:{
					Scanner scan5 = new Scanner(System.in);
					System.out.println("Entrez l'id de l'ordinateur à mettre à jour");
					int id_fixe = scan5.nextInt();
					System.out.println("Entrez la colonne a changer (2-name, 3-introduced, 4-discontinued, 5-company_id");
					int colonne = scan5.nextInt();
					
					switch (colonne) {
						case 2 :{ 
							System.out.println("Entrez un nom d'ordinateur");
						    Scanner scan_ordi = new Scanner(System.in);
						    String name_computer = scan_ordi.nextLine();
						    base.updateComputer(id_fixe, colonne, name_computer);
						    break;
						}
						case 3:{
							Scanner scan_ordi = new Scanner(System.in);
							System.out.println("Entrez le jour d'entrée de cet ordinateur");
							int day_entry = scan_ordi.nextInt();
							System.out.println("Entrez le mois d'entrée de cet ordinateur");
							int month_entry = scan_ordi.nextInt();
							System.out.println("Entrez l'année d'entrée de cet ordinateur");
							int year_entry = scan_ordi.nextInt();
							LocalDate entree_pc = LocalDate.of(year_entry,month_entry,day_entry);
							base.updateComputer(id_fixe, colonne, entree_pc);
							break;
							
						}
						case 4:{
							Scanner scan_ordi = new Scanner(System.in);
							System.out.println("Entrez le jour de sortie de cet ordinateur");
							int day_entry = scan_ordi.nextInt();
							System.out.println("Entrez le mois de sortie de cet ordinateur");
							int month_entry = scan_ordi.nextInt();
							System.out.println("Entrez l'année de sortie de cet ordinateur");
							int year_entry = scan_ordi.nextInt();
							LocalDate entree_pc = LocalDate.of(year_entry,month_entry,day_entry);
							base.updateComputer(id_fixe, colonne, entree_pc);
							break;
						}
						case 5:{
							System.out.println("Entrez l'id de la compagnie");
							Scanner scan_ordi = new Scanner(System.in);
							int id_comp = scan_ordi.nextInt();
							base.updateComputer(id_fixe, colonne,id_comp);
							break;
						}
					}	
					break;
				}
				case 6:
					System.out.println("Entrez l'id de l'ordinateur a enlever.");
					Scanner scan_ordi = new Scanner(System.in);
					int id_comp = scan_ordi.nextInt();
					base.deleteComputer(id_comp);
					break;
					
				
			}
			
			
			if (choix!=0) {
				System.out.println("\nVoulez vous continuer d'agir sur la base de donnée ? tapez 0 pour quitter");
				choix = sc.nextInt();
				if(choix==0) {
					decision=false;
				}
			}
			
		}while(decision);
		
		
		System.out.println("A bientôt chez Excilys ma caille");
		
	}
	

}
