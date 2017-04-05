package fr.dawan.formation.domain;

import java.math.BigDecimal;

import fr.dawan.formation.interfaces.IFacturable;

public class Formation implements IFacturable {
	private String sujet;
	private int duree;
	private int prixHT;

	/**
	 * @param sujet
	 */
	public Formation(String sujet) {
		this.sujet = sujet;
	}

	/**
	 * @param sujet
	 * @param duree
	 */
	public Formation(String sujet, int duree) {
		this(sujet);
		this.duree = duree;
	}

	/**
	 * @param sujet
	 * @param duree
	 * @param prixHT
	 */
	public Formation(String sujet, int duree, int prixHT) {
		this(sujet, duree);
		this.prixHT = prixHT;
	}

	public String getSujet() {
		return sujet;
	}

	public void setSujet(String sujet) {
		this.sujet = sujet;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public int getPrixHT() {
		return prixHT;
	}

	public void setPrixHT(int prixHT) {
		this.prixHT = prixHT;
	}

	@Override
	public BigDecimal calculMontantTTC() {
		return new BigDecimal("1.2").multiply(new BigDecimal(prixHT));
	}

}
