/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.rasatasitraka.tpbanque.jsf;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.inject.Inject;
import mg.itu.rasatasitraka.tpbanque.entity.CompteBancaire;
import mg.itu.rasatasitraka.tpbanque.jsf.util.Util;
import mg.itu.rasatasitraka.tpbanque.service.GestionnaireCompte;

/**
 *
 * @author Sitraka Rasata
 */
@Named(value = "ajoutCompte")
@RequestScoped
public class AjoutCompte {

    String nom;
    int solde;

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    /**
     * Creates a new instance of AjoutCompte
     */
    public AjoutCompte() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public String action() {
        boolean erreur = false;
        CompteBancaire nouveauCompte = new CompteBancaire(nom, solde);

        if (solde < 0) {
            Util.messageErreur("Solde saisi invalide", "Solde saisi invalide", "form:solde");
            erreur = true;
        }

        if (erreur) {
            return null;
        }

        gestionnaireCompte.creerCompte(nouveauCompte);

        String message = new StringBuilder()
                .append("Compte Bancaire n°")
                .append(nouveauCompte.getId())
                .append(" créé avec succès")
                .toString();
        Util.addFlashInfoMessage(message);

        return "listeComptes?faces-redirect=true";
    }
}
