package menu;

import commandLineMenus.Action;
import commandLineMenus.List;
import commandLineMenus.ListData;
import commandLineMenus.ListOption;
import commandLineMenus.Menu;
import commandLineMenus.Option;

public class ListCand {
	
private java.util.List<String> Candidat;
	
	ListCand(java.util.List<String> people)
	{
		this.Candidat = people;
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
				return Candidat;
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
		someoneMenu.add(SupprimerCand(someone));
		return someoneMenu;
	}
	
	private Option SupprimerCand(String someone)
	{
		return new Option("SuppCand", "s", new Action()
		{
			@Override
			public void optionSelected()
			{
				System.out.println("Entrer nom: " + someone + ".");
			}
		});
	}	

}
