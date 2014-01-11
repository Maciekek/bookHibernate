package com.example.bookHibernate.service;

import java.util.List;

import com.example.bookHibernate.domain.PartOfSpeech;
import com.example.bookHibernate.domain.Word;

public interface WordManager {
	
	void addWord(Word word);
	List<Word> getAllWord();
	List<Word> findWordByMianownik(String mianownik);
	void deleteWordById(Word word);
	Word getRecordById(Word word);
	void updateMianownikByID(Word word, String newValue);
	void updateDopelniaczByID(Word word, String newValue);
	void updateWolaczByID(Word word, String newValue);
	void updateByID(Word word);
	
	Long addPartOfSpeech(PartOfSpeech pos);
	PartOfSpeech findPosById(Long id);
	void makeRelation(Long wordId, Long posId);
	List<PartOfSpeech> getWordFromPartOfSpeech( Word word);
	PartOfSpeech getPartOfSpeechById(PartOfSpeech pos);
	void updatePartOfSpeech(PartOfSpeech pos);
	List<PartOfSpeech> getAllPartOfSpeech();
	List<PartOfSpeech> getPartOfSpeechByRzeczownik(String rzeczownik);
	void deletePartOfSpeechById(PartOfSpeech partOfSpeech);
	void deleteWordWithAllPartOfSpeech(Word word);

	void clearAddedDB();
}
