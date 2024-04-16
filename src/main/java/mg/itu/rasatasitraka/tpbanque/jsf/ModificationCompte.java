/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.rasatasitraka.tpbanque.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import mg.itu.rasatasitraka.tpbanque.entity.CompteBancaire;
import mg.itu.rasatasitraka.tpbanque.jsf.util.Util;
import mg.itu.rasatasitraka.tpbanque.service.GestionnaireCompte;

/**
 * Backing bean pour la page qui effectue la modification d'un compte (le nom)
 *
 * @author Sitraka Rasata
 */
@Named(value = "modificationCompte")
@ViewScoped
public class ModificationCompte implements Serializable {

    private Long id;
    private String nom;
    CompteBancaire compte;


    @Inject
    private GestionnaireCompte compteManager;

    /**
     * Creates a new instance of ModificationCompte
     */
    public ModificationCompte() {
    }

    public void loadCompte() {
        compte = compteManager.findById(id);
        this.setNom(nom = compte.getNom());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CompteBancaire getCompte() {
        return compte;
    }

    public void setCompte(CompteBancaire compte) {
        this.compte = compte;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String modifier() {

        String ancienNom = compte.getNom();
        compte.setNom(nom);
        compteManager.update(compte);

        String message = new StringBuilder()
                .append("Le nom du compte n°")
                .append(compte.getId())
                .append(" modifié avec succès de ")
                .append(ancienNom)
                .append(" à ")
                .append(nom)
                .toString();

        Util.addFlashInfoMessage(message);
        return "listeComptes?faces-redirect=true";
    }

}
