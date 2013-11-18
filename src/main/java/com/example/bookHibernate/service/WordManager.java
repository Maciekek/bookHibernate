package com.example.bookHibernate.service;

import java.util.List;

import com.example.bookHibernate.domain.Word;

public interface WordManager {
	
	void addWord(Word word);
	List<Word> getAllWord();
	Word findWordByMianownik(String mianownik);
	void deleteWordById(Word word);
	Word getRecordById(Word word);
	void updateMianownikByID(Word word, String newValue);
	void updateDopelniaczByID(Word word, String newValue);
	void updateWolaczByID(Word word, String newValue);
	void updateByID(Word word);
}
