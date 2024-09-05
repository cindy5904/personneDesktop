package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        PersonneService personneService = new PersonneService();

        JFrame frame = new JFrame("Gestion des Personnes");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(10, 20, 80, 25);
        panel.add(idLabel);

        JTextField idText = new JTextField(20);
        idText.setBounds(100, 20, 165, 25);
        panel.add(idText);


        JLabel prenomLabel = new JLabel("Prénom:");
        prenomLabel.setBounds(10, 50, 80, 25);
        panel.add(prenomLabel);

        JTextField prenomText = new JTextField(20);
        prenomText.setBounds(100, 50, 165, 25);
        panel.add(prenomText);


        JLabel telephoneLabel = new JLabel("Téléphone:");
        telephoneLabel.setBounds(10, 80, 80, 25);
        panel.add(telephoneLabel);

        JTextField telephoneText = new JTextField(20);
        telephoneText.setBounds(100, 80, 165, 25);
        panel.add(telephoneText);


        JButton ajouterButton = new JButton("Ajouter");
        ajouterButton.setBounds(10, 110, 100, 25);
        panel.add(ajouterButton);

        JButton modifierButton = new JButton("Modifier");
        modifierButton.setBounds(120, 110, 100, 25);
        panel.add(modifierButton);

        JButton supprimerButton = new JButton("Supprimer");
        supprimerButton.setBounds(230, 110, 100, 25);
        panel.add(supprimerButton);

        JButton afficherButton = new JButton("Afficher");
        afficherButton.setBounds(340, 110, 100, 25);
        panel.add(afficherButton);

        JTextArea affichageTextArea = new JTextArea();
        affichageTextArea.setBounds(10, 150, 450, 200);
        panel.add(affichageTextArea);


        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String prenom = prenomText.getText();
                String telephone = telephoneText.getText();
                personneService.ajouterPersonne(prenom, telephone);
                personneService.afficherPersonnes(affichageTextArea);
            }
        });


        modifierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long id = Long.parseLong(idText.getText());
                String prenom = prenomText.getText();
                String telephone = telephoneText.getText();
                personneService.modifierPersonne(id, prenom, telephone);
                personneService.afficherPersonnes(affichageTextArea);
            }
        });


        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long id = Long.parseLong(idText.getText());
                personneService.supprimerPersonne(id);
                personneService.afficherPersonnes(affichageTextArea);
            }
        });


        afficherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                personneService.afficherPersonnes(affichageTextArea);
            }
        });


        personneService.afficherPersonnes(affichageTextArea);

        frame.setVisible(true);
    }
}