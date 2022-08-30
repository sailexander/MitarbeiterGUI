package verwaltung;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import mitarbeiter.Mitarbeiter;

public class Verwaltung {

	private final String DATA_FILE = "data/mitarbeiter_verwaltung";
	private ArrayList<Abteilung> abteilungen;
	
	public Verwaltung() {
		this.abteilungen = new ArrayList<Abteilung>();
		this.loadData();
	}
	
	public ArrayList<Abteilung> getAbteilungen() {
		return this.abteilungen;
	}
	
	public int getAnzahlAbteilungen() {
		return this.abteilungen.size();
	}
	
	public void addAbteilung(Abteilung neu) {
		if(!this.abteilungen.contains(neu)) {
			this.abteilungen.add(neu);
		}
	}
	
	public void removeAbteilung(Abteilung target) {
		this.abteilungen.remove(target);
	}
	
	public void loadData() {
		try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(this.DATA_FILE));
            this.abteilungen = (ArrayList<Abteilung>) in.readObject();
            in.close();
            System.out.println(this.getAnzahlAbteilungen() + " Abteilungen geladen.");
        } catch(IOException ex){
            ex.printStackTrace();
        } catch(ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        }
	}
	
	public void saveData() {
		if (this.abteilungen.isEmpty()) {
			System.out.println("Es gibt keine Abteilungen zu speichern.");
			return;
		}
		
		try {
			System.out.print("Speichere " + this.getAnzahlAbteilungen() + " Abteilungen ... ");
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(this.DATA_FILE));
			out.writeObject(this.abteilungen);
			out.close();
			System.out.println("Done!");
		} catch(IOException ex){
            ex.printStackTrace();
        }
	}
}
