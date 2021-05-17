package com.excilys.model;


public class Page {
	
	private final int NB_LIGNE=20;
	private int nb_element;
	private int numero_page;
	
	 
	
	 public Page(int nb_element) {
		   this.nb_element = nb_element;
		   this.numero_page = 1;
	 }

	public void getPagePrecedente() {
		this.numero_page--;
	}
	
	public void getPageSuivante() {
		this.numero_page++;
	}
	
	public void setNumPage(int num) {
		this.numero_page=num;
	}
	
	public int getNumeroPage() {
		return this.numero_page;
	}
	
	public int getMaxElement() {
		return this.nb_element;
	}
	
	public int getNBElementParPage() {
		return NB_LIGNE;
	}
	
	
	
	
}
