package menu;

import commandLineMenus.Action;
import commandLineMenus.List;
import commandLineMenus.ListData;
import commandLineMenus.ListOption;
import commandLineMenus.Menu;
import commandLineMenus.Option;

public class ListCompete {
	
	
	private java.util.List<String> compete;
	
	ListCompete(java.util.List<String> compete)
	{
		this.compete = compete;
		List<String> list = getCompeteList();
		list.start();
	}
	private List<String> getCompeteList()
	{
		List<String> liste = new List<>("Selectionner une competition",
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
				return compete;
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
		someoneMenu.add(ModNomcompeteAction(someone));
		someoneMenu.add(ModDatecompeteAction(someone));
		someoneMenu.add(AjoutCandcompeteAction(someone));
		someoneMenu.add(SuppcompeteAction(someone));
		someoneMenu.add(SuppCandAction(someone));
		someoneMenu.setAutoBack(true);
		return someoneMenu;
	}
	
	private Option ModNomcompeteAction(String someone)
	{
		return new Option("ModNOM", "n", new Action()
		{
			@Override
			public void optionSelected()
			{
				System.out.println("Entrer votre nouveau nom: " + someone + ".");
			}
		});
	}
	
	private Option ModDatecompeteAction(String someone)
	{
		return new Option("ModDate", "d", new Action()
		{
			@Override
			public void optionSelected()
			{
				System.out.println("Entrer la nouvelle date: " + someone + ".");
			}
		});
	}
	
	private Option AjoutCandcompeteAction(String someone)
	{
		return new Option("AjoutCand", "a", new Action()
		{
			@Override
			public void optionSelected()
			{
				System.out.println("Ajouter un candidat " + someone + ".");
			}
		});
	}
	
	private Option SuppcompeteAction(String someone)
	{
		return new Option("SuppCompet", "c", new Action()
		{
			@Override
			public void optionSelected()
			{
				System.out.println("Ajouter un candidat " + someone + ".");
			}
		});
	}
	
	private Option SuppCandAction(String someone)
	{
		return new Option("SuppCand", "s", new Action()
		{
			@Override
			public void optionSelected()
			{
				System.out.println("Ajouter un candidat " + someone + ".");
			}
		});
	}

	
	
	
}
