package Application;

import inscriptions.Candidat;
import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Personne;
import hibernate.Hibernate;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.*;
import java.time.LocalDate;



/**
 * Point d'entrée dans l'application, un seul objet de type Inscription
 * permet de gérer les compétitions, candidats (de type equipe ou personne)
 * ainsi que d'inscrire des candidats à des compétition.
 */

public class Inscriptions implements Serializable
{
	private static final long serialVersionUID = -3095339436048473524L;
	private static final String FILE_NAME = "Inscriptions.srz";
	private static Inscriptions inscriptions;
	
	private static Equipe.EquipeCreator equipeCreator;
	private static Personne.PersonneCreator personneCreator;
	private static Competition.CompetitionCreator competitionCreator;
	
	public static void setEquipeCreator(Equipe.EquipeCreator equipeCreator) { inscriptions.equipeCreator = equipeCreator; }
	public static void setPersonneCreator(Personne.PersonneCreator personneCreator) { inscriptions.personneCreator = personneCreator; }
	public static void setCompetitionCreator(Competition.CompetitionCreator competitionCreator) { inscriptions.competitionCreator = competitionCreator; }
	
	private SortedSet<Competition> competitions = new TreeSet<>();
	private SortedSet<Candidat> candidats = new TreeSet<>();

	private Inscriptions()
	{
	}
	
	/**
	 * Retourne les compétitions.
	 * @return
	 */
	
	public SortedSet<Competition> getCompetitions()
	{
		return Collections.unmodifiableSortedSet(competitions);
	}
	
	/**
	 * Retourne tous les candidats (personnes et équipes confondues).
	 * @return
	 */
	
	public SortedSet<Candidat> getCandidats()
	{
		return Collections.unmodifiableSortedSet(candidats);
	}

	/**
	 * Retourne toutes les personnes.
	 * @return
	 */
	
	public SortedSet<Personne> getPersonnes()
	{
		SortedSet<Personne> personnes = new TreeSet<>();
		for (Candidat c : getCandidats())
			if (c instanceof Personne)
				personnes.add((Personne)c);
		return Collections.unmodifiableSortedSet(personnes);
	}

	/**
	 * Retourne toutes les équipes.
	 * @return
	 */
	
	public SortedSet<Equipe> getEquipes()
	{
		SortedSet<Equipe> equipes = new TreeSet<>();
		for (Candidat c : getCandidats())
			if (c instanceof Equipe)
				equipes.add((Equipe)c);
		return Collections.unmodifiableSortedSet(equipes);
	}

	/**
	 * Créée une compétition. Ceci est le seul moyen, il n'y a pas
	 * de constructeur public dans {@link Competition}.
	 * @param nom
	 * @param dateCloture
	 * @param enEquipe
	 * @return
	 */
	
	public Competition createCompetition(String nom, 
			LocalDate dateCloture, boolean enEquipe)
	{
		Competition competition = competitionCreator.create(this, nom, dateCloture, enEquipe);
		competitions.add(competition);
		
		Inscriptions.saveEntity(competition);
		return competition;
	}

	/**
	 * Créée une Candidat de type Personne. Ceci est le seul moyen, il n'y a pas
	 * de constructeur public dans {@link Personne}.

	 * @param nom
	 * @param prenom
	 * @param mail
	 * @return
	 */
	
	public Personne createPersonne(String nom, String prenom, String mail)
	{
		Personne personne = personneCreator.create(this, nom, prenom, mail);
		candidats.add(personne);
		
		Inscriptions.saveEntity(personne);
		return personne;
	}
	
	/**
	 * Créée une Candidat de type équipe. Ceci est le seul moyen, il n'y a pas
	 * de constructeur public dans {@link Equipe}.
	 * @param nom
	 * @param prenom
	 * @param mail
	 * @return
	 */
	
	public Equipe createEquipe(String nom)
	{
		Equipe equipe = equipeCreator.create(this, nom);
		candidats.add(equipe);
		
		Inscriptions.saveEntity(equipe);
		return equipe;
	}
	
	public void delete(Competition competition)
	{
		competitions.remove(competition);
		Inscriptions.removeEntity(competition);
	}
	
	public void delete(Candidat candidat)
	{
		candidats.remove(candidat);
		Inscriptions.removeEntity(candidat);
	}
	
	/**
	 * Retourne l'unique instance de cette classe.
	 * Crée cet objet s'il n'existe déjà.
	 * @return l'unique objet de type {@link Inscriptions}.
	 */
	
	public static Inscriptions getInscriptions()
	{
		
		if (inscriptions == null)
		{
			inscriptions = readObject();
			if (inscriptions == null)
				inscriptions = new Inscriptions();
		}
		return inscriptions;
	}

	/**
	 * Retourne un object inscriptions vide. Ne modifie pas les compétitions
	 * et candidats déjà existants.
	 */
	
	public Inscriptions reinitialiser()
	{
		inscriptions = new Inscriptions();
		return getInscriptions();
	}

	/**
	 * Efface toutes les modifications sur Inscriptions depuis la dernière sauvegarde.
	 * Ne modifie pas les compétitions et candidats déjà existants.
	 */
	
	public Inscriptions recharger()
	{
		inscriptions = null;
		return getInscriptions();
	}
	
	private static Inscriptions readObject()
	{
		ObjectInputStream ois = null;
		try
		{
			FileInputStream fis = new FileInputStream(FILE_NAME);
			ois = new ObjectInputStream(fis);
			return (Inscriptions)(ois.readObject());
		}
		catch (IOException | ClassNotFoundException e)
		{
			return null;
		}
		finally
		{
				try
				{
					if (ois != null)
						ois.close();
				} 
				catch (IOException e){}
		}	
	}
	
	/**
	 * D�marre la connexion � la BDD
	 */
	public static void start() {
		Hibernate.open();
	}
	
	/**
	 * Sauvegarde un entit� / La modifie si besoin
	 * @param o
	 */
	public static void saveEntity(Object o){
		Hibernate.save(o);
	}
	
	/**
	 * Supprime un entit�
	 * @param o
	 */
	public static void removeEntity(Object o){
		Hibernate.delete(o);
	}

	
	@Override
	public String toString()
	{
		return "Candidats : " + getCandidats().toString()
			+ "\nCompetitions  " + getCompetitions().toString();
	}
	
	
	/**
	 * Importe les entit�s de la BDD dans le programme
	 */
	public static void importDataFromDatabase() {

		List<Equipe> equipes = Hibernate.getData("Equipe");
		List<Personne> personnes = Hibernate.getData("Personne");
		List<Competition> competitions = Hibernate.getData("Competition");

		List<Candidat> candidats = new ArrayList<Candidat>();

		candidats.addAll(personnes);
		candidats.addAll(equipes);

		inscriptions.getInscriptions().setCandidats( candidats );
		inscriptions.getInscriptions().setCompetitions( competitions );
	}

	private void setCandidats(List<Candidat> candidats){
		this.candidats = (SortedSet<Candidat>) new TreeSet<Candidat>(candidats);
	}

	private void setCompetitions(List<Competition> competitions){
		this.competitions = (SortedSet<Competition>) new TreeSet<Competition>(competitions);;
	}
}
