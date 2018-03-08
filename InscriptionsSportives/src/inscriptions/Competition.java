package inscriptions;

import java.io.Serializable;
import java.util.Collections;
import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;



/**
 * Représente une compétition, c'est-à-dire un ensemble de candidats 
 * inscrits à un événement, les inscriptions sont closes à la date dateCloture.
 *
 */
@Entity
@Table(name = "competition")
public class Competition implements Comparable<Competition>, Serializable
{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_co")
    private int id_co;
	
	
	private static final long serialVersionUID = -2882150118573759729L;
	private Inscriptions inscriptions;
	
	@Column(name = "nom")
	private String nom;
	
	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "Participer",
	joinColumns = {@JoinColumn(name = "id_co")},
	inverseJoinColumns = {@JoinColumn(name = "id_cand")})
	private Set<Candidat> candidats;
	
	@Column(name="date_cloture")
	private LocalDate dateCloture;
	
	@Column(name="en_equipe")
	private boolean enEquipe = false;
	
	
	private LocalDate dateSystem = LocalDate.now();

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
		// TODO vérifier que l'on avance pas la date.
		this.dateCloture = dateCloture;
		}
		else
		{
			//mettre l'Exception
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
		// TODO vérifier que la date de clôture n'est pas passée
		if(inscriptionsOuvertes()) {
			if (enEquipe)
				throw new RuntimeException();
			personne.add(this);
			return candidats.add(personne);
			}
			else
			{
				return false;
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
		if(inscriptionsOuvertes()) {
			if (!enEquipe)
				throw new RuntimeException();
			equipe.add(this);
			return candidats.add(equipe);
			
		}

		else
		{
			return false;
		}
		// TODO vérifier que la date de clôture n'est pas passée
		
	}

	/**
	 * Retourne les personnes que l'on peut inscrire à cette competition.
	 * @return les personnes que l'on peut inscrire à cette compétition.
	 */
	
	public Set<Personne> getPersonnesAInscrire()
	{
		// TODO retourner les personnes que l'on peut inscrire à cette compétition.
		return null;
	}

	/**
	 * Désinscrit un candidat.
	 * @param candidat
	 * @return
	 */
	
	public boolean remove(Candidat candidat)
	{
		candidat.remove(this);
		return candidats.remove(candidat);
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
