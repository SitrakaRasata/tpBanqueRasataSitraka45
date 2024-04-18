/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.rasatasitraka.tpbanque.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;
import mg.itu.rasatasitraka.tpbanque.entity.CompteBancaire;
import mg.itu.rasatasitraka.tpbanque.entity.OperationBancaire;
import mg.itu.rasatasitraka.tpbanque.service.GestionnaireCompte;

/**
 *
 * @author ix0017
 */
@Named(value = "operations")
@ViewScoped
public class Operations implements Serializable {

    private long id;

    private CompteBancaire compte;
    
    private List<OperationBancaire> listeOperations;

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    public void chargerCompte() {
        compte = gestionnaireCompte.findById(id);
        listeOperations = compte.getOperations();
    }

    /**
     * Creates a new instance of Operations
     */
    public Operations() {
    }

    public CompteBancaire getCompte() {
        return compte;
    }

    public List<OperationBancaire> getListeOperations() {
        return listeOperations;
    }

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public long getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(long id) {
        this.id = id;
    }

}
