package inscriptions;

import org.junit.Test;

public class testUnitaire {
	public class TestsJUnit
	{
	
		Inscriptions ins ; 
		Personne test = new Personne(ins , "test", "test", "test" ); 
		
	 @Test
	 public void testCompare()
	 {
	  assertEquals("", 2, test.compareTo());
	 }
	 
	 @Test
	 public void testDelete()
	 {
	  assertEquals("", 2, test.delete());
	 }
	 
	 @Test
	 public void testEquipes()
	 {
	  assertEquals("", 2, test.getEquipes());
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
