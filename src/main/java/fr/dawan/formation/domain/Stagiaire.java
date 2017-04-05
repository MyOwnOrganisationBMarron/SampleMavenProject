package fr.dawan.formation.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import fr.dawan.formation.interfaces.IRemisable;

public class Stagiaire extends Personne implements IRemisable, Serializable, Comparable<Stagiaire> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

	public static int nbStagiaires = 0;
	private String societe;
	private Date dateNaissance = new Date();
	private PriseEnCharge payeur;

	/**
	 * 
	 */
	public Stagiaire() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param nom
	 * @param prenom
	 */
	public Stagiaire(String nom, String prenom) {
		super(nom, prenom);
		nbStagiaires++;
		payeur = PriseEnCharge.Employeur;
	}

	public String getSociete() {
		return societe;
	}

	public void setSociete(String societe) {
		this.societe = societe;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public PriseEnCharge getPayeur() {
		return payeur;
	}

	public void setPayeur(PriseEnCharge payeur) {
		this.payeur = payeur;
	}

	// @Override
	// public String toString() {
	// return getPrenom()+" "+getNom()+ " ("+getSociete()+") prise en charge
	// "+getPayeur();
	// }

	@Override
	public String methodeAbstraite() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal calculTauxRemise() {
		BigDecimal remise = BigDecimal.ZERO;
		switch (payeur) {
		case PoleEmploi:
			remise = new BigDecimal("0.15");
			break;

		default:
			break;
		}
		return remise;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateNaissance == null) ? 0 : dateNaissance.hashCode());
		result = prime * result + ((getNom() == null) ? 0 : getNom().hashCode());
		result = prime * result + ((getPrenom() == null) ? 0 : getPrenom().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		Stagiaire s1 = (Stagiaire) obj;
		if (this.getNom().equals(s1.getNom())) {
			if (this.getPrenom().equals(s1.getPrenom())) {
				return this.getDateNaissance().equals(s1.getDateNaissance());
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public int compareTo(Stagiaire o) {
		int result = this.getNom().compareTo(o.getNom());
		if (result == 0) {
			result = this.getPrenom().compareTo(o.getPrenom());
		}
		return result;
	}

}
