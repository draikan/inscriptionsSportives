package menu;

import commandLineMenus.Action;
import commandLineMenus.List;
import commandLineMenus.ListData;
import commandLineMenus.ListOption;
import commandLineMenus.Menu;
import commandLineMenus.Option;

public class Listpersonne {
	
private java.util.List<String> personne;
	
	Listpersonne(java.util.List<String> personne)
	{
		this.personne = personne;
		List<String> list = getCompeteList();
		list.start();
	}
	private List<String> getCompeteList()
	{
		List<String> liste = new List<>("Selectionner une personne",
				getListCompetition(),
				getOptionListeCompetition());
		liste.setAutoBack(false);
		liste.addQuit("q");
		return liste;
	}
	
	private ListData<String> getListCompetition()
	{
		return new ListData<String>()
		{
			@Override
			public java.util.List<String> getList()
			{
				return personne;
			}
		};
	}
	
	private ListOption<String> getOptionListeCompetition()
	{
		return new ListOption<String>()
		{ 
			public Option getOption(String someone)
			{
				return getSomeoneMenu(someone);
			}
		};
	}
	
	private Option getSomeoneMenu(final String someone)
	{
		Menu someoneMenu = new Menu(someone);
		someoneMenu.add(SupprimerPersonne(someone));
		someoneMenu.add(ModNom(someone));
		someoneMenu.add(ModPrenom(someone));
		//a completer
		return someoneMenu;
	}
	
	private Option ModNom(String someone)
	{
		return new Option("ModNOM", "n", new Action()
		{
			@Override
			public void optionSelected()
			{
				System.out.println("Entrer nouveau nom: " + someone + ".");
			}
		});
	}
	private Option ModPrenom(String someone)
	{
		return new Option("ModPrenom", "p", new Action()
		{
			@Override
			public void optionSelected()
			{
				System.out.println("Entrer nouveau prenom: " + someone + ".");
			}
		});
	}
	
	private Option SupprimerPersonne(String someone)
	{
		return new Option("Supp", "a", new Action()
		{
			@Override
			public void optionSelected()
			{
				personne.remove(someone);
				System.out.println(someone + " has been deleted.");
				System.out.println(personne);
			}
		});
	}	

}
