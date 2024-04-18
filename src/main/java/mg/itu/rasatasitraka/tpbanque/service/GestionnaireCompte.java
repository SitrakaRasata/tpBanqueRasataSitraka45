/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.rasatasitraka.tpbanque.service;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;
import mg.itu.rasatasitraka.tpbanque.entity.CompteBancaire;

/**
 *
 * @author Sitraka Rasata
 */
@DataSourceDefinition(
        className = "com.mysql.cj.jdbc.MysqlDataSource",
        name = "java:app/jdbc/banque",
        serverName = "localhost",
        portNumber = 3306,
        user = "sitraka_rasata_45",
        password = "root",
        databaseName = "banque",
        properties = {
            "useSSL=false",
            "allowPublicKeyRetrieval=true",
            "driverClass=com.mysql.cj.jdbc.Driver"
        }
)
@Stateless
public class GestionnaireCompte {

    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;

    @Transactional
    public void creerCompte(CompteBancaire compteBancaire) {
        em.persist(compteBancaire);
    }

    public List<CompteBancaire> getAllComptes() {
        TypedQuery<CompteBancaire> query = em.createNamedQuery("CompteBancaire.findAll", CompteBancaire.class);
        return query.getResultList();
    }

    public long nbComptes() {
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(c) FROM CompteBancaire c", Long.class);
        return query.getSingleResult();
    }

    public CompteBancaire findById(long id) {
        return em.find(CompteBancaire.class, id);
    }

    @Transactional
    public CompteBancaire update(CompteBancaire compte) {
        return em.merge(compte);
    }

    @Transactional
    public void transferer(CompteBancaire source, CompteBancaire destination,
            int montant) {
        source.retirer(montant);
        destination.deposer(montant);
        update(source);
        update(destination);
    }

    @Transactional
    public void supprimerCompte(CompteBancaire compte) {
        em.remove(em.merge(compte));
    }

    /**
     * Dépôt d'argent sur un compte bancaire.
     *
     * @param compteBancaire
     * @param montant
     */
    @Transactional
    public void deposer(CompteBancaire compteBancaire, int montant) {
        compteBancaire.deposer(montant);
        update(compteBancaire);
    }

    /**
     * Retrait d'argent sur un compte bancaire.
     *
     * @param compteBancaire
     * @param montant
     */
    @Transactional
    public void retirer(CompteBancaire compteBancaire, int montant) {
        compteBancaire.retirer(montant);
        update(compteBancaire);
    }
}
