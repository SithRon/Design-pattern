package fr.afcepf.ai103.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import fr.afcepf.ai103.data.Produit;
import fr.afcepf.ai103.datasource.SimpleDataSource;

public class ProductDaoJdbc implements ProductDao {

	private Connection etablirConnection() throws SQLException{
		//version temporaire sans factory
		DataSource ds = new SimpleDataSource();
		return ds.getConnection();
	}

	public static Long getAutoIncrPk(PreparedStatement pst) {

		Long pk = null;
		try {
			ResultSet rsKeys = pst.getGeneratedKeys(); // récupérer valeur clef primaire
			if (rsKeys.next()) {
				pk = rsKeys.getLong(1);
			}
			rsKeys.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pk;
	}

	@Override
	public Produit insererNouveauxProduits(Produit p) {
		Connection cn = null;
		try {
			cn = etablirConnection();

			PreparedStatement pst = cn.prepareStatement("insert into Produit (label, prix) values (?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
			pst.setString(1, p.getLabel());
			pst.setDouble(2, p.getPrix()); // indiquer la valeur du deuxième (car 2) "?"
			pst.executeUpdate();
			p.setNumero(getAutoIncrPk(pst)); // mettre à jour la partie numero du produit (null --> auto_increment pk)
			pst.close();
			/*
			 * Statement st = cn.createStatement(); // déclaration statement String reqSQL =
			 * "insert into Produit(label, prix) values('" + p.getLabel() + "'," +
			 * p.getPrix() + ")"; System.out.println(reqSQL); st.executeUpdate(reqSQL); //
			 * execution statement st.close(); // fermeture statement
			 */
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeCn(cn);
		}
		return p;
	}

	@Override
	public List<Produit> rechercherProduits() {
		List<Produit> listeProduits = new ArrayList<Produit>();
		Connection cn = null;
		try {
			cn = etablirConnection();
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("select * from Produit"); // récupère lignes
			while (rs.next()) {
				Produit prod = new Produit();
				prod.setNumero(rs.getLong("numero"));
				prod.setLabel(rs.getString("label"));
				prod.setPrix(rs.getDouble("prix"));
				listeProduits.add(prod);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeCn(cn);
		}

		return listeProduits;
	}

	public static void closeCn(Connection cn) {
		try {
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void mettreAjourProduit(Produit p) {
		Connection cn = null;
		try {
			cn = etablirConnection();

			PreparedStatement pst = cn.prepareStatement("update Produit set label = ? WHERE numero = ? ");
			pst.setString(1, p.getLabel());
			pst.executeUpdate();
			pst.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeCn(cn);
		}

	}

	@Override
	public void supprimerProduit(Long numero) {
		Connection cn = null;
		try {
			cn = etablirConnection();

			PreparedStatement pst = cn.prepareStatement("delete from Produit where numero = ?");
			pst.setLong(1, numero);
			pst.executeUpdate();
			pst.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeCn(cn);
		}

	}

}
