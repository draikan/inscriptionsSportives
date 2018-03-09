package inscriptions;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Représente une Equipe. C'est-à-dire un ensemble de personnes pouvant 
 * s'inscrire à une compétition.
 * 
 */

@Entity
@Table(name = "equipe")
public class Equipe extends Candidat
{
	Inscriptions test;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_eq")
    private int id_eq;
	
	private static final long serialVersionUID = 4147819927233466035L;
	
	 @ManyToMany(cascade = { CascadeType.ALL })
	 @JoinTable(
	name = "Appartenir",
	joinColumns = { @JoinColumn(name = "id_e") },
	inverseJoinColumns = { @JoinColumn(name = "id_p") })
	private SortedSet<Personne> membres = new TreeSet<>();
	
	
	Equipe(Inscriptions inscriptions, String nom)
	{
		super(inscriptions, nom);
	}

	/**
	 * Retourne l'ensemble des personnes formant l'équipe.
	 */
	
	public SortedSet<Personne> getMembres()
	{
		return Collections.unmodifiableSortedSet(membres);
	}
	
	
	/**
	 * Ajoute une personne dans l'équipe.
	 * @param membre
	 * @return
	 */

	public boolean add(Personne membre)
	{
		membre.add(this);
		return membres.add(membre);
	}

	/**
	 * Supprime une personne de l'équipe. 
	 * @param membre
	 * @return
	 */
	
	public boolean remove(Personne membre)
	{
		membre.remove(this);
		return membres.remove(membre);
	}

	/**
	 * Retourne les personnes que l'on peut ajouter dans cette équipe.
	 * @return les personnes que l'on peut ajouter dans cette équipe.
	 */
	
	public Set<Personne> getPersonnesAAjouter()
	{
		// TODO retourner les personnes que l'on peut ajouter dans cette équipe.
		SortedSet<Personne> test2 = test.getPersonnes();
		 test2.removeAll(getMembres());
		return Collections.unmodifiableSortedSet(test2);
	}
	
	
	@Override
	public void delete()
	{
		super.delete();
	}
	
	@Override
	public String toString()
	{
		return "Equipe " + super.toString();
	}
}
