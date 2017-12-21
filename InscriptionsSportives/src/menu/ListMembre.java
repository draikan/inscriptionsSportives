package menu;

import commandLineMenus.Action;
import commandLineMenus.List;
import commandLineMenus.ListData;
import commandLineMenus.ListOption;
import commandLineMenus.Menu;
import commandLineMenus.Option;

public class ListMembre {
	
	private java.util.List<String> membre;
	
	ListMembre(java.util.List<String> membre)
	{
		this.membre = membre;
		List<String> list = getCompeteList();
		list.start();
	}
	private List<String> getCompeteList()
	{
		List<String> liste = new List<>("Selectionner un membre",
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
				return membre;
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
		someoneMenu.add(SupprimerMembre(someone));
		return someoneMenu;
	}
	
	private Option SupprimerMembre(String someone)
	{
		return new Option("Supp", "a", new Action()
		{
			@Override
			public void optionSelected()
			{
				membre.remove(someone);
				System.out.println(someone + " has been deleted.");
				System.out.println(membre);
			}
		});
	}	
}
