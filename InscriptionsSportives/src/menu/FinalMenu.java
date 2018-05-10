package menu;

import java.util.ArrayList;

import commandLineMenus.Action;
import commandLineMenus.Menu;
import commandLineMenus.Option;
import commandLineMenus.rendering.examples.util.InOut;
import Application.Inscriptions;
import javafx.application.Application;



public class FinalMenu {
	
	private static Menu mainMenu;
	private static  Menupersonne Mpers;
	private static MenuCompete Mcomp;
	private static MenuEquipe Meq;
	private static Inscriptions inscriptions;

	public FinalMenu(){
		inscriptions = Inscriptions.getInscriptions();
		mainMenu = getMainMenu();
		Mpers = new Menupersonne() ;
		Mcomp = new MenuCompete();
		Meq = new MenuEquipe();
	}

	
	public static void main(String[] args)
	{
		Inscriptions.start();
		Inscriptions.importDataFromDatabase();
		FinalMenu menu = new FinalMenu();
		menu.start();
	}
	
	public void start()
	{
		mainMenu.start();
	}
	
	public Inscriptions getInscriptions()
	{
		return inscriptions;
	}
	

	static Menu getMainMenu()
	{
		Menu mainMenu = new Menu("Menu Complet");
		mainMenu.add(Mpers.getMenuPersonne(inscriptions));
		mainMenu.add(Meq.getMenuEquipe());
		mainMenu.add(Mcomp.getMenuComp());
		mainMenu.addQuit("q");
		return mainMenu;
	}
	
	static Option PersonneMenu()
	{
		Option Personne = new Option("Inserer Personne", "ip", 
				insererPersonneAction());
		Option Modifpersonne = new Option("Modifier Personne", "mp", 
				ModifierpersonneOption());
		return Personne;
	}
	
	static Option EquipeMenu()
	{
		Option Creeequipe = new Option("Creer Equipe", "ce", 
				creerequipeAction());
		Option Modifequipe = new Option("Modifier Equipe", "me", 
				ModifierequipeOption());
		return Creeequipe;
	}
	
	static Option CompeteMenu()
	{
		Option Creecompete = new Option("Creer Competition", "cc", 
				creercompetionAction());
		Option Modifcompete = new Option("Modifier Competition", "mc", 
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
				listeequipe();
					
		}

			private void listeequipe() {
				
				
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
				 ListCompete();
					
		}
			private void ListCompete() {
				
			}
	};
	}
	
	static Action ModifierpersonneOption()
	{
		return new Action()
		{
			@Override
			public void optionSelected() {
				Listpersonne();
					
		}
			private void Listpersonne() {
				
				
			}
	};
	}
}
