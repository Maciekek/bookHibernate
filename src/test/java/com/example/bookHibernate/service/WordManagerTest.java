package com.example.bookHibernate.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.bookHibernate.domain.Word;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class WordManagerTest {
	@Autowired
	WordManager wordManager;
	private final String MIANOWNIK_1 = "rower";
	private final String DOPELNIACZ_1 = "rowerem";
	private final String WOLACZ_1 = "o rower!";

	private final String MIANOWNIK_2 = "samochod";
	private final String DOPELNIACZ_2 = "samochodem";
	private final String WOLACZ_2 = "o samochod!";

	private final String MIANOWNIK_3 = "motor";
	private final String DOPELNIACZ_3 = "motorem";
	private final String WOLACZ_3 = "o motor!";

	@Test
	public void checkAddWord() {

		Word word = new Word();
		word.setMianownik(MIANOWNIK_1);
		word.setDopelniacz(DOPELNIACZ_1);
		word.setWolacz(WOLACZ_1);
		int count = wordManager.getAllWord().size();
		wordManager.addWord(word);
		
		Word retrievedWord = wordManager.getRecordById(word);
		
		assertEquals(count + 1, wordManager.getAllWord().size());
		assertEquals(MIANOWNIK_1, retrievedWord.getMianownik());
		assertEquals(DOPELNIACZ_1, retrievedWord.getDopelniacz());
		assertEquals(WOLACZ_1, retrievedWord.getWolacz());

	}

	@Test
	public void checkGetRecordByMianownik() {

		Word word = new Word();
		word.setMianownik(MIANOWNIK_1);
		word.setDopelniacz(DOPELNIACZ_1);
		word.setWolacz(WOLACZ_1);

		wordManager.addWord(word);

		Word retrievedWord = wordManager.findWordByMianownik(MIANOWNIK_1);
		assertEquals(MIANOWNIK_1, retrievedWord.getMianownik());
		assertEquals(DOPELNIACZ_1, retrievedWord.getDopelniacz());
		assertEquals(WOLACZ_1, retrievedWord.getWolacz());

	}

	@Test
	public void checkGetByIdWhen1InDB() {

		Word word = new Word();
		word.setMianownik(MIANOWNIK_1);
		word.setDopelniacz(DOPELNIACZ_1);
		word.setWolacz(WOLACZ_1);

		wordManager.addWord(word);

		Word retrievedWord = wordManager.getRecordById(word);
		assertEquals(MIANOWNIK_1, retrievedWord.getMianownik());
		assertEquals(DOPELNIACZ_1, retrievedWord.getDopelniacz());
		assertEquals(WOLACZ_1, retrievedWord.getWolacz());

	}

	@Test
	public void checkGetByIdWhen2InDB() {
		//Word 1
		Word word = new Word();
		word.setMianownik(MIANOWNIK_1);
		word.setDopelniacz(DOPELNIACZ_1);
		word.setWolacz(WOLACZ_1);
		//Word2
		Word word2 = new Word();
		word2.setMianownik(MIANOWNIK_2);
		word2.setDopelniacz(DOPELNIACZ_2);
		word2.setWolacz(WOLACZ_2);

		wordManager.addWord(word);
		wordManager.addWord(word2);

		Word retrievedWord = wordManager.getRecordById(word2);
		assertEquals(MIANOWNIK_2, retrievedWord.getMianownik());
		assertEquals(DOPELNIACZ_2, retrievedWord.getDopelniacz());
		assertEquals(WOLACZ_2, retrievedWord.getWolacz());

	}

	@Test
	public void checkGetAllWord() {

		Word word = new Word();
		word.setMianownik(MIANOWNIK_1);
		word.setDopelniacz(DOPELNIACZ_1);
		word.setWolacz(WOLACZ_1);
		Word word2 = new Word();
		word2.setMianownik(MIANOWNIK_2);
		word2.setDopelniacz(DOPELNIACZ_2);
		word2.setWolacz(WOLACZ_2);

		wordManager.addWord(word);
		wordManager.addWord(word2);

		List<Word> retrievedWords = wordManager.getAllWord();
		Word wordRetrieved1 = retrievedWords.get(0);
		Word wordRetrieved2 = retrievedWords.get(1);
		assertEquals(MIANOWNIK_1, wordRetrieved1.getMianownik());
		assertEquals(DOPELNIACZ_1, wordRetrieved1.getDopelniacz());
		assertEquals(WOLACZ_1, wordRetrieved1.getWolacz());
		assertEquals(MIANOWNIK_2, wordRetrieved2.getMianownik());
		assertEquals(DOPELNIACZ_2, wordRetrieved2.getDopelniacz());
		assertEquals(WOLACZ_2, wordRetrieved2.getWolacz());
	}

	@Test
	public void checkDeleteWordByIdWhen3InDB() {
		// Word 1
		Word word1 = new Word();
		word1.setMianownik(MIANOWNIK_1);
		word1.setDopelniacz(DOPELNIACZ_1);
		word1.setWolacz(WOLACZ_1);

		// Word 2
		Word word2 = new Word();
		word2.setMianownik(MIANOWNIK_2);
		word2.setDopelniacz(DOPELNIACZ_2);
		word2.setWolacz(WOLACZ_2);

		// Word 3
		Word word3 = new Word();
		word3.setMianownik(MIANOWNIK_3);
		word3.setDopelniacz(DOPELNIACZ_3);
		word3.setWolacz(WOLACZ_3);

		wordManager.addWord(word1);
		wordManager.addWord(word2);
		wordManager.addWord(word3);
		wordManager.deleteWordById(word1);

		List<Word> retrievedWords = wordManager.getAllWord();
		Word retrievedWord = retrievedWords.get(0);
		Word retrievedWord1 = retrievedWords.get(1);

		assertEquals(2, retrievedWords.size());
		assertEquals(MIANOWNIK_2, retrievedWord.getMianownik());
		assertEquals(DOPELNIACZ_2, retrievedWord.getDopelniacz());
		assertEquals(WOLACZ_2, retrievedWord.getWolacz());
		assertEquals(MIANOWNIK_3, retrievedWord1.getMianownik());
		assertEquals(DOPELNIACZ_3, retrievedWord1.getDopelniacz());
		assertEquals(WOLACZ_3, retrievedWord1.getWolacz());

	}

	@Test
	public void checkDeleteWordByIdWhen2InDB() {
		// Word 1
		Word word1 = new Word();
		word1.setMianownik(MIANOWNIK_1);
		word1.setDopelniacz(DOPELNIACZ_1);
		word1.setWolacz(WOLACZ_1);

		// Word 2
		Word word2 = new Word();
		word2.setMianownik(MIANOWNIK_2);
		word2.setDopelniacz(DOPELNIACZ_2);
		word2.setWolacz(WOLACZ_2);

		wordManager.addWord(word1);
		wordManager.addWord(word2);
		
		int count = wordManager.getAllWord().size();
		wordManager.deleteWordById(word1);
		
		List<Word> retrievedWords = wordManager.getAllWord();
		Word retrievedWord = retrievedWords.get(0);

		assertEquals(count-1, retrievedWords.size());
		assertEquals(MIANOWNIK_2, retrievedWord.getMianownik());
		assertEquals(DOPELNIACZ_2, retrievedWord.getDopelniacz());
		assertEquals(WOLACZ_2, retrievedWord.getWolacz());

	}

	@Test
	public void checkDeleteWordByIdWhen1InDB() {
		// Word 1
		Word word1 = new Word();
		word1.setMianownik(MIANOWNIK_1);
		word1.setDopelniacz(DOPELNIACZ_1);
		word1.setWolacz(WOLACZ_1);

		wordManager.addWord(word1);
		wordManager.deleteWordById(word1);
		List<Word> retrievedWords = wordManager.getAllWord();

		assertEquals(0, retrievedWords.size());
	}

	@Test
	public void checkDeleteWord_FIND_VERSION() {
		boolean isNotExist = true;
		// Word 1
		Word word1 = new Word();
		word1.setMianownik(MIANOWNIK_1);
		word1.setDopelniacz(DOPELNIACZ_1);
		word1.setWolacz(WOLACZ_1);

		// Word 2
		Word word2 = new Word();
		word2.setMianownik(MIANOWNIK_2);
		word2.setDopelniacz(DOPELNIACZ_2);
		word2.setWolacz(WOLACZ_2);

		// Word 3
		Word word3 = new Word();
		word3.setMianownik(MIANOWNIK_3);
		word3.setDopelniacz(DOPELNIACZ_3);
		word3.setWolacz(WOLACZ_3);

		wordManager.addWord(word1);
		wordManager.addWord(word2);
		wordManager.addWord(word3);
		wordManager.deleteWordById(word1);

		List<Word> retrievedWord = wordManager.getAllWord();
		for(Word word : retrievedWord){
			if(word.getMianownik().equals(MIANOWNIK_1))
				isNotExist = false;
		}
		
		assertTrue(isNotExist);

	}

	@Test
	public void checkUpdateByMianownik() {
		// Word 1
		Word word1 = new Word();
		word1.setMianownik(MIANOWNIK_1);
		word1.setDopelniacz(DOPELNIACZ_1);
		word1.setWolacz(WOLACZ_1);

		wordManager.addWord(word1);

		wordManager.updateMianownikByID(word1, MIANOWNIK_2);

		Word retrievedWord = wordManager.getRecordById(word1);
		assertEquals(MIANOWNIK_2, retrievedWord.getMianownik());
		assertEquals(DOPELNIACZ_1, retrievedWord.getDopelniacz());
		assertEquals(WOLACZ_1, retrievedWord.getWolacz());
	}

	@Test
	public void checkUpdateByMianownikWhen3InDB() {
		// Word 1
		Word word1 = new Word();
		word1.setMianownik(MIANOWNIK_1);
		word1.setDopelniacz(DOPELNIACZ_1);
		word1.setWolacz(WOLACZ_1);

		// Word 2
		Word word2 = new Word();
		word2.setMianownik(MIANOWNIK_2);
		word2.setDopelniacz(DOPELNIACZ_2);
		word2.setWolacz(WOLACZ_2);

		// Word 3
		Word word3 = new Word();
		word3.setMianownik(MIANOWNIK_3);
		word3.setDopelniacz(DOPELNIACZ_3);
		word3.setWolacz(WOLACZ_3);

		wordManager.addWord(word1);
		wordManager.addWord(word2);
		wordManager.addWord(word3);

		wordManager.updateMianownikByID(word1, "MIANOWNIK_ZMIENIONY");

		List<Word> retrievedWords = wordManager.getAllWord();
		Word retrievedSingleWord1 = retrievedWords.get(0);
		Word retrievedSingleWord2 = retrievedWords.get(1);
		Word retrievedSingleWord3 = retrievedWords.get(2);

		assertEquals(3, retrievedWords.size());

		assertEquals("MIANOWNIK_ZMIENIONY", retrievedSingleWord1.getMianownik());
		assertEquals(DOPELNIACZ_1, retrievedSingleWord1.getDopelniacz());
		assertEquals(WOLACZ_1, retrievedSingleWord1.getWolacz());
		assertEquals(MIANOWNIK_2, retrievedSingleWord2.getMianownik());
		assertEquals(DOPELNIACZ_2, retrievedSingleWord2.getDopelniacz());
		assertEquals(WOLACZ_2, retrievedSingleWord2.getWolacz());
		assertEquals(MIANOWNIK_3, retrievedSingleWord3.getMianownik());
		assertEquals(DOPELNIACZ_3, retrievedSingleWord3.getDopelniacz());
		assertEquals(WOLACZ_3, retrievedSingleWord3.getWolacz());
	}

	@Test
	public void checkUpdateByDopelniacz() {
		// Word 1
		Word word1 = new Word();
		word1.setMianownik(MIANOWNIK_1);
		word1.setDopelniacz(DOPELNIACZ_1);
		word1.setWolacz(WOLACZ_1);

		wordManager.addWord(word1);

		wordManager.updateDopelniaczByID(word1, DOPELNIACZ_2);

		Word retrievedWord = wordManager.getRecordById(word1);
		assertEquals(MIANOWNIK_1, retrievedWord.getMianownik());
		assertEquals(DOPELNIACZ_2, retrievedWord.getDopelniacz());
		assertEquals(WOLACZ_1, retrievedWord.getWolacz());
	}

	@Test
	public void checkUpdateByDopelniaczWhen3InDB() {
		// Word 1
		Word word1 = new Word();
		word1.setMianownik(MIANOWNIK_1);
		word1.setDopelniacz(DOPELNIACZ_1);
		word1.setWolacz(WOLACZ_1);

		// Word 2
		Word word2 = new Word();
		word2.setMianownik(MIANOWNIK_2);
		word2.setDopelniacz(DOPELNIACZ_2);
		word2.setWolacz(WOLACZ_2);

		// Word 3
		Word word3 = new Word();
		word3.setMianownik(MIANOWNIK_3);
		word3.setDopelniacz(DOPELNIACZ_3);
		word3.setWolacz(WOLACZ_3);

		wordManager.addWord(word1);
		wordManager.addWord(word2);
		wordManager.addWord(word3);

		wordManager.updateDopelniaczByID(word2, "DOPELNIACZ_ZMIENIONY");

		List<Word> retrievedWords = wordManager.getAllWord();
		Word retrievedSingleWord1 = retrievedWords.get(0);
		Word retrievedSingleWord2 = retrievedWords.get(1);
		Word retrievedSingleWord3 = retrievedWords.get(2);

		assertEquals(3, retrievedWords.size());

		assertEquals(MIANOWNIK_1, retrievedSingleWord1.getMianownik());
		assertEquals(DOPELNIACZ_1, retrievedSingleWord1.getDopelniacz());
		assertEquals(WOLACZ_1, retrievedSingleWord1.getWolacz());
		assertEquals(MIANOWNIK_2, retrievedSingleWord2.getMianownik());
		assertEquals("DOPELNIACZ_ZMIENIONY",
				retrievedSingleWord2.getDopelniacz());
		assertEquals(WOLACZ_2, retrievedSingleWord2.getWolacz());
		assertEquals(MIANOWNIK_3, retrievedSingleWord3.getMianownik());
		assertEquals(DOPELNIACZ_3, retrievedSingleWord3.getDopelniacz());
		assertEquals(WOLACZ_3, retrievedSingleWord3.getWolacz());
	}

	@Test
	public void checkUpdateByWolacz() {
		// Word 1
		Word word1 = new Word();
		word1.setMianownik(MIANOWNIK_1);
		word1.setDopelniacz(DOPELNIACZ_1);
		word1.setWolacz(WOLACZ_1);

		wordManager.addWord(word1);

		wordManager.updateWolaczByID(word1, WOLACZ_2);

		Word retrievedWord = wordManager.getRecordById(word1);
		assertEquals(MIANOWNIK_1, retrievedWord.getMianownik());
		assertEquals(DOPELNIACZ_1, retrievedWord.getDopelniacz());
		assertEquals(WOLACZ_2, retrievedWord.getWolacz());
	}

	@Test
	public void checkUpdateByWolaczWhen3InDB() {
		// Word 1
		Word word1 = new Word();
		word1.setMianownik(MIANOWNIK_1);
		word1.setDopelniacz(DOPELNIACZ_1);
		word1.setWolacz(WOLACZ_1);

		// Word 2
		Word word2 = new Word();
		word2.setMianownik(MIANOWNIK_2);
		word2.setDopelniacz(DOPELNIACZ_2);
		word2.setWolacz(WOLACZ_2);

		// Word 3
		Word word3 = new Word();
		word3.setMianownik(MIANOWNIK_3);
		word3.setDopelniacz(DOPELNIACZ_3);
		word3.setWolacz(WOLACZ_3);

		wordManager.addWord(word1);
		wordManager.addWord(word2);
		wordManager.addWord(word3);

		wordManager.updateWolaczByID(word3, "WOLACZ_ZMIENIONY");

		List<Word> retrievedWords = wordManager.getAllWord();
		Word retrievedSingleWord1 = retrievedWords.get(0);
		Word retrievedSingleWord2 = retrievedWords.get(1);
		Word retrievedSingleWord3 = retrievedWords.get(2);

		assertEquals(3, retrievedWords.size());

		assertEquals(MIANOWNIK_1, retrievedSingleWord1.getMianownik());
		assertEquals(DOPELNIACZ_1, retrievedSingleWord1.getDopelniacz());
		assertEquals(WOLACZ_1, retrievedSingleWord1.getWolacz());
		assertEquals(MIANOWNIK_2, retrievedSingleWord2.getMianownik());
		assertEquals(DOPELNIACZ_2, retrievedSingleWord2.getDopelniacz());
		assertEquals(WOLACZ_2, retrievedSingleWord2.getWolacz());
		assertEquals(MIANOWNIK_3, retrievedSingleWord3.getMianownik());
		assertEquals(DOPELNIACZ_3, retrievedSingleWord3.getDopelniacz());
		assertEquals("WOLACZ_ZMIENIONY", retrievedSingleWord3.getWolacz());
	}

	 @Test
	 public void checkUpdateById() {
	 // Word 1
	 Word word1 = new Word();
	 word1.setMianownik(MIANOWNIK_1);
	 word1.setDopelniacz(DOPELNIACZ_1);
	 word1.setWolacz(WOLACZ_1);
	
	 wordManager.addWord(word1);
	 
	 word1.setMianownik(MIANOWNIK_2);
	 word1.setDopelniacz(DOPELNIACZ_2);
	 word1.setWolacz(WOLACZ_2);
	 
	 wordManager.updateByID(word1);
	 
	 Word retrievedWord = wordManager.getRecordById(word1);
	 assertEquals(MIANOWNIK_2, retrievedWord.getMianownik());
	 assertEquals(DOPELNIACZ_2, retrievedWord.getDopelniacz());
	 assertEquals(WOLACZ_2, retrievedWord.getWolacz());
	
	
	 }
}
