package menu;

import commandLineMenus.Action;
import commandLineMenus.Menu;
import commandLineMenus.Option;
import commandLineMenus.rendering.examples.util.InOut;

public class FinalMenu {

	static Menu getMainMenu()
	{
		Menu mainMenu = new Menu("Menu Complet");
		
		// Adds a submenu or an option is then done 
		// with a method call
		mainMenu.add(CompeteMenu());
		mainMenu.add(EquipeMenu());
		mainMenu.add(PersonneMenu());
		mainMenu.addQuit("q");
		return mainMenu;
	}
	
	static Option CompeteMenu()
	{
		Option Creecompete = new Option("Creér Competition", "c", 
				creercompetionAction());
		Option Modifcompete = new Option("Modifier Competition", "m", 
				ModifiercompetionOption());
		return Creecompete;
	}
	
	static Action creercompetionAction()
	{
		return new Action()
		{
			public void optionSelected()
			{
				String a = InOut.getString("Le nom de la competition : "),
						b = InOut.getString("Date de debut de la compete : "),
						c = InOut.getString("Date de fin d'inscription : "),
						d = InOut.getString("Type de compete : ");
				System.out.println("Le nom de la competition :" + a );
				System.out.println("Date de debut de la compete :" +b );
				System.out.println("Date de fin d'inscription :" + c );
				System.out.println("Type de compete :" + d );
			}
		};
	}
}
