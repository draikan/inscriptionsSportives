package inscriptions;

import Application.Inscriptions;

import javax.persistence.*;

import static Application.Inscriptions.getInscriptions;

import java.io.Serializable;
import java.util.Collections;
import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;



/**
 * Représente une compétition, c'est-à-dire un ensemble de candidats 
 * inscrits à un événement, les inscriptions sont closes à la date dateCloture.
 *
 */
@Entity
public class Competition implements Comparable<Competition>, Serializable
{
	
	public interface CompetitionCreator{ Competition create(Inscriptions application, String nom, LocalDate dateCloture, boolean enEquipe); }

	static {
		getInscriptions().setCompetitionCreator(Competition::new);
	}
	

	@Transient
	private static final long serialVersionUID = -2882150118573759729L;
	
	@Transient
	private Inscriptions inscriptions;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_co")
    private int id_co;
	
	@Column(name = "nom")
	private String nom;
	
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "Participer",
	joinColumns = {@JoinColumn(name = "id_co")},
	inverseJoinColumns = {@JoinColumn(name = "id_cand")})
	private Set<Candidat> candidats;
	
	
	@Column(name="date_cloture")
	private LocalDate dateCloture;
	
	@Column(name="en_equipe")
	private boolean enEquipe = false;
	
	
	
	
	private LocalDate dateSystem = LocalDate.now();
	
	/**
	 * Constructeur pour Hibernante
	 */
	Competition(){  }

	Competition(Inscriptions inscriptions, String nom, LocalDate dateCloture, boolean enEquipe)
	{
		this.enEquipe = enEquipe;
		this.inscriptions = inscriptions;
		this.nom = nom;
		this.dateCloture = dateCloture;
		candidats = new TreeSet<>();
	}
	
	/**
	 * Retourne le nom de la compétition.
	 * @return
	 */
	
	public String getNom()
	{
		return nom;
	}
	
	/**
	 * Modifie le nom de la compétition.
	 */
	
	public void setNom(String nom)
	{
		this.nom = nom ;
		Inscriptions.saveEntity(this);
	}
	
	/**
	 * Retourne vrai si les inscriptions sont encore ouvertes, 
	 * faux si les inscriptions sont closes.
	 * @return
	 */
	
	public boolean inscriptionsOuvertes()
	{
		return(dateSystem.isBefore(dateCloture));
	}
	
	/**
	 * Retourne la date de cloture des inscriptions.
	 * @return
	 */
	
	public LocalDate getDateCloture()
	{
		return dateCloture;
	}
	
	/**
	 * Est vrai si et seulement si les inscriptions sont réservées aux équipes.
	 * @return
	 */
	
	public boolean estEnEquipe()
	{
		return enEquipe;
	}
	
	/**
	 * Modifie la date de cloture des inscriptions. Il est possible de la reculer 
	 * mais pas de l'avancer.
	 * @param dateCloture
	 */
	
	public void setDateCloture(LocalDate dateCloture)
	{
		if(dateCloture.isAfter(this.dateCloture) ) {
		this.dateCloture = dateCloture;
		Inscriptions.saveEntity(this);
		}
		else
		{
			throw new RuntimeException("Date de cl�ture invalide !");
		}
		
	}
	
	/**
	 * Retourne l'ensemble des candidats inscrits.
	 * @return
	 */
	
	public Set<Candidat> getCandidats()
	{
		return Collections.unmodifiableSet(candidats);
	}
	
	/**
	 * Inscrit un candidat de type Personne à la compétition. Provoque une
	 * exception si la compétition est réservée aux équipes ou que les 
	 * inscriptions sont closes.
	 * @param personne
	 * @return
	 */
	
	public boolean add(Personne personne)
	{
		if( this.inscriptionsOuvertes() ) {
			personne.add(this);
			Boolean r = candidats.add(personne);

			Inscriptions.saveEntity(this);
			return r;
		} else {
			throw new RuntimeException("Les inscriptions sont closes !");
		}	
	}

	/**
	 * Inscrit un candidat de type Equipe à la compétition. Provoque une
	 * exception si la compétition est réservée aux personnes ou que 
	 * les inscriptions sont closes.
	 * @param personne
	 * @return
	 */

	public boolean add(Equipe equipe)
	{
		if( this.inscriptionsOuvertes() ) {
			equipe.add(this);
			Boolean r = candidats.add(equipe);

			Inscriptions.saveEntity(this);
			return r;
		}
		return false;
		
	}

	/**
	 * Désinscrit un candidat.
	 * @param candidat
	 * @return
	 */
	public boolean remove(Candidat candidat)
	{
		Boolean r = candidats.remove(candidat);
		Inscriptions.saveEntity(this);
		return r;
	}
	
	public void clear(){
		this.candidats = new TreeSet<Candidat>();
	}
	
	/**
	 * Supprime la compétition de l'application.
	 */
	
	public void delete()
	{
		for (Candidat candidat : candidats)
			remove(candidat);
		inscriptions.delete(this);
	}
	
	@Override
	public int compareTo(Competition o)
	{
		return getNom().compareTo(o.getNom());
	}
	
	@Override
	public String toString()
	{
		return getNom();
	}
}
