package menu;

import java.util.ArrayList;

import commandLineMenus.List;
import commandLineMenus.ListOption;
import commandLineMenus.Option;

public class Menu {
	java.util.List<String> people;
	
	public Menu(java.util.List<String> people)
	{
		this.people = people;
		List<String> list = getPeopleList(people);
		list.start();
	}
	
	private List<String> getPeopleList(final java.util.List<String> people)
	{
		List<String> liste = new List<>("Select someone to display his name",
				() -> people, 
				getListOptionSomeone()
				);
		liste.setAutoBack(false);
		liste.addQuit("q");
		return liste;
	}
	
	private ListOption<String> getListOptionSomeone()
	{
		return (String someone) -> new Option("Display " + someone, null, () -> System.out.println(someone));
	}
	
	public static void main(String[] args)
	{
		java.util.List<String> people = new ArrayList<>();
		people.add("Ginette");
		people.add("Marcel");
		people.add("Gisèle");
		new Menu(people);
	}
}
