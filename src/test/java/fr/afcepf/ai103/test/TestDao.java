
package fr.afcepf.ai103.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.afcepf.ai103.dao.ProductDao;
import fr.afcepf.ai103.dao.ProductDaoJdbc;
import fr.afcepf.ai103.data.Produit;

public class TestDao {

	private ProductDao productDao = null;

	@Before
	public void initDao() {
		this.productDao = new ProductDaoJdbc();

	}

	@Test
	public void testCRUD() {

		Produit nouveauProd = new Produit(null, "nouveau produit que j'aime", 2.56);
		nouveauProd = productDao.insererNouveauxProduits(nouveauProd);
		System.out.println("numero du produit insere=" + nouveauProd.getNumero());

		Produit produit2Modifie = new Produit(2L, "super super stylo", 4.23);
		productDao.mettreAjourProduit(produit2Modifie);

		Long numDeleted = 6L;
		System.out.println("avant suppression du numero " + numDeleted);
		affListProd(productDao);

		productDao.supprimerProduit(numDeleted);

		System.out.println("apres suppression du numero " + numDeleted);
		affListProd(productDao);
	}

	public static void affListProd(ProductDao productDao) {
		List<Produit> listeProd = productDao.rechercherProduits();
		if (listeProd != null)
			for (Produit p : listeProd) {
				System.out.println(p);
			}

	}
}
