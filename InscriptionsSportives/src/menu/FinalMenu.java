package menu;

import commandLineMenus.Action;
import commandLineMenus.Menu;
import commandLineMenus.Option;
import commandLineMenus.rendering.examples.util.InOut;

public class FinalMenu {
	
	//public java.util.List<String> compete;
	//public java.util.List<String> personne;
	//public java.util.List<String> equipe;

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
	
	static Option PersonneMenu()
	{
		Option Personne = new Option("Inserer Personne", "c", 
				insererPersonneAction());
		Option Modifpersonne = new Option("Modifier Personne", "m", 
				ModifierpersonneOption());
		return Personne;
	}
	
	static Option EquipeMenu()
	{
		Option Creeequipe = new Option("Creér Equipe", "c", 
				creerequipeAction());
		Option Modifequipe = new Option("Modifier Equipe", "m", 
				ModifierequipeOption());
		return Creeequipe;
	}
	
	static Option CompeteMenu()
	{
		Option Creecompete = new Option("Creér Competition", "c", 
				creercompetionAction());
		Option Modifcompete = new Option("Modifier Competition", "m", 
				ModifiercompetionOption());
		return Creecompete;
	}
	
	static Action insererPersonneAction()
	{
		return new Action()
		{
			public void optionSelected()
			{
				String a = InOut.getString("Le nom de la personne : "),
						b = InOut.getString("Le prenom : ");
				System.out.println("Le nom de la competition :" + a );
				System.out.println("Date de debut de la compete :" +b );
				
				//a completer
			}
		};
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
	
	static Action creerequipeAction()
	{
		return new Action()
		{
			public void optionSelected()
			{
				String a = InOut.getString("Le nom de l'equipe : ");
				System.out.println("Le nom de l'equipe :" + a );
			}
		};
	}
	
	static Action ModifierequipeOption()
	{
		return new Action()
		{
			@Override
			public void optionSelected() {
				listeequipe(equipe);
					
		}
	};
	}
	
	static Action ModifiercompetionOption()
	{
		return new Action()
		{
			@Override
			public void optionSelected() {
				//ListCompete(compete);
				ListCompete newcompete = new ListCompete(compete);
					
		}
	};
	}
	
	static Action ModifierpersonneOption()
	{
		return new Action()
		{
			@Override
			public void optionSelected() {
				Listpersonne(personne);
					
		}
	};
	}
}
