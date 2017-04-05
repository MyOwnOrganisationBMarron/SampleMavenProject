package fr.dawan.formation.domain;

import java.io.Serializable;

public abstract class Personne implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6663549159744321600L;
	private Civilite titre;
	private String nom;
	private String prenom;

	public Personne() {
		super();
	}

	/**
	 * @param nom
	 * @param prenom
	 */
	public Personne(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
	}

	public Civilite getTitre() {
		return titre;
	}

	public void setTitre(Civilite titre) {
		this.titre = titre;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	@Override
	public String toString() {
		return "Personne [titre=" + titre + ", nom=" + nom + ", prenom=" + prenom + "]";
	}

	public abstract String methodeAbstraite();

}