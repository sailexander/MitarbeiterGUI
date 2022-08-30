package micha;

import mitarbeiter.Manager;
import mitarbeiter.Mitarbeiter;
import mitarbeiter.SchichtArbeiter;
import verwaltung.Abteilung;
import verwaltung.Verwaltung;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Verwaltung verwaltung = new Verwaltung();

        AddView addview = new AddView(
                verwaltung.getAbteilungen().get(0).getMitarbeiterAsList().get(1),
                verwaltung.getAbteilungen().get(0),
                verwaltung
        );
    }
}

