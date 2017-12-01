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
	 public void testDeleteCandidat()
	 {
		 Equipe test1 = ins.createEquipe("testEq");
		 Personne test2 = ins.createPersonne("test","test","test");
		 test1.add(test2) ;
		 test1.delete();
		 assertEquals("",test1);
	 }
	
	@Test
	 public void testAddRemovegetMember()
	 {
		 Equipe test1 = ins.createEquipe("testEq");
		 Personne test2 = ins.createPersonne("test","test","test");
		 test1.add(test2) ;
		 test1.remove(test2);
		 test1.getMembres() ; 
		 assertEquals("",test1.getMembres());
	 }
	
	 @Test
	 public void testSetGetPrenom()
	 {

	 }
	 
	 @Test
	 public void testSetGetNom()
	 {

	 }
	 
	 @Test
	 public void testGetEquipes()
	 {
	 }
	 
	}


}
