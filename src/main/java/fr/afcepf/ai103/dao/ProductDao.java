package fr.afcepf.ai103.dao;

import java.util.List;

import fr.afcepf.ai103.data.Produit;

/* DAO = Data Acces Object = objet de traitement spécialisé dans l'accès aux données 
avec méthodes CRUD : Create, Research, Update, Delete */

public interface ProductDao {

	public Produit insererNouveauxProduits(Produit p);

	public List<Produit> rechercherProduits();

	public void mettreAjourProduit(Produit p);

	public void supprimerProduit(Long numero);
}
