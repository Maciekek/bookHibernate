package com.example.bookHibernate.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name = "word.all", query = "SELECT w FROM Word w"),
	@NamedQuery(name = "word.byMianownik", query = "Select w from Word w where w.mianownik = :mianownik"),
	@NamedQuery(name = "word.byId", query = "Select w from Word w where w.id = :id")
	
})
public class Word {

	private Long id;
	private String mianownik = "";
	private String dopelniacz = "";
	private String wolacz = "";

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@Column(unique = true, nullable = false)
	public String getMianownik() {
		return mianownik;
	}
	public void setMianownik(String mianownik) {
		this.mianownik = mianownik;
	}
	public String getDopelniacz() {
		return dopelniacz;
	}
	public void setDopelniacz(String dopelniacz) {
		this.dopelniacz = dopelniacz;
	}
	public String getWolacz() {
		return wolacz;
	}
	public void setWolacz(String wolacz) {
		this.wolacz = wolacz;
	}
	
	
	
}
