package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.excilys.model.*;

public class Mapper {
	
	public Mapper() {
		
	}
	
	public List<Computer> dataToListComputer(ResultSet data) throws SQLException{
		
		List<Computer> Computers = new ArrayList<>();
		
		while(data.next()) {
			int id = data.getInt(1);
			String name = data.getString(2);
			int num_company = data.getInt(5);
			LocalDate date= LocalDate.of(2019,1,1);
			LocalDate date2 = LocalDate.of(2020,1,1);
			
			if (data.getDate(3) != null) {
				date = data.getDate(3).toLocalDate();
			}
			if (data.getDate(4) != null) {
				date2 = data.getDate(4).toLocalDate();
			}
			Computers.add(new Computer(id,name,date,date2,num_company));
		}
		return Computers;
	}
	
	public List<Company> dataToListCompany(ResultSet data) throws SQLException{
		
		List<Company> Companies = new ArrayList<>();
		while(data.next()) {
			int id = data.getInt(1);
			String name = data.getString(2);
			Companies.add(new Company(id,name));
		}
		return Companies;	
	}
	
	public Computer dataToComputer(ResultSet results) throws SQLException{
		
		int id1=0;
		String name="000";
		int num_company=0;
		LocalDate date=LocalDate.of(2019,1,1);
		LocalDate date2=LocalDate.of(2020,1,1);;
		
		while(results.next()) {
			id1 = results.getInt(1);
			name = results.getString(2);
			num_company = results.getInt(5);
			
			
			if (results.getDate(3) != null) {
				date = results.getDate(3).toLocalDate();
			}
			if (results.getDate(4) != null) {
				date2 = results.getDate(4).toLocalDate();
			}
		}
		return new Computer(id1,name,date,date2,num_company);
		
	}
	
	public Computer createComputer(String name,LocalDate date1,LocalDate date2,int id_comp) {
		return new Computer(name,date1,date2,id_comp);	
	}
		

}