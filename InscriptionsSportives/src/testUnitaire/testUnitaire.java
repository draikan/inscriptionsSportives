package testUnitaire;

import static org.junit.Assert.*;

import org.junit.Test;
import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

public class testUnitaire {

		Inscriptions ins ; 
		Personne test ;
		Equipe testEq ; 
		Competition comp ; 
	 
	 
	@Test
	 public void testDeleteCandidat()
	 {
		 Equipe test1 = ins.createEquipe("testEq");
		 test1.delete();
		 assertEquals("",test1.getNom());
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
		 Personne test2 = ins.createPersonne("test","test","test");
		 test2.setNom("toto");
		 assertEquals("toto", test2.getNom());
	 }
	 
	 @Test
	 public void testSetGetNom()
	 {
		 Personne test2 = ins.createPersonne("test","test","test");
		 test2.setPrenom("toto");
		 assertEquals("toto", test2.getPrenom());
	 }
	 
	 @Test
	 public void testGetEquipes()
	 {
		 Equipe test1 = ins.createEquipe("testEq");
		 Personne test2 = ins.createPersonne("test","test","test");
		 test1.add(test2) ;
		 assertEquals("test1",test2.getEquipes());
	 }
	 
	}
