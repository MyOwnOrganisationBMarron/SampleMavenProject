package fr.dawan.formation.domain;

public class Formateur extends Personne {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2150474257468965440L;
	private String matricule;
	private boolean interne;

	/**
	 * @param nom
	 * @param prenom
	 */
	public Formateur(String nom, String prenom) {
		super(nom, prenom);
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public boolean isInterne() {
		return interne;
	}

	public void setInterne(boolean interne) {
		this.interne = interne;
	}

	@Override
	public String toString() {
		return "Formateur [matricule=" + matricule + ", interne=" + interne + ", toString()=" + super.toString() + "]";
	}

	@Override
	public String methodeAbstraite() {
		// TODO Auto-generated method stub
		return null;
	}
}
