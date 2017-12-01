package testUnitaire;

import static org.junit.Assert.*;

import org.junit.Test;
import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

public class testUnitaire {

	public class TestsJUnit
	{
		Inscriptions ins ; 
		Personne test ;
		Equipe testEq ; 
		Competition comp ; 
	 
	@Test
	 public void testDeleteEquipe()
	 {
		 Equipe test1 = ins.createEquipe("testEq");
		 Personne test2 = ins.createPersonne("test","test","test");
		 test1.add(test2) ; 
		 assertEquals("testEq",testEq);
	 }
	 
	@Test
	 public void testDeletePersonne()
	 {
		 Equipe test1 = ins.createEquipe("testEq");
		 Personne test2 = ins.createPersonne("test","test","test");
		 test1.add(test2) ; 
		 assertEquals("testEq",testEq);
	 }
	
	 @Test
	 public void testCompetitions()
	 {
	  assertEquals("", 2, test.getCompetitions());
	 }
	 
	 @Test
	 public void testMail()
	 {
	  assertEquals("", 2, test.getMail());
	 }
	 
	 @Test
	 public void testNom()
	 {
	  assertEquals("", 2, test.getNom());
	 }
	 
	 @Test
	 public void testPrenom()
	 {
	  assertEquals("", 2, test.getPrenom());
	 }
	 
	 @Test
	 public void testsetMail()
	 {
	  assertEquals("", 2, test.setMail(mail));
	 }

	 @Test
	 public void testsetNom()
	 {
	  assertEquals("", 2, test.setNom(nom));
	 }
	 
	 @Test
	 public void testsetPrenom()
	 {
	  assertEquals("", 2, test.setPrenom(prenom));
	 }
	 
	 @Test
	 public void testtoString()
	 {
	  assertEquals("", 2, test.toString());
	 }
	 
	 @Test
	 public void testCompare()
	 {
	  assertEquals("", 2, test.setNom(nom););
	 }
	}


}
