package menu;

import commandLineMenus.Action;
import commandLineMenus.List;
import commandLineMenus.ListData;
import commandLineMenus.ListOption;
import commandLineMenus.Menu;
import commandLineMenus.Option;

public class ListParticipantNonInscrit {
	
	private java.util.List<String> partinoninscrit;
	
	ListParticipantNonInscrit(java.util.List<String> people)
	{
		this.partinoninscrit = people;
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
				return partinoninscrit;
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
		someoneMenu.add(AjouterParticipant(someone));
		return someoneMenu;
	}
	
	private Option AjouterParticipant(String someone)
	{
		return new Option("ModNOM", "a", new Action()
		{
			@Override
			public void optionSelected()
			{
				System.out.println("Entrer nom: " + someone + ".");
			}
		});
	}	
}
