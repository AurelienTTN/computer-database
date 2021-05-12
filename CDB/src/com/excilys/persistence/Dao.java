package com.excilys.persistence;

import java.time.LocalDate;
import java.sql.*;
import com.excilys.model.Computer;

public class Dao {
	// Properties
	private String url;
	private String login;
	private String passwd;
	private Connection con = null;
	
	// Query
	private static final String AJOUT_ONE_COMPUTER = "INSERT INTO computer (name,introduced,discontinued,company_id) VALUES(?,?,?,?);";
	
	private static final String UPDATE_COMPUTER_NAME = "UPDATE computer SET name=? WHERE id=?";
	private static final String UPDATE_COMPUTER_INTRODUCED = "UPDATE computer SET introduced=? WHERE id=?";
	private static final String UPDATE_COMPUTER_DISCONTINUED = "UPDATE computer SET discontinued=? WHERE id=?";
	private static final String UPDATE_COMPUTER_COMPANY_ID = "UPDATE computer SET company_id=? WHERE id=?";
	
	private static final String DELETE_COMPUTER = "DELETE FROM computer WHERE id=?";

	// Constructor
	public Dao(String url, String login, String passwd) {
		this.url = url;
		this.login = login;
		this.passwd = passwd;

	}

	public void connection() {
		
		/* On essaye de se connecter à notre base de donnée grâce aux informations passées au construteur*/
		try {
		    this.con = DriverManager.getConnection( this.url, this.login, this.passwd );
		  
		} catch ( SQLException e ) {
			System.out.println(e);
		}
		     
	}
	
	
	public void afficherListeComputer() {
		String query = "SELECT * FROM computer;";
		ResultSet results;
		try {
		Statement stmt = this.con.createStatement();
		results = stmt.executeQuery(query);
		while(results.next()) {
			int id = results.getInt(1);
			String name = results.getString(2);
			int num_company = results.getInt(5);
			LocalDate date= LocalDate.of(2020,1,1);
			LocalDate date2 = LocalDate.of(2020,1,1);
			
			if (results.getDate(3) != null) {
				date = results.getDate(3).toLocalDate();
			}
			if (results.getDate(4) != null) {
				date2 = results.getDate(4).toLocalDate();
			}
			
			System.out.println(id+" "+name+" "+num_company+" "+date+" "+date2);
		}
		
		}
		catch(Exception e)
			{System.out.println(e+"exception due a la requete");
		}
	}
	
	public void afficherListeCompagnies() {
		String query = "SELECT * FROM company;";
		ResultSet results;
		try {
			Statement stmt = this.con.createStatement();
			results = stmt.executeQuery(query);
		while(results.next()) {
			int id = results.getInt(1);
			String name = results.getString(2);
			System.out.println(id+" "+name);
		}
		
		}
		catch(Exception e)
			{System.out.println(e+"exception due a la requete");
		}
	}
	
	public void afficherUnComputer(int id) {
		
		String query = "SELECT * FROM computer WHERE id="+id+";";
		ResultSet results;
		try {
			Statement stmt = this.con.createStatement();
			results = stmt.executeQuery(query);
			int idres;
			String name;
			while (results.next()) {
				idres = results.getInt(1);
				name = results.getString(2);
				System.out.println(idres +" "+name);
			}
		}
		catch(Exception e)
			{System.out.println(e+"exception due a la requete");
		}

	}
	
	// Gérer les dates fixer à null pour l'ajout
	public void ajouterUnComputer(Computer c) {
	
		String name = c.getName();
		LocalDate date_entree_pc = c.getEntryDate();
		LocalDate date_sortie_pc=c.getOutDate();
		int company_id = c.getIdCompany();
		try {
			PreparedStatement ps = this.con.prepareStatement(AJOUT_ONE_COMPUTER);
			
			ps.setString(1,name);
			ps.setDate(2, Date.valueOf(date_entree_pc));
			ps.setDate(3, Date.valueOf(date_sortie_pc));			
			ps.setInt(4, company_id);
			
			ps.executeUpdate();
			
		}
		catch(Exception e)
			{System.out.println(e+"exception due a la requete");
		}
	}
		
	
	// Cette méthode permet d'update un computer selon son id, on doit séletionner le champ à changer et sa valeur.
	// A Ajouter : test sur les classes des chhamps
	
	public void updateComputer(int id, int colonne, Object value) {
		
		
		switch(colonne) {
		
		// Si l'utilisateur veut changer le nom
		case 2:{
			try {
				PreparedStatement ps = this.con.prepareStatement(UPDATE_COMPUTER_NAME);
				ps.setString(1, (String)value);
				ps.setInt(2,id);
				ps.executeUpdate();
				
			}
			catch(Exception e){
				System.out.println(e+"exception due a la requete");
			}
			break;
		}
		case 3:{
			try {
				PreparedStatement ps = this.con.prepareStatement(UPDATE_COMPUTER_INTRODUCED);
				ps.setDate(1, Date.valueOf((LocalDate)value));
				ps.setInt(2,id);
				ps.executeUpdate();
				
			}
			catch(Exception e){
				System.out.println(e+"exception due a la requete");
			}
			break;
		}
		case 4:{
			try {
				PreparedStatement ps = this.con.prepareStatement(UPDATE_COMPUTER_DISCONTINUED);
				ps.setDate(1, Date.valueOf((LocalDate)value));
				ps.setInt(2,id);
				ps.executeUpdate();
				
			}
			catch(Exception e){
				System.out.println(e+"exception due a la requete");
			}
			break;
		}
		case 5:{
			try {
				PreparedStatement ps = this.con.prepareStatement(UPDATE_COMPUTER_COMPANY_ID);
				ps.setInt(1, (int)value);
				ps.setInt(2,id);
				ps.executeUpdate();
				
			}
			catch(Exception e){
				System.out.println(e+"exception due a la requete");
			}
			break;
		}	
		}
	}
	
	public void deleteComputer(int id) {
		
		try {
			PreparedStatement ps = this.con.prepareStatement(DELETE_COMPUTER);
			ps.setInt(1,id);
			ps.executeUpdate();
		}
		catch(Exception e){
			System.out.println(e+"exception due a la requete");
		}
		 
	}
		
			
		
}

