package fr.afcepf.ai103.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.afcepf.ai103.data.Produit;

public class ProductDaoSimu implements ProductDao {

	private Map<Long, Produit> mp = new HashMap<Long, Produit>();
	private Long lastNumero;
	
	ProductDaoSimu(){
		mp.put(1L, new Produit(1L, "p1", 3.5));
		mp.put(1L, new Produit(2L, "p2", 1.5));
		lastNumero=2L;
	}
	
	@Override
	public Produit insererNouveauxProduits(Produit p) {
		lastNumero++;
		p.setNumero(lastNumero);
		mp.put(lastNumero, p);
		return p;
		//test
	}

	@Override
	public List<Produit> rechercherProduits() {
		return new ArrayList<>(mp.values());
	}

	@Override
	public void mettreAjourProduit(Produit p) {
		mp.put(p.getNumero(), p);
	}

	@Override
	public void supprimerProduit(Long numero) {
		mp.remove(numero);
	}

}
