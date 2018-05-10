package inscriptions;

import Application.Inscriptions;
import javax.persistence.*;
import java.util.Collections;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import static Application.Inscriptions.*;

/**
 * Représente une Equipe. C'est-à-dire un ensemble de personnes pouvant 
 * s'inscrire à une compétition.
 * 
 */

@Entity
public class Equipe extends Candidat
{
	
	public interface EquipeCreator{ Equipe create(Inscriptions application, String nom); }

	static {
		getInscriptions().setEquipeCreator(Equipe::new);
	}
	
	@Transient
	private static final long serialVersionUID = 4147819927233466035L;
	
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "Appartenir",
			joinColumns = { @JoinColumn(name = "id_e") },
			inverseJoinColumns = { @JoinColumn(name = "id_p") }
	)
	@OrderBy("id ASC")
	private SortedSet<Personne> membres = new TreeSet<Personne>();
	
	
	/**
	 * Constructeur pour Hibernante
	 */
	Equipe(){ }
	
	
	
	
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
		Boolean r =  membres.remove(membre);
		Inscriptions.saveEntity(this);
		return r ;
	}

	/**
	 * Supprime une personne de l'équipe. 
	 * @param membre
	 * @return
	 */
	
	public boolean remove(Personne membre)
	{
		Boolean r =  membres.remove(membre);
		Inscriptions.saveEntity(this);
		return r;
	}
	
	public void clear(){
		this.membres = new TreeSet<Personne>();
	}

		
	@Override
	public void delete()
	{
		for (Personne p : membres){
			p.remove(this);
		}
		super.delete();
	}
	
	@Override
	public String toString()
	{
		return "Equipe : " + this.getNom() + " (" + this.getMembres().size() + ")";
	}
}
