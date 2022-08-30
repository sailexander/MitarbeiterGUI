package micha;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddView extends JFrame {
    String[] abteilungenArray = {"", "Abteilung 1", "Abteilung 2"};
    String[] arbeiterArray = {"", Arbeiter.SCHICHTARBEITER.toString(), Arbeiter.BÜROARBEITER.toString(), Arbeiter.MANAGER.toString()};
    JPanel schichtarbeiterPanel;
    JPanel bueroarbeiterPanel;
    JPanel managerPanel;
    JPanel midPanel;
    JComboBox<Arbeiter> typeSelector;
    JComboBox<Arbeiter> abteilungSelector;

    JTextField nameFieldText;
    JTextField addressFieldText;
    JTextField addressFieldText2;
    JTextField schichtArbeiterVerdienstText;
    JTextField bueroArbeiterFestlohnText;
    JTextField managerFestlohnText;
    JTextField managerBonusText;

    AddView() {
        /* Basics */
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 500);
        setTitle("Add new Worker");

        /* HERE COME DA BUTTONS BABY */
        JButton saveButton = new JButton("Speichern");//create button
        saveButton.addActionListener(
                event -> {
                        // TODO:
                        System.out.println("Speichern Clicked.");
                        if(verify()){
                            System.out.println("Sending the following data: ");
                            print_all();
                        }
                }
        );

        JButton abortButton = new JButton("Abbrechen");//create button
        abortButton.addActionListener(
                event -> System.exit(0)
        );

        /* Make a Flow Layout panel and put the buttons there */
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(saveButton);
        topPanel.add(abortButton);

        /* Make the "Main" Panel where we will glue everything to.*/
        JPanel mainPanel = new JPanel();
        add(mainPanel);

        // starting with the top Panel
        mainPanel.add(topPanel);

        /* Mid Panel will contain all the crap the user can select */
        midPanel = new JPanel(new GridLayout(6, 1));
        // selector Panel will gets the selectable worker and Abteilung types.
        JPanel selectorPanel = new JPanel(new FlowLayout());
        // text Panel is where we will put the user input like name, address
        JPanel textPanel = new JPanel(new GridLayout(3, 1));

        // add the user input stuff to the mid panel
        midPanel.add(selectorPanel);
        midPanel.add(textPanel);

        // add mid panel to main panel. Layout reasons
        mainPanel.add(midPanel);

        /* Create the actual input components */
        JPanel nameFieldPanel = new JPanel();
        JLabel nameFieldLabel = new JLabel("Name: ");
        nameFieldText = new JTextField(40);
        nameFieldPanel.add(nameFieldLabel);
        nameFieldPanel.add(nameFieldText);

        JPanel addressFieldPanel = new JPanel();
        JLabel addressFieldLabel = new JLabel("Straße: ");
        addressFieldText = new JTextField(40);
        addressFieldPanel.add(addressFieldLabel);
        addressFieldPanel.add(addressFieldText);

        JPanel addressFieldPanel2 = new JPanel();
        JLabel addressFieldLabel2 = new JLabel("PLZ: ");
        addressFieldText2 = new JTextField(40);
        addressFieldPanel2.add(addressFieldLabel2);
        addressFieldPanel2.add(addressFieldText2);

        // Glue them to the text Panel
        textPanel.add(nameFieldPanel);
        textPanel.add(addressFieldPanel);
        textPanel.add(addressFieldPanel2);

        /* Extra methods to create these panels. This becomes relevant later.
        * These panels have the worker type specific input (money) */
        schichtarbeiterPanel = createSchichtarbeiterPanel();
        bueroarbeiterPanel = createBueroArbeiterPanel();
        managerPanel = createManagerPanel();

        /* The worker type selector. Based on what you select it will display a different extra input. */
        typeSelector = new JComboBox(arbeiterArray);
        typeSelector.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Object selected_arbeiter = typeSelector.getSelectedItem();
                        try {
                            System.out.println(selected_arbeiter.toString());
                        }
                        catch(NullPointerException npe){
                            System.out.println("NullPointerException");
                        }
                        enableExtrapanel(selected_arbeiter);
                    }
                }
        );
        /* AbteilungsSelector doesn't actually do anything fancy. */
        abteilungSelector = new JComboBox(abteilungenArray);
        abteilungSelector.addActionListener(
                event -> {
                    // TODO:
                    System.out.println(abteilungSelector.getSelectedItem());
                }
        );

        selectorPanel.add(typeSelector, BorderLayout.CENTER);
        selectorPanel.add(abteilungSelector, BorderLayout.CENTER);

        setVisible(true);
    }
    void enableExtrapanel(Object arbeiter){
        /* Remove all - potential - panels from the layout and validate it. */
        midPanel.remove(schichtarbeiterPanel);
        midPanel.remove(bueroarbeiterPanel);
        midPanel.remove(managerPanel);
        validate();

        // Create and make visible the specific panel.
        if (arbeiter == Arbeiter.SCHICHTARBEITER.toString()){
            midPanel.add(createSchichtarbeiterPanel());
            schichtarbeiterPanel.setVisible(true);
            return;
        }
        if (arbeiter == Arbeiter.BÜROARBEITER.toString()){
            midPanel.add(createBueroArbeiterPanel());
            bueroarbeiterPanel.setVisible(true);
            return;
        }
        if (arbeiter == Arbeiter.MANAGER.toString()){
            midPanel.add(createManagerPanel());
            managerPanel.setVisible(true);
        }
    }


    /**
     * Create the panel for the Schichtarbeiter. Contains a Textfield and a label.
     * @return JPanel
     */
    JPanel createSchichtarbeiterPanel(){
        schichtarbeiterPanel = new JPanel();
        JLabel schichtarbeiterLabel = new JLabel("Schichtverdienst: ");
        schichtArbeiterVerdienstText = new JTextField(30);
        schichtarbeiterPanel.add(schichtarbeiterLabel);
        schichtarbeiterPanel.add(schichtArbeiterVerdienstText);
        schichtarbeiterPanel.setVisible(false);

        return schichtarbeiterPanel;
    }


    /**
     * Create the panel for the Büroarbeiter. Contains a Textfield and a label.
     * @return JPanel
     */
    JPanel createBueroArbeiterPanel(){
        bueroarbeiterPanel = new JPanel();
        JLabel bueroarbeiterLabel = new JLabel("Festlohn: ");
        bueroArbeiterFestlohnText = new JTextField(30);
        bueroarbeiterPanel.add(bueroarbeiterLabel);
        bueroarbeiterPanel.add(bueroArbeiterFestlohnText);
        bueroarbeiterPanel.setVisible(false);
        return bueroarbeiterPanel;
    }


    /**
     * Create the panel for the Manager. Contains two Textfields and labels.
     * @return JPanel
     */
    JPanel createManagerPanel(){
        managerPanel = new JPanel(new GridLayout(2,1));
        JPanel managerHelpPanel1 = new JPanel();
        JPanel managerHelpPanel2 = new JPanel();
        JLabel managerLabel = new JLabel("Festlohn: ");
        managerFestlohnText = new JTextField(30);
        JLabel managerLabel2 = new JLabel("Bonus: ");
        managerBonusText = new JTextField(30);
        managerHelpPanel1.add(managerLabel);
        managerHelpPanel1.add(managerFestlohnText);
        managerHelpPanel2.add(managerLabel2);
        managerHelpPanel2.add(managerBonusText);

        managerPanel.add(managerHelpPanel1);
        managerPanel.add(managerHelpPanel2);
        managerPanel.setVisible(false);
        return managerPanel;
    }


    /**
     * Make sure all the necessary information is provided by the user.
     * @return Boolean wether everything is ok or not.*/
    boolean verify(){
        if(typeSelector.getSelectedItem() == ""){
            popup("Bitte Mitarbeitertyp auswählen.");
            return false;
        }
        if(abteilungSelector.getSelectedItem() == ""){
            popup("Bitte Abteilung auswählen.");
            return false;
        }
        if(nameFieldText.getText().equals("")){
            popup("Bitte dem Mitarbeiter einen Namen geben");
            return false;
        }
        if(typeSelector.getSelectedItem() == Arbeiter.SCHICHTARBEITER.toString()){
            if(schichtArbeiterVerdienstText.getText().equals("")){
                popup("Bitte dem Mitarbeiter ein Gehalt zahlen.");
                return false;
            }
        }
        if(typeSelector.getSelectedItem() == Arbeiter.BÜROARBEITER.toString()){
            if(bueroArbeiterFestlohnText.getText().equals("")){
                popup("Bitte dem Mitarbeiter ein Gehalt zahlen.");
                return false;
            }
        }
        if(typeSelector.getSelectedItem() == Arbeiter.MANAGER.toString()){
            if(managerFestlohnText.getText().equals("")){
                popup("Bitte dem Mitarbeiter ein Gehalt zahlen.");
                return false;
            }
        }
        return true;
    }


    /**
     * Helper function to create a popup.
     * @param message What the popup will say
     */
    private void popup(String message){
        JFrame jFrame = new JFrame();
        JOptionPane.showMessageDialog(jFrame, message);
    }


    /**
     * Just print all the information that's collected.
     */
    private void print_all(){
        System.out.println("Mitarbeitertyp: " + typeSelector.getSelectedItem());
        System.out.println("Abteilung: " + abteilungSelector.getSelectedItem());
        System.out.println("Mitarbeitername: " + nameFieldText.getText());
        System.out.println("Mitarbeiteraddresse: " + addressFieldText.getText());
        System.out.println("Mitarbeiter PLZ: " + addressFieldText2.getText());
        if(typeSelector.getSelectedItem() == Arbeiter.MANAGER.toString()) {
            System.out.println("Festgehalt: " + managerFestlohnText.getText());
            System.out.println("Bonus Gehalt: " + managerBonusText.getText());
        }
        if(typeSelector.getSelectedItem() == Arbeiter.BÜROARBEITER.toString()) {
            System.out.println("Festgehalt: " + bueroArbeiterFestlohnText.getText());
        }
        if(typeSelector.getSelectedItem() == Arbeiter.SCHICHTARBEITER.toString()) {
            System.out.println("Stundenlohn: " + schichtArbeiterVerdienstText.getText());
        }
    }
}
