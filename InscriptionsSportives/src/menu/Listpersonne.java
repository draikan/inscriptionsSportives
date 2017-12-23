package menu;

import commandLineMenus.Action;
import commandLineMenus.List;
import commandLineMenus.ListData;
import commandLineMenus.ListOption;
import commandLineMenus.Menu;
import commandLineMenus.Option;
import inscriptions.Personne;

public class Listpersonne {
	
private java.util.List<Personne> personne;
	
	Listpersonne(java.util.List<Personne> personne)
	{
		this.personne = personne;
		List<Personne> list = getPersonneList();
		list.start();
	}
	public List<Personne> getPersonneList()
	{
		List<Personne> liste = new List<>("Selectionner une personne",
				getListPersonne(),
				getOptionListePersonne());
		liste.setAutoBack(false);
		liste.addQuit("q");
		return liste;
	}
	
	private ListData<Personne> getListPersonne()
	{
		return new ListData<Personne>()
		{
			@Override
			public java.util.List<Personne> getList()
			{
				return personne;
			}
		};
	}
	
	private ListOption<Personne> getOptionListePersonne()
	{
		return new ListOption<Personne>()
		{ 
			public Option getOption(Personne someone)
			{
				return getSomeoneMenu(someone);
			}
		};
	}
	
	private Option getSomeoneMenu(Personne someone)
	{
		Menu someoneMenu = new Menu("Option pour  "
				+someone.getPrenom()+" "+someone.getNom(),null);
		
		someoneMenu.add(VoirInfo(someone));
		someoneMenu.add(AjoutAEquipe(someone));
		someoneMenu.add(AjoutACompete(someone));
		someoneMenu.add(DesPersonneComp(someone));
		someoneMenu.add(RetirerPersEqu(someone));
		someoneMenu.add(SupprimerPersonne(someone));
		
		
		someoneMenu.setAutoBack(true);
		someoneMenu.addQuit("q");
		return someoneMenu;
	}
	
	private Option VoirInfo(Personne someone)
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
	private Option AjoutAEquipe(Personne someone)
	{
		return new Option("Integrer une equipe", "a", new Action()
		{
			@Override
			public void optionSelected()
			{

			}
		});
	}	
	private Option AjoutACompete(Personne someone)
	{
		return new Option("Inscrire a une competition", "b", new Action()
		{
			@Override
			public void optionSelected()
			{
			
			}
		});
	}	
	private Option DesPersonneComp(Personne someone)
	{
		return new Option("Desinscrire", "c", new Action()
		{
			@Override
			public void optionSelected()
			{
	
			}
		});
	}	
	private Option RetirerPersEqu(Personne someone)
	{
		return new Option("Retirer de l'equipe", "d", new Action()
		{
			@Override
			public void optionSelected()
			{
	
			}
		});
	}	
	private Option SupprimerPersonne(Personne someone)
	{
		return new Option("SuppPersonne", "e", new Action()
		{
			@Override
			public void optionSelected()
			{
				
			}
		});
	}	
	
	private Option SupprimerPersonne(String someone)
	{
		return new Option("Supp", "f", new Action()
		{
			@Override
			public void optionSelected()
			{
				personne.remove(someone);
			}
		});
	}	

}
