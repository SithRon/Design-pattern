package fr.afcepf.ai103.data;

public class Produit {
	@Override
	public String toString() {
		return "Produit [numero=" + numero + ", label=" + label + ", prix=" + prix + "]";
	}

	private Long numero; // pk (primary key)

	private String label;

	private double prix;

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public Produit(Long numero, String label, double prix) {
		super();
		this.numero = numero;
		this.label = label;
		this.prix = prix;
	}

	public Produit() {
		super();
	}
}
