package mitarbeiter;

import java.io.Serializable;

public abstract class Mitarbeiter implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	// Deklaration der Signatur "Ich weiß nicht wie, sondern nur dass..."
	// abstract bedeutet Grundprinzip der Methode (Signatur)
	// wird ohne Inhalt deklariert

	public abstract double einkommen();

	public Mitarbeiter(int id, String name) throws IllegalArgumentException {
		this.setID(id);
		// this.id=id;
		this.setName(name);
	}

	/*
	 * Kopierkonstruktor erstellt einen Klon
	 */
	public Mitarbeiter(Mitarbeiter original) {
		this.name = original.getName();
		this.id = original.getID();
	}

	protected void setID(int id) throws IllegalArgumentException {
		if (id > 999 && id < 10000)
			this.id = id;
		else
			throw new IllegalArgumentException("id ist nicht im gültigen Bereich");

	}

	public int getID() {
		return this.id;
	}

	public void setName(String name) {
		String erlaubt = "";
		boolean anfangName = true;

		for (int i = 0; i < name.length(); i++) {
			if (name.charAt(i) >= 'a' && name.charAt(i) <= 'z') {
				if (anfangName) {
					erlaubt += (char) (name.charAt(i) + ('A' - 'a'));
					anfangName = false;
				} else {
					erlaubt += name.charAt(i);
				}
			}else
				if (name.charAt(i) == ' ') {
					anfangName = true;
					erlaubt += name.charAt(i);
			} else if (name.charAt(i) >= 'A' && name.charAt(i) <= 'Z') {
				if (anfangName) {
					erlaubt += name.charAt(i);
					anfangName = false;
				} else {
					erlaubt += (char) (name.charAt(i) - ('A' - 'a'));
				}
			}
		}if(erlaubt.length()>1)this.name=erlaubt;else

	{
		throw new IllegalArgumentException("Name ist zu kurz oder enthält falche Zeichen");
	}
	}

	public String getName() {
		return this.name;
	}

	public String toString() {
		return ("\nID: " + id + "\nName: " + name);
	}

}
