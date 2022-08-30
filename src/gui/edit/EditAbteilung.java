package gui.edit;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mitarbeiter.Manager;
import verwaltung.Abteilung;
import verwaltung.Verwaltung;

public class EditAbteilung extends JFrame{

	private Verwaltung verwaltung;
	private Abteilung abteilung;
	private ArrayList<Manager> managers;
	
	private JPanel mainPanel;
	private JPanel optionPanel;
	private JPanel buttonPanel;
	
	//neue Abteilung anlegen
	public EditAbteilung(Verwaltung verwaltung) {
		this.verwaltung = verwaltung;
		this.managers = new ArrayList<Manager>();
		
		this.initFrame();
	}
	
	//existierende Abteilung bearbeiten
	public EditAbteilung(Verwaltung verwaltung, Abteilung abteilung) {
		this(verwaltung);
		this.abteilung = abteilung;
	}
	
	public void initFrame() {
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setSize(600, 500);
        setTitle("Edit Abteilung");
        
        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(new BoxLayout(this.mainPanel, BoxLayout.PAGE_AXIS));
        this.add(this.mainPanel);
        
        this.optionPanel = new JPanel();
        this.mainPanel.add(this.optionPanel);
        JLabel label = new JLabel("label");
        JTextField text = new JTextField(40);
        this.optionPanel.add(label);
        this.optionPanel.add(text);
        
        this.buttonPanel = new JPanel();
        this.mainPanel.add(this.buttonPanel);
        
        
        this.setVisible(true);
	}
}
