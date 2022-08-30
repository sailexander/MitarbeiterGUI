
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import mitarbeiter.*;
import verwaltung.Abteilung;
import verwaltung.Verwaltung;

public class MitarbeiterMain {

	public static void main(String[] args) {
		//testBasics();
		//testWithAbteilung();
		//erstelleVerwaltung();
		//ladeVerwaltung();
		findeManager();
	}

	public static void testBasics() {
		ArrayList<Mitarbeiter> alleMitarbeiter = new ArrayList<>();
		alleMitarbeiter.add(new Manager(333333, "Pia", 10000, 0.3));
		alleMitarbeiter.add(new BueroArbeiter(456789, "Horst", 1300));
		SchichtArbeiter mal = new SchichtArbeiter(121212, "Yan", 15);
		mal.arbeite(43 * 4);
		alleMitarbeiter.add(mal);
		Fahrer fred = new Fahrer(456, "Fred", 23.5, "C");
		fred.arbeite(60 * 4);
		alleMitarbeiter.add(fred);

		System.out.println("Test Methode toString()");
		for (int i = 0; i < alleMitarbeiter.size(); i++) {
			System.out.print(alleMitarbeiter.get(i).toString());
		}
		System.out.println("\n\nTest Methode einkommen()");
		for (Mitarbeiter m : alleMitarbeiter) {
			System.out.print(m.getName() + " vom Typ \"");
			System.out.print(m.getClass() + "\" hat ");
			System.out.println("Einkommen: " + m.einkommen());
		}
		double lohnsumme = 0, durchschnitt = 0;
		for (Mitarbeiter m : alleMitarbeiter) {
			lohnsumme += m.einkommen();
		}
		durchschnitt = lohnsumme / alleMitarbeiter.size();
		System.out.println("Lohnsumme: " + lohnsumme);
		System.out.println("Durchschnittlicher Lohn: " + durchschnitt);

	}

	public static void testWithAbteilung() {
		Abteilung it = new Abteilung("IT-Abteilung", new Manager(444444, "Paula", 10000, 0.05));
		it.addMitarbeiter(new Manager(333333, "Pia", 10000, 0.3));
		it.addMitarbeiter(new BueroArbeiter(456789, "Horst", 1300));
		SchichtArbeiter mal = new SchichtArbeiter(121212, "Yan", 15);
		mal.arbeite(43 * 4);
		it.addMitarbeiter(mal);
		Fahrer fred = new Fahrer(456, "Fred", 23.5, "C");
		fred.arbeite(60 * 4);
		it.addMitarbeiter(fred);

		System.out.println("Test gehaltsListe");
		System.out.println(it.gehaltsListe());
		
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("testfile"));
			out.writeObject(it);
			out.close();
		} catch(IOException ex){
            ex.printStackTrace();
        }
		
		Abteilung itNeu = null;
		
		try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("testfile"));
            itNeu = (Abteilung)in.readObject();
            in.close();
        } catch(IOException ex){
            ex.printStackTrace();
        } catch(ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        }
		
		System.out.println("\nTest2 gehaltsListe");
		System.out.println(itNeu.gehaltsListe());
		
		ArrayList<Mitarbeiter> mitarbeiterList = itNeu.getMitarbeiterAsList();
		Mitarbeiter m = mitarbeiterList.get(0);
		m.setName("test");
		
		System.out.println("\nTest2 gehaltsListe");
		System.out.println(itNeu.gehaltsListe());
	}

	public static void erstelleVerwaltung() {
		Abteilung it = new Abteilung("IT-Abteilung", new Manager(444444, "Paula", 10000, 0.05));
		it.addMitarbeiter(new Manager(333333, "Pia", 10000, 0.3));
		it.addMitarbeiter(new BueroArbeiter(456789, "Horst", 1300));
		SchichtArbeiter mal = new SchichtArbeiter(121212, "Yan", 15);
		mal.arbeite(43 * 4);
		it.addMitarbeiter(mal);
		Fahrer fred = new Fahrer(456, "Fred", 23.5, "C");
		fred.arbeite(60 * 4);
		it.addMitarbeiter(fred);
		
		Abteilung support = new Abteilung("Kundensupport", new Manager(444445, "Thomas", 10000, 0.05));
		support.addMitarbeiter(new Manager(33334, "Christian", 10000, 0.3));
		support.addMitarbeiter(new BueroArbeiter(456790, "Peter", 1300));
		SchichtArbeiter alex = new SchichtArbeiter(121213, "Alex", 15);
		alex.arbeite(43 * 4);
		support.addMitarbeiter(alex);
		Fahrer johann = new Fahrer(457, "Johann", 23.5, "C");
		johann.arbeite(60 * 4);
		support.addMitarbeiter(fred);
		
		Verwaltung verwaltung = new Verwaltung();
		verwaltung.addAbteilung(it);
		verwaltung.addAbteilung(support);
		verwaltung.saveData();

	}
	
	public static void ladeVerwaltung() {
		Verwaltung verwaltung = new Verwaltung();
	}
	
	public static void findeManager() {
		Verwaltung verwaltung = new Verwaltung();
		
		ArrayList<Manager> managers = verwaltung.getManagers();
		
		managers.forEach((manager) -> System.out.println(manager.getID() + " " + manager.getName()));
		
		verwaltung.removeMitarbeiter(managers.get(1));
		managers = verwaltung.getManagers();
		
		managers.forEach((manager) -> System.out.println(manager.getID() + " " + manager.getName()));
	}
}
