package inscriptions;

import Application.Inscriptions;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import static Application.Inscriptions.getInscriptions;


/**
 * Représente une personne physique pouvant s'inscrire à une compétition.
 */


@Entity
public class Personne extends Candidat
{ 
	
	public interface PersonneCreator{ Personne create(Inscriptions application, String nom, String prenom, String mail); }

	static {
		getInscriptions().setPersonneCreator(Personne::new);
	}
	
	@Transient
	private static final long serialVersionUID = 4434646724271327254L;
	
	
	@Column(name = "prenom")
	private String prenom;
	
	@Column(name = "mail")
	private String mail;
	
	
	@ManyToMany(mappedBy="membres")
	@OrderBy("id ASC")
	private Set<Equipe> equipes;
	
	

	/**
	 * Constructeur pour Hibernante
	 */
	Personne(){ }
	
	
	Personne(Inscriptions inscriptions, String nom, String prenom, String mail)
	{
		super(inscriptions, nom);
		this.prenom = prenom;
		this.mail = mail;
		this.equipes = new TreeSet<>();
	}

	/**
	 * Retourne le prénom de la personne.
	 * @return
	 */
	
	public String getPrenom()
	{
		return prenom;
	}

	/**
	 * Modifie le prénom de la personne.
	 * @param prenom
	 */
	
	public void setPrenom(String prenom)
	{
		this.prenom = prenom;
		Inscriptions.saveEntity(this);
	}

	/**
	 * Retourne l'adresse électronique de la personne.
	 * @return
	 */
	
	public String getMail()
	{
		return mail;
	}

	/**
	 * Modifie l'adresse électronique de la personne.
	 * @param mail
	 */
	
	public void setMail(String mail)
	{
		this.mail = mail;
		Inscriptions.saveEntity(this);
	}

	/**
	 * Retoure les équipes dont cette personne fait partie.
	 * @return
	 */
	
	public Set<Equipe> getEquipes()
	{
		return Collections.unmodifiableSet(equipes);
	}
	
	/**
	 * Ajoute une �quipe � une personne
	 * @param equipe
	 */
	boolean add(Equipe equipe)
	{
		Boolean r =  equipes.add(equipe);
		Inscriptions.saveEntity(this);
		return r ;
	}

	/**
	 * Supprimer une �quipe � une personne
	 * @param equipe
	 */
	boolean remove(Equipe equipe)
	{
		Boolean r =  equipes.remove(equipe);
		Inscriptions.saveEntity(this);
		return r ;
	}
	
	@Override
	public void delete()
	{
		super.delete();
		for (Equipe e : equipes)
			e.remove(this);
	}
	
	@Override
	public String toString()
	{
		return "Personne : " + this.getNom() + " " + this.getPrenom() + " <" + this.getMail() + "> ";
	}
}
