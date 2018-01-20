package menu;

import java.util.ArrayList;

import commandLineMenus.Action;
import commandLineMenus.List;
import commandLineMenus.ListData;
import commandLineMenus.ListOption;
import commandLineMenus.Menu;
import commandLineMenus.Option;
import inscriptions.Inscriptions;
import inscriptions.Personne;

public class Menupersonne {
	
	
	private static Inscriptions inscriptions;

	
	public Menupersonne()
	{
		inscriptions = Inscriptions.getInscriptions();
	}
	
	public Inscriptions getInscriptions()
	{
		return inscriptions;
	}
	
	static Menu getMenuPersonne(Inscriptions inscriptions)
	{
		Menu menuPersonne = new Menu ("Gestion de Personnes","1");
		menuPersonne.add(AjouterPersonneMenu());
		menuPersonne.add(GererpersonneList());
		menuPersonne.addQuit("q");
		menuPersonne.setAutoBack(false);
		return menuPersonne;
	}

	static Option AjouterPersonneMenu()
	{
		Option Personne = new Option("Inserer Personne", "a", 
				insererPersonneAction());
		return Personne;
	}
	static Action insererPersonneAction()
	{
		return new Action()
		{
			public void optionSelected()1
			{
			
			}
		};
	}
	
	private static List<Personne> GererpersonneList() 
	{
			return new List<Personne>("Liste de personnes", "b", 
					() -> new ArrayList<>(inscriptions.getPersonnes()),
					(element) -> getSomeoneMenu(element)
					);
		
	}
	
	private static Option getSomeoneMenu(Personne someone)
	{
		Menu someoneMenu = new Menu("Option pour  "
				+someone.getPrenom()+" "+someone.getNom(),null);
		
		someoneMenu.add(VoirInfo(someone));
		someoneMenu.add(AjoutAEquipe(someone));
		someoneMenu.add(AjoutACompete(someone));
		someoneMenu.add(DesPersonneComp(someone));
		someoneMenu.add(RetirerPersEqu(someone));
		//someoneMenu.add(SupprimerPersonne(someone));
		
		
		someoneMenu.setAutoBack(true);
		someoneMenu.addQuit("q");
		return someoneMenu;
	}
	
	private static Option VoirInfo(Personne someone)
	{
		return new Option("Voir", "a", new Action()
		{
			@Override
			public void optionSelected()
			{
				System.out.println("Prénom : "+someone.getPrenom()+"/Nom : "+
						someone.getNom()+"/ Mail : "+someone.getMail());
				if(!someone.getEquipes().isEmpty())
				{
					System.out.println(someone.getEquipes().toString());
				}
				else
				{
					System.out.println(someone.getPrenom()+ " n'a pas d'équipe");
				}
				if(!someone.getCompetitions().isEmpty())
				{
					System.out.println("Participe à "+someone.getCompetitions().toString());
				}
				else
				{
					System.out.println(someone.getPrenom()+" ne participe à aucune compétition");
				}
			}
		});
	}
	private static Option AjoutAEquipe(Personne someone)
	{
		return new Option("Integrer une equipe", "b", new Action()
		{
			@Override
			public void optionSelected()
			{

			}
		});
	}	
	private static Option AjoutACompete(Personne someone)
	{
		return new Option("Inscrire a une competition", "c", new Action()
		{
			@Override
			public void optionSelected()
			{
			
			}
		});
	}	
	private static Option DesPersonneComp(Personne someone)
	{
		return new Option("Desinscrire", "d", new Action()
		{
			@Override
			public void optionSelected()
			{
	
			}
		});
	}	
	private static Option RetirerPersEqu(Personne someone)
	{
		return new Option("Retirer de l'equipe", "e", new Action()
		{
			@Override
			public void optionSelected()
			{
	
			}
		});
	}
	
	//à faire
	
	//private static Option SupprimerPersonne(Personne someone)
	//{
	//	return new Option("Supp", "f", new Action()
	//	{
	//		@Override
	//		public void optionSelected()
	//		{
	//			//Lpers.remove(someone);
	//		}
	//	});
	//}	

	
	
	
	
	
	
	
	
}
