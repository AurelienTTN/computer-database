package com.excilys.model;

import java.time.LocalDate;

public class Computer {
	
	private String name;
	private int company_id;
	private LocalDate dateEntree;
	private LocalDate dateSortie;
	
	public Computer(String name,int company_id,LocalDate dateEntree, LocalDate dateSortie) {
		this.name = name;
		this.company_id = company_id;
		
		if (dateEntree.isBefore(dateSortie)){
			this.dateEntree=dateEntree;
			this.dateSortie=dateSortie;
		}
		else {
			System.out.println("les dates ne sont pas bonnes, elles seront instanciées null.");
			this.dateEntree=null;
			this.dateSortie=null;
		}
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getIdCompany() {
		return this.company_id;
	}
	
	public LocalDate getEntryDate() {
		return this.dateEntree;
	}
	
	public LocalDate getOutDate() {
		return this.dateSortie;
	}
	
}
