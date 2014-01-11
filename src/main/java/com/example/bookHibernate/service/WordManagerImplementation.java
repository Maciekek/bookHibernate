package com.example.bookHibernate.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.bookHibernate.domain.PartOfSpeech;
import com.example.bookHibernate.domain.Word;

@Component
@Transactional
public class WordManagerImplementation implements WordManager {

	List<Word> wordsAdded = new ArrayList<Word>();

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
		wordsAdded.add(word);
	}

	@SuppressWarnings("unchecked")
	public List<Word> getAllWord() {
		return sessionFactory.getCurrentSession().getNamedQuery("word.all")
				.list();
	}

	@SuppressWarnings("unchecked")
	public List<Word> findWordByMianownik(String mianownik) {
		return (List<Word>) sessionFactory.getCurrentSession()
				.getNamedQuery("word.byMianownik")
				.setString("mianownik", mianownik).list();

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

	public Long addPartOfSpeech(PartOfSpeech pos) {
		pos.setId(null);
		return (Long) sessionFactory.getCurrentSession().save(pos);

	}

	public PartOfSpeech findPosById(Long id) {

		return (PartOfSpeech) sessionFactory.getCurrentSession().get(
				PartOfSpeech.class, id);
	}

	public void makeRelation(Long wordId, Long posId) {
		Word word = (Word) sessionFactory.getCurrentSession().get(Word.class,
				wordId);
		PartOfSpeech pos = (PartOfSpeech) sessionFactory.getCurrentSession()
				.get(PartOfSpeech.class, posId);

		word.getPos().add(pos);
	}

	public List<PartOfSpeech> getWordFromPartOfSpeech(Word word) {
		List<PartOfSpeech> poses = new ArrayList<PartOfSpeech>(word.getPos());
		return poses;
	}

	public PartOfSpeech getPartOfSpeechById(PartOfSpeech pos) {
		return (PartOfSpeech) sessionFactory.getCurrentSession()
				.getNamedQuery("partOfSpeech.byId").setLong("id", pos.getId())
				.uniqueResult();

	}

	public void updatePartOfSpeech(PartOfSpeech pos) {
		sessionFactory.getCurrentSession().update(pos);
		
	}

	@SuppressWarnings("unchecked")
	public List<PartOfSpeech> getAllPartOfSpeech() {
		return sessionFactory.getCurrentSession().getNamedQuery("partOfSpeech.all").list();
	}

	@SuppressWarnings("unchecked")
	public List<PartOfSpeech> getPartOfSpeechByRzeczownik(String rzeczownik) {
		return  (List<PartOfSpeech>) sessionFactory.getCurrentSession()
				.getNamedQuery("partOfSpeech.byRzeczownik")
				.setString("rzeczownik", rzeczownik).list();

	}

	public void deletePartOfSpeechById(PartOfSpeech partOfSpeech) {
		sessionFactory.getCurrentSession().delete(partOfSpeech);
		
	}

	public void deleteWordWithAllPartOfSpeech(Word word) {
		List<PartOfSpeech> allPoses = getAllPartOfSpeech();
		
		for (PartOfSpeech pos : allPoses) {
			for (PartOfSpeech singlePos : word.getPos())
				if (singlePos.getId().equals(pos.getId())){
					sessionFactory.getCurrentSession().delete(pos);
				}
		}
		
		
		word.getPos().clear();
		
	}

	public void clearAddedDB() {
		for (Word wordAdded : wordsAdded) {
			try {
				deleteWordById(wordAdded);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		wordsAdded.clear();
	}
}
