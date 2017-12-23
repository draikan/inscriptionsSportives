package menu;

import java.util.SortedSet;
import java.util.TreeSet;

import commandLineMenus.Action;
import commandLineMenus.Menu;
import commandLineMenus.Option;
import commandLineMenus.rendering.examples.util.InOut;
import inscriptions.Inscriptions;
import inscriptions.Personne;

public class Menupersonne {
	
	private java.util.List<Personne> Lpers ;
	private static Listpersonne Lpers2;

	private static Inscriptions inscriptions;
	
	public Menupersonne()
	{
		Lpers2 = new Listpersonne(Lpers);
		inscriptions = Inscriptions.getInscriptions();
	}

	public Inscriptions getInscriptions()
	{
		return inscriptions;
	}
	
	static Menu getMenuPersonne(Inscriptions inscriptions)
	{
		Menu menuPersonne = new Menu ("Gestion de Personnes","3");
		menuPersonne.add(AjouterPersonneMenu());
		menuPersonne.add(Lpers2.getPersonneList());
		menuPersonne.addQuit("q");
		menuPersonne.setAutoBack(false);
		return menuPersonne;
	}

	static Option AjouterPersonneMenu()
	{
		Option Personne = new Option("Inserer Personne", "i", 
				insererPersonneAction());
		return Personne;
	}
	
	static Action insererPersonneAction()
	{
		return new Action()
		{
			public void optionSelected()
			{
				String nom = InOut.getString("Le nom de la personne : "),
						prenom = InOut.getString("Le prenom : "),
						email = InOut.getString("Le mail : ");
					inscriptions.createPersonne(nom, prenom, email);
			}
		};
	}
}
