package com.example.bookHibernate.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name = "partOfSpeech.all", query = "Select p from PartOfSpeech p"),
	@NamedQuery(name = "partOfSpeech.byId", query = "Select p from PartOfSpeech p where p.id = :id"),
	@NamedQuery(name = "partOfSpeech.byRzeczownik", query = "Select p from PartOfSpeech p where p.rzeczownik = :rzeczownik")
})
public class PartOfSpeech {
	private Long id;
	private String rzeczownik;
	private String czasownik;
	private String przymiotnik;	
	
	public PartOfSpeech() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PartOfSpeech(Long id, String rzeczownik, String czasownik,
			String przymiotnik) {
		super();
		this.id = id;
		this.rzeczownik = rzeczownik;
		this.czasownik = czasownik;
		this.przymiotnik = przymiotnik;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRzeczownik() {
		return rzeczownik;
	}
	public void setRzeczownik(String rzeczownik) {
		this.rzeczownik = rzeczownik;
	}
	public String getCzasownik() {
		return czasownik;
	}
	public void setCzasownik(String czasownik) {
		this.czasownik = czasownik;
	}
	public String getPrzymiotnik() {
		return przymiotnik;
	}
	public void setPrzymiotnik(String przymiotnik) {
		this.przymiotnik = przymiotnik;
	}
	
	
	

}
