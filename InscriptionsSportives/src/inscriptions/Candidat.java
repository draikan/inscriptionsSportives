package inscriptions;

import Application.Inscriptions;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/**
 * Candidat √† un √©v√©nement sportif, soit une personne physique, soit une √©quipe.
 *
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Candidat implements Comparable<Candidat>, Serializable
{
	@Transient
	private static final long serialVersionUID = -6035399822298694746L;

	@Transient
	private Inscriptions inscriptions;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_cand")
	private int id_cand;

	@Column(name = "nom")
	private String nom;
	
	@ManyToMany(mappedBy = "candidats")				 
	private Set<Competition> competitions;
	
	
	/**
	 * Constructeur pour Hibernante
	 */
	Candidat(){
		this.inscriptions = Inscriptions.getInscriptions();
	}
	
	Candidat(Inscriptions inscriptions, String nom)
	{
		this.inscriptions = inscriptions;	
		this.nom = nom;
		competitions = new TreeSet<>();
	}

	
	/**
	 * Retourne le nom du candidat.
	 * @return
	 */
	
	public String getNom()
	{
		return nom;
	}

	/**
	 * Modifie le nom du candidat.
	 * @param nom
	 */
	
	public void setNom(String nom)
	{
		this.nom = nom;
		Inscriptions.saveEntity(this);
	}

	/**
	 * Retourne toutes les comp√©titions auxquelles ce candidat est inscrit.s
	 * @return
	 */

	public Set<Competition> getCompetitions()
	{
		return Collections.unmodifiableSet(competitions);
	}
	
	
	/**
	 * Ajoute une compÈtition ‡ un candidat
	 * @param competition
	 * @return
	 */
	boolean add(Competition competition)
	{
		Boolean r = competitions.add(competition);

		Inscriptions.saveEntity(this);
		return r;
	}

	/**
	 * Supprimer une compÈtition ‡ un candidat
	 * @param competition
	 * @return
	 */
	boolean remove(Competition competition)
	{
		Boolean r = competitions.remove(competition);

		Inscriptions.saveEntity(this);
		return r;
	}

	/**
	 * Supprime un candidat de l'application.
	 */
	
	public void delete()
	{
		for (Competition c : competitions)
			c.remove(this);
		inscriptions.delete(this);
	}
	
	@Override
	public int compareTo(Candidat o)
	{
		return getNom().compareTo(o.getNom());
	}
	
	@Override
	public String toString()
	{
		return "\n" + getNom() + " -> inscrit ‡ " + getCompetitions();
	}
}
