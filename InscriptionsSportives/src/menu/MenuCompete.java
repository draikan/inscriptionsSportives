package menu;

import java.time.LocalDate;
import java.util.ArrayList;

import commandLineMenus.Action;
import commandLineMenus.List;
import commandLineMenus.Menu;
import commandLineMenus.Option;
import commandLineMenus.rendering.examples.util.InOut;
import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

public class MenuCompete {
	
	
	private static Inscriptions inscriptions;

	public MenuCompete()
	{
		inscriptions = Inscriptions.getInscriptions();
	}
	
	
	static Menu getMenuComp()
	{
		Menu menuPersonne = new Menu ("Gestion des competitions","3");
		menuPersonne.add(CreercompMenu());
		menuPersonne.add(GerercompList());
		menuPersonne.addQuit("q");
		menuPersonne.setAutoBack(false);
		return menuPersonne;
	}

	static Option CreercompMenu()
	{
		Option Personne = new Option("Creer une competition", "a", 
				creercompAction());
		return Personne;
	}
	static Action creercompAction()
	{
		return new Action()
		{
			public void optionSelected()
			{
				String nom = InOut.getString("Le nom : ");
				//
			}
		};
	}
	
	private static List<Competition> GerercompList() 
	{
			return new List<Competition>("Liste des competes", "b", 
					() -> new ArrayList<>(inscriptions.getCompetitions()),
					(element) -> getcompMenu(element)
					);
		
	}
	
	private static Option getcompMenu(Competition someone)
	{
		Menu someoneMenu = new Menu("Option pour  "
				+someone.getNom(),null);
		
		someoneMenu.add(VoirInfo(someone));
		someoneMenu.add(ModifierDatecloture(someone));
		someoneMenu.add(ModifierNom(someone));
		someoneMenu.add(Suppcomp(someone));
		

		someoneMenu.setAutoBack(true);
		someoneMenu.addQuit("q");
		return someoneMenu;
	}
	
	private static Option VoirInfo(Competition someone)
	{
		return new Option("Voir", "a", new Action()
		{
			@Override
			public void optionSelected()
			{
				System.out.println("/Nom : "+someone.getNom()+"/ Date de cloture des inscription : "+someone.getDateCloture());
				if(someone.estEnEquipe()){
					System.out.println("Compete en equipe");
				}
				else {System.out.println("Compete individuelle");}
			}
		});
	}
	private static Option ModifierDatecloture(Competition someone)
	{
		return new Option("Modifier la date de cloture des inscription", "b", new Action()
		{
			@Override
			public void optionSelected()
			{
				
			}
		});
	}
	private static Option ModifierNom(Competition someone)
	{
		return new Option("Modifier le nom de la competition", "b", new Action()
		{
			@Override
			public void optionSelected()
			{
				
			}
		});
	}
	private static Option Suppcomp(Competition someone)
	{
		return new Option("Supprimer cette competition", "b", new Action()
		{
			@Override
			public void optionSelected()
			{
				
			}
		});
	}
	
}
