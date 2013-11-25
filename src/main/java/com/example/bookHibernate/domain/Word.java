package com.example.bookHibernate.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

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
	
	private List<PartOfSpeech> pos = new ArrayList<PartOfSpeech>();

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
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
	@OneToMany(cascade = CascadeType.ALL, fetch =FetchType.LAZY)
	public List<PartOfSpeech> getPos() {
		return pos;
	}

	public void setPos(List<PartOfSpeech> pos) {
		this.pos = pos;
	}
	
	
	
}
