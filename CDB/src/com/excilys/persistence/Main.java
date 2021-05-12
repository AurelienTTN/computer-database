package com.excilys.persistence;

import java.time.LocalDate;

import com.excilys.model.Computer;


public class Main {

	public static void main(String[] args) {
		
		
		
		
		String url = "jdbc:mysql://127.0.0.1:3306/computer-database-db";
		String name = "admincdb";
		String passwd = "qwerty1234";
				
		Dao base = new Dao(url,name,passwd);
		base.connection();
		
		
		String name_pc1 = "Lenovo IdeaPad S540";
		int compagny_id = 40;
		LocalDate entree_pc1 = LocalDate.of(2015,6,22);
		LocalDate sortie_pc1 = LocalDate.of(2016,6,22);
		Computer pc1 = new Computer(name_pc1,compagny_id,entree_pc1,sortie_pc1);
		
		/*
		base.ajouterUnComputer(pc1);
		base.afficherListeCompagnies();
		base.afficherUnComputer(581);
		*/
		LocalDate entree_pc2 = LocalDate.of(2030,6,22);
		base.updateComputer(587,2,"Jackie");
		base.afficherListeComputer();
		
		
		

	}

}
