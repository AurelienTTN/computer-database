package com.excilys.persistence;

import java.sql.*;
import java.time.LocalDate;

import com.excilys.model.Computer;


public class Main {

	public static void main(String[] args) throws SQLException {
		
		
		
		
		String url = "jdbc:mysql://127.0.0.1:3306/computer-database-db";
		String name = "admincdb";
		String passwd = "qwerty1234";
				
		Dao base = new Dao(url,name,passwd);
		base.connection();
		
		ResultSet result = base.ListeComputer();
		
		while(result.next()) {
			int id = result.getInt(1);
			String name_pc = result.getString(2);
			int num_company = result.getInt(5);
			LocalDate date= LocalDate.of(2020,1,1);
			LocalDate date2 = LocalDate.of(2020,1,1);
			
			if (result.getDate(3) != null) {
				date = result.getDate(3).toLocalDate();
			}
			if (result.getDate(4) != null) {
				date2 = result.getDate(4).toLocalDate();
			}
			
			System.out.println(id+" "+name_pc+" "+num_company+" "+date+" "+date2);
		}
		
		
		
		
		
		
		
		/*
		String name_pc1 = "Lenovo IdeaPad S540";
		int compagny_id = 40;
		LocalDate entree_pc1 = LocalDate.of(2015,6,22);
		LocalDate sortie_pc1 = LocalDate.of(2016,6,22);
		Computer pc1 = new Computer(name_pc1,compagny_id,entree_pc1,sortie_pc1);
		base.ajouterUnComputer(pc1);
		base.afficherListeCompagnies();
		base.afficherUnComputer(581);
	
		LocalDate entree_pc2 = LocalDate.of(2030,6,22);
		base.updateComputer(587,2,"Jackie");
		base.afficherListeComputer();
		*/
		
		
		

	}

}
