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

import com.example.bookHibernate.domain.PartOfSpeech;
import com.example.bookHibernate.domain.Word;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
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
	
	private final String RZECZOWNIK_1 = "Rower";
	private final String CZASOWNIK_1 = "Jade Rowerem";
	private final String PRZYMIOTNIK_1 = "Bialy Rower";
	
	private final String RZECZOWNIK_2 = "Samochod";
	private final String CZASOWNIK_2 = "Jade Samochodem";
	private final String PRZYMIOTNIK_2 = "Czerwony samochod";
	
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
	 boolean isFound = false;
	 Word word = new Word();
	 word.setMianownik(MIANOWNIK_1);
	 word.setDopelniacz(DOPELNIACZ_1);
	 word.setWolacz(WOLACZ_1);
	
	 wordManager.addWord(word);
	
	 List<Word> words = wordManager.findWordByMianownik(MIANOWNIK_1);
		for(Word retrievedWord : words) {
			if (retrievedWord.getMianownik() == word.getMianownik())
				isFound = true;
		}
	 
	 assertTrue(isFound);
	 }

	@Test
	public void checkGetByIdWhen1InDB() {

		Word word = new Word();
		word.setMianownik(MIANOWNIK_2);
		word.setDopelniacz(DOPELNIACZ_2);
		word.setWolacz(WOLACZ_2);

		wordManager.addWord(word);

		Word retrievedWord = wordManager.getRecordById(word);
		assertEquals(MIANOWNIK_2, retrievedWord.getMianownik());
		assertEquals(DOPELNIACZ_2, retrievedWord.getDopelniacz());
		assertEquals(WOLACZ_2, retrievedWord.getWolacz());

	}

	@Test
	public void checkGetByIdWhen2InDB() {
		// Word 1
		Word word = new Word();
		word.setMianownik(MIANOWNIK_1);
		word.setDopelniacz(DOPELNIACZ_1);
		word.setWolacz(WOLACZ_1);
		// Word2
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
		int count = wordManager.getAllWord().size();
		wordManager.addWord(word);
		wordManager.addWord(word2);

		assertEquals(count+2, wordManager.getAllWord().size());
	}

	@Test
	public void checkDeleteWordByIdWhen3InDB() {
		boolean isExist = false;
		
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

		int count = wordManager.getAllWord().size();
		wordManager.addWord(word1);
		wordManager.addWord(word2);
		wordManager.addWord(word3);
		wordManager.deleteWordById(word1);

		List<Word> retrievedWords = wordManager.getAllWord();
		
		for (Word rWord : retrievedWords) {
			if (rWord.getId().equals(word1.getId())) {
				isExist = true;
			}
		}
		assertEquals(count + 2, retrievedWords.size());
		assertFalse(isExist);
	}

	@Test
	public void checkDeleteWord_FIND_VERSION() {
		boolean isExist = false;
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
		for (Word rWord : retrievedWord) {
			if (rWord.getMianownik().equals(word1.getId()))
				isExist = true;
		}

		assertFalse(isExist);

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


		Word retrievedWord1 = wordManager.getRecordById(word1);
		Word retrievedWord2 = wordManager.getRecordById(word2);
		Word retrievedWord3 = wordManager.getRecordById(word3);
		


		assertEquals("MIANOWNIK_ZMIENIONY", retrievedWord1.getMianownik());
		assertEquals(DOPELNIACZ_1, retrievedWord1.getDopelniacz());
		assertEquals(WOLACZ_1, retrievedWord1.getWolacz());
		assertEquals(MIANOWNIK_2, retrievedWord2.getMianownik());
		assertEquals(DOPELNIACZ_2, retrievedWord2.getDopelniacz());
		assertEquals(WOLACZ_2, retrievedWord2.getWolacz());
		assertEquals(MIANOWNIK_3, retrievedWord3.getMianownik());
		assertEquals(DOPELNIACZ_3, retrievedWord3.getDopelniacz());
		assertEquals(WOLACZ_3, retrievedWord3.getWolacz());
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

		Word retrievedWord1 = wordManager.getRecordById(word1);
		Word retrievedWord2 = wordManager.getRecordById(word2);
		Word retrievedWord3 = wordManager.getRecordById(word3);
		

		assertEquals(MIANOWNIK_1, retrievedWord1.getMianownik());
		assertEquals(DOPELNIACZ_1, retrievedWord1.getDopelniacz());
		assertEquals(WOLACZ_1, retrievedWord1.getWolacz());
		assertEquals(MIANOWNIK_2, retrievedWord2.getMianownik());
		assertEquals("DOPELNIACZ_ZMIENIONY",
				retrievedWord2.getDopelniacz());
		assertEquals(WOLACZ_2, retrievedWord2.getWolacz());
		assertEquals(MIANOWNIK_3, retrievedWord3.getMianownik());
		assertEquals(DOPELNIACZ_3, retrievedWord3.getDopelniacz());
		assertEquals(WOLACZ_3, retrievedWord3.getWolacz());
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

		Word retrievedWord1 = wordManager.getRecordById(word1);
		Word retrievedWord2 = wordManager.getRecordById(word2);
		Word retrievedWord3 = wordManager.getRecordById(word3);
		assertEquals(MIANOWNIK_1, retrievedWord1.getMianownik());
		assertEquals(DOPELNIACZ_1, retrievedWord1.getDopelniacz());
		assertEquals(WOLACZ_1, retrievedWord1.getWolacz());
		assertEquals(MIANOWNIK_2, retrievedWord2.getMianownik());
		assertEquals(DOPELNIACZ_2, retrievedWord2.getDopelniacz());
		assertEquals(WOLACZ_2, retrievedWord2.getWolacz());
		assertEquals(MIANOWNIK_3, retrievedWord3.getMianownik());
		assertEquals(DOPELNIACZ_3, retrievedWord3.getDopelniacz());
		assertEquals("WOLACZ_ZMIENIONY", retrievedWord3.getWolacz());
	}

	@Test
	public void checkUpdateByIdWord() {
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
	
	@Test
	public void checkAddPartOfSpeech() {
		PartOfSpeech pos = new PartOfSpeech();
		pos.setRzeczownik(RZECZOWNIK_1);
		pos.setCzasownik(CZASOWNIK_1);
		pos.setPrzymiotnik(PRZYMIOTNIK_1);
		
		Long posID = wordManager.addPartOfSpeech(pos);
		
		PartOfSpeech retrievedPos = wordManager.findPosById(posID);
		assertEquals(RZECZOWNIK_1, retrievedPos.getRzeczownik());
		assertEquals(CZASOWNIK_1, retrievedPos.getCzasownik());
		assertEquals(PRZYMIOTNIK_1, retrievedPos.getPrzymiotnik());
	}
	
	@Test
	public void checkMakeRelation() {
		Word word1 = new Word();
		word1.setMianownik(MIANOWNIK_1);
		word1.setDopelniacz(DOPELNIACZ_1);
		word1.setWolacz(WOLACZ_1);

		wordManager.addWord(word1);
		Word retrievedWord = wordManager.getRecordById(word1);
		
		PartOfSpeech pos = new PartOfSpeech();
		pos.setRzeczownik(RZECZOWNIK_1);
		pos.setCzasownik(CZASOWNIK_1);
		pos.setPrzymiotnik(PRZYMIOTNIK_1);

		
		Long posID = wordManager.addPartOfSpeech(pos);
		
		wordManager.makeRelation(retrievedWord.getId(), posID);
	}
	
	@Test
	public void checkGetPartOfSpeechFromWord() {
		Word word1 = new Word();
		word1.setMianownik(MIANOWNIK_1);
		word1.setDopelniacz(DOPELNIACZ_1);
		word1.setWolacz(WOLACZ_1);

		wordManager.addWord(word1);
		Word retrievedWord = wordManager.getRecordById(word1);
		
		PartOfSpeech pos = new PartOfSpeech();
		pos.setRzeczownik(RZECZOWNIK_1);
		pos.setCzasownik(CZASOWNIK_1);
		pos.setPrzymiotnik(PRZYMIOTNIK_1);
		Long posID = wordManager.addPartOfSpeech(pos);
		PartOfSpeech pos2 = new PartOfSpeech();
		pos2.setRzeczownik(RZECZOWNIK_2);
		pos2.setCzasownik(CZASOWNIK_2);
		pos2.setPrzymiotnik(PRZYMIOTNIK_2);
		Long posID2 = wordManager.addPartOfSpeech(pos2);
		
		
		wordManager.makeRelation(retrievedWord.getId(), posID);
		wordManager.makeRelation(retrievedWord.getId(), posID2);
		
		List<PartOfSpeech> retrievedPoses = wordManager.getWordFromPartOfSpeech(word1);
		PartOfSpeech retrievedPos1 = retrievedPoses.get(0);
		PartOfSpeech retrievedPos2 = retrievedPoses.get(1);
		
		assertEquals(RZECZOWNIK_1, retrievedPos1.getRzeczownik());
		assertEquals(CZASOWNIK_1, retrievedPos1.getCzasownik());
		assertEquals(PRZYMIOTNIK_1, retrievedPos1.getPrzymiotnik());
		assertEquals(RZECZOWNIK_2, retrievedPos2.getRzeczownik());
		assertEquals(CZASOWNIK_2, retrievedPos2.getCzasownik());
		assertEquals(PRZYMIOTNIK_2, retrievedPos2.getPrzymiotnik());
		
	}
	
	@Test
	public void checkGetPartOfSpeechById() {
		PartOfSpeech pos = new PartOfSpeech();
		pos.setRzeczownik(RZECZOWNIK_1);
		pos.setCzasownik(CZASOWNIK_1);
		pos.setPrzymiotnik(PRZYMIOTNIK_1);
		
		wordManager.addPartOfSpeech(pos);
		PartOfSpeech retrievedPos = wordManager.getPartOfSpeechById(pos);
		
		assertEquals(RZECZOWNIK_1, retrievedPos.getRzeczownik());
		assertEquals(CZASOWNIK_1, retrievedPos.getCzasownik());
		assertEquals(PRZYMIOTNIK_1, retrievedPos.getPrzymiotnik());
	}
	
	@Test
	public void checkUpdateByIdPartOfSpeech() {
		// Word 1
		PartOfSpeech pos = new PartOfSpeech();
		pos.setRzeczownik(RZECZOWNIK_1);
		pos.setCzasownik(CZASOWNIK_1);
		pos.setPrzymiotnik(PRZYMIOTNIK_1);

		wordManager.addPartOfSpeech(pos);
		
		pos.setRzeczownik(RZECZOWNIK_1);
		pos.setCzasownik(CZASOWNIK_2);
		pos.setPrzymiotnik(PRZYMIOTNIK_1);

		wordManager.updatePartOfSpeech(pos);;

		PartOfSpeech retrievedPos = wordManager.getPartOfSpeechById(pos);
		assertEquals(RZECZOWNIK_1, retrievedPos.getRzeczownik());
		assertEquals(CZASOWNIK_2, retrievedPos.getCzasownik());
		assertEquals(PRZYMIOTNIK_1, retrievedPos.getPrzymiotnik());

	}
	@Test 
	public void checkDeletePartOfSpeechByIdWhen3InDB() {
		boolean isExist = false;
		
		//Part of speech 1 
		PartOfSpeech pos1 = new PartOfSpeech();
		pos1.setRzeczownik(RZECZOWNIK_1);
		pos1.setCzasownik(CZASOWNIK_1);
		pos1.setPrzymiotnik(PRZYMIOTNIK_1);
		
		PartOfSpeech pos2 = new PartOfSpeech();
		pos2.setRzeczownik(RZECZOWNIK_2);
		pos2.setCzasownik(CZASOWNIK_2);
		pos2.setPrzymiotnik(PRZYMIOTNIK_2);
		int count = wordManager.getAllPartOfSpeech().size();
		
		wordManager.addPartOfSpeech(pos1);
		wordManager.addPartOfSpeech(pos2);
		wordManager.deletePartOfSpeechById(pos1);

		List<PartOfSpeech> retrievedPoses = wordManager.getAllPartOfSpeech();
		
		for (PartOfSpeech rPos : retrievedPoses) {
			if (rPos.getId().equals(pos1.getId())) {
				isExist = true;
			}
		}
		assertEquals(count + 1, retrievedPoses.size());
		assertFalse(isExist);
	}
	
	@Test 
	public void checkGetAllPartOfSpeech() {
		boolean isFound = false;
		PartOfSpeech pos = new PartOfSpeech();
		
		pos.setRzeczownik("Roweros");
		pos.setCzasownik(CZASOWNIK_1);
		pos.setPrzymiotnik(PRZYMIOTNIK_1);
		int count = wordManager.getAllPartOfSpeech().size();
		wordManager.addPartOfSpeech(pos);
		
		List<PartOfSpeech> poses = wordManager.getPartOfSpeechByRzeczownik("Roweros");
		for(PartOfSpeech retrievedPos : poses) {
			if (retrievedPos.getRzeczownik() == pos.getRzeczownik())
				isFound = true;
		}
		assertEquals(count + 1 , wordManager.getAllPartOfSpeech().size());
		assertTrue(isFound);
	}
	
	@Test 
	public void checkDeleteWithDependencies() {
		boolean isExist = false;
		Word word1 = new Word();
		word1.setMianownik(MIANOWNIK_1);
		word1.setDopelniacz(DOPELNIACZ_1);
		word1.setWolacz(WOLACZ_1);
		
		PartOfSpeech pos1 = new PartOfSpeech();
		pos1.setRzeczownik(RZECZOWNIK_1);
		pos1.setCzasownik(CZASOWNIK_1);
		pos1.setPrzymiotnik(PRZYMIOTNIK_1);
		
		PartOfSpeech pos2 = new PartOfSpeech();
		pos2.setRzeczownik(RZECZOWNIK_1);
		pos2.setCzasownik("Ide z rowerem");
		pos2.setPrzymiotnik("Zielony rower");
		
		wordManager.addWord(word1);
		Long posID1 = wordManager.addPartOfSpeech(pos1);
		Long posID2 = wordManager.addPartOfSpeech(pos2);
		wordManager.makeRelation(word1.getId(), posID1);
		wordManager.makeRelation(word1.getId(), posID2);
		
		wordManager.deleteWordWithAllPartOfSpeech(word1);
		
		List<PartOfSpeech> retrievedPoses = wordManager.getAllPartOfSpeech();
		
		for (PartOfSpeech rPos : retrievedPoses) {
			if (rPos.getId().equals(pos1.getId())) {
				isExist = true;
			}
		}
		assertFalse(isExist);
		
	}
}
