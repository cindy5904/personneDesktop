package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import java.util.List;

public class PersonneService {
    public void ajouterPersonne(String prenom, String telephone) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Personne personne = new Personne(prenom, telephone);
            session.save(personne);
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Personne ajoutée avec succès !");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public void modifierPersonne(Long id, String nouveauPrenom, String nouveauTelephone) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Personne personne = session.get(Personne.class, id);
            if (personne != null) {
                personne.setPrenom(nouveauPrenom);
                personne.setTelephone(nouveauTelephone);
                session.update(personne);
                transaction.commit();
                JOptionPane.showMessageDialog(null, "Personne modifiée avec succès !");
            } else {
                JOptionPane.showMessageDialog(null, "Personne introuvable.");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public void supprimerPersonne(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Personne personne = session.get(Personne.class, id);
            if (personne != null) {
                session.delete(personne);
                transaction.commit();
                JOptionPane.showMessageDialog(null, "Personne supprimée avec succès !");
            } else {
                JOptionPane.showMessageDialog(null, "Personne introuvable.");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public void afficherPersonnes(JTextArea textArea) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Personne> personnes = session.createQuery("from Personne", Personne.class).list();
            textArea.setText("");
            for (Personne personne : personnes) {
                textArea.append("ID: " + personne.getId() + " - Prénom: " + personne.getPrenom() + " - Téléphone: " + personne.getTelephone() + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
