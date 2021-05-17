package com.excilys.model;

import java.time.LocalDate;

public class Computer {
	
	private int id;
	private String name;
	private int company_id;
	private LocalDate dateEntree;
	private LocalDate dateSortie;
	
	public Computer(int id,String name,LocalDate dateEntree, LocalDate dateSortie,int company_id) {
		
		this.id=id;
		this.name = name;
		this.company_id = company_id;
		
		if (dateEntree.isBefore(dateSortie)){
			this.dateEntree=dateEntree;
			this.dateSortie=dateSortie;
		}
		else {
			this.dateEntree=null;
			this.dateSortie=null;
		}
	}
	
	public Computer(String name,LocalDate dateEntree, LocalDate dateSortie,int company_id) {
		
		this.name = name;
		this.company_id = company_id;
		this.dateEntree=dateEntree;
		this.dateSortie=dateSortie;
	
	}
	
	
	
	@Override
	public String toString() {
		return this.id + " " + this.name+" "+this.dateEntree+" "+this.dateSortie+" "+this.company_id;
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
