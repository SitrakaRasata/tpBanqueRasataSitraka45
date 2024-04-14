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
import mg.itu.rasatasitraka.tpbanque.service.GestionnaireCompte;

/**
 *
 * @author Sitraka Rasata
 */
@Named(value = "transfertArgent")
@ViewScoped
public class TransfertArgent implements Serializable {

    int idSource;
    int idDestination;
    int montant;

    @Inject
    private GestionnaireCompte compteManager;

    /**
     * Creates a new instance of TransfertArgent
     */
    public TransfertArgent() {
    }

    public int getIdSource() {
        return idSource;
    }

    public int getIdDestination() {
        return idDestination;
    }

    public int getMontant() {
        return montant;
    }

    public void setIdSource(int idSource) {
        this.idSource = idSource;
    }

    public void setIdDestination(int idDestination) {
        this.idDestination = idDestination;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public String transferer() {
        CompteBancaire source = compteManager.findById(idSource);
        CompteBancaire destination = compteManager.findById(idDestination);
        compteManager.transferer(source, destination, montant);

        return "listeComptes?faces-redirect=true";
    }
    
}
