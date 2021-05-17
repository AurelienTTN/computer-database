package com.excilys.model;

import com.excilys.mapper.Mapper;
import com.excilys.persistence.Dao;
import com.excilys.service.Service;

public class Page {
	
	private static final int NB_LIGNE=20;
	private int nb_element;
	private int nb_page_max;
	
	private static Page instance;
	 
	
	 private Page(int nb_element) {

		        try {
		            Thread.sleep(1000);
		        } catch (InterruptedException ex) {
		            ex.printStackTrace();
		        }
		        this.nb_element = nb_element;
		        if((nb_element % NB_LIGNE) == 0)
		        	this.nb_page_max = nb_element/NB_LIGNE;
		        else
		        	this.nb_page_max = nb_element/NB_LIGNE + 1;
	 }


	public static Page getInstance(int nb_element) {
	    if (instance == null) {
	        instance = new Page(nb_element);
	    }
	    return instance;
	}
	
	public int[] getIndex(int num_page) {
		
		int index_1=num_page*NB_LIGNE-NB_LIGNE;
		int index_2=index_1+NB_LIGNE;
		if(index_2>nb_element) {
			index_2=nb_element;
		}
		int index[]= {index_1,index_2};
		return index;
	}
	
	

}
