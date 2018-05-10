package menu;

import java.util.ArrayList;

import commandLineMenus.Action;
import commandLineMenus.List;
import commandLineMenus.Menu;
import commandLineMenus.Option;
import commandLineMenus.rendering.examples.util.InOut;
import inscriptions.Equipe;
import Application.Inscriptions;
import inscriptions.Personne;

public class MenuEquipe {

	private static Inscriptions inscriptions;
	private static Equipe equipe;
	
	public MenuEquipe()
	{
		inscriptions = Inscriptions.getInscriptions();
	}
	
	
	static Menu getMenuEquipe()
	{
		Menu menuPersonne = new Menu ("Gestion des equipe","2");
		menuPersonne.add(AjouterEquipeMenu());
		menuPersonne.add(GererequipeList());
		menuPersonne.addQuit("q");
		menuPersonne.setAutoBack(false);
		return menuPersonne;
	}

	static Option AjouterEquipeMenu()
	{
		Option Personne = new Option("Creer une équipe", "a", 
				creerequipeAction());
		return Personne;
	}
	static Action creerequipeAction()
	{
		return new Action()
		{
			public void optionSelected()
			{
				String nom = InOut.getString("Le nom : ");
				inscriptions.createEquipe(nom);
			}
		};
	}
	
	private static List<Equipe> GererequipeList() 
	{
			return new List<Equipe>("Liste des equipes", "b", 
					() -> new ArrayList<>(inscriptions.getEquipes()),
					(element) -> getEqMenu(element)
					);
		
	}
	
	private static Option getEqMenu(Equipe someone)
	{
		Menu someoneMenu = new Menu("Option pour  "
				+someone.getNom(),null);
		
		someoneMenu.add(VoirInfo(someone));
		someoneMenu.add(Integrernewmember(someone));
		//someoneMenu.add(ListMembre());

		someoneMenu.setAutoBack(true);
		someoneMenu.addQuit("q");
		return someoneMenu;
	}
	
	private static Option VoirInfo(Equipe someone)
	{
		return new Option("Voir", "a", new Action()
		{
			@Override
			public void optionSelected()
			{
				System.out.println("/Nom : "+someone.getNom());
				if(!someone.getCompetitions().isEmpty())
				{
					System.out.println("Participe à "+someone.getCompetitions().toString());
				}
				else
				{
					System.out.println(someone.getNom()+" ne participe à aucune compétition");
				}
			}
		});
	}
	private static Option Integrernewmember(Equipe someone)
	{
		return new Option("Integrer une nouvelle recrue", "b", new Action()
		{
			@Override
			public void optionSelected()
			{
				
			}
		});
	}
	
	
	private static List<Personne> ListMembre() 
	{
			return new List<Personne>("Liste des personnes composant l'equipe", "b", 
					() -> new ArrayList<>(equipe.getMembres()),
					(element) -> getPersMenu(element)
					);
		
	}
	
	private static Option getPersMenu(Personne someone)
	{
		Menu someoneMenu = new Menu("Option pour  "
				+someone.getNom(),null);
		
		someoneMenu.add(SupprimerMembre(someone));
		someoneMenu.add(ListMembre());
		someoneMenu.setAutoBack(true);
		someoneMenu.addQuit("q");
		return someoneMenu;
	}
	
	private static Option SupprimerMembre(Personne someone)
	{
		return new Option("Retirer ce membre de l'equipe", "b", new Action()
		{
			@Override
			public void optionSelected()
			{
				
			}
		});
	}
}
