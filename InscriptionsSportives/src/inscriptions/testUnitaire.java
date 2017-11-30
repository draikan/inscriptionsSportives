package inscriptions;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Test;

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
		 test1.getPrenom() ;
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
