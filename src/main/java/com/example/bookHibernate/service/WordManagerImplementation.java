package com.example.bookHibernate.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.bookHibernate.domain.Word;

@Component
@Transactional
public class WordManagerImplementation implements WordManager {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addWord(Word word) {
		word.setId(null);
		sessionFactory.getCurrentSession().persist(word);

	}

	@SuppressWarnings("unchecked")
	public List<Word> getAllWord() {
		return sessionFactory.getCurrentSession().getNamedQuery("word.all")
				.list();
	}

	public Word findWordByMianownik(String mianownik) {
		return (Word) sessionFactory.getCurrentSession()
				.getNamedQuery("word.byMianownik")
				.setString("mianownik", mianownik).uniqueResult();

	}

	public void deleteWordById(Word word) {
		sessionFactory.getCurrentSession().delete(word);

	}

	public Word getRecordById(Word word) {
		// word = (Word) sessionFactory.getCurrentSession().get(Word.class,
		// word.getId());
		return (Word) sessionFactory.getCurrentSession()
				.getNamedQuery("word.byId").setLong("id", word.getId())
				.uniqueResult();
	}

	public void updateMianownikByID(Word word, String newValue) {
		word.setMianownik(newValue);

		sessionFactory.getCurrentSession().update(word);
	}

	public void updateDopelniaczByID(Word word, String newValue) {
		word.setDopelniacz(newValue);

		sessionFactory.getCurrentSession().update(word);
	}

	public void updateWolaczByID(Word word, String newValue) {
		word.setWolacz(newValue);

		sessionFactory.getCurrentSession().update(word);
	}

	public void updateByID(Word word) {
		
		
		sessionFactory.getCurrentSession().update(word);
	}

}
