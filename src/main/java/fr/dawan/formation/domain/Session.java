package fr.dawan.formation.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import fr.dawan.formation.Utils;
import fr.dawan.formation.exception.OverQuotaException;
import fr.dawan.formation.interfaces.IFacturable;
import fr.dawan.formation.interfaces.IRemisable;

public class Session implements IFacturable, IRemisable {
	public static final int NB_PLACES_DEFAUT = 12;
	private Date dateDebut;
	private String lieu;
	private Formation formationDispensee;
	private Set<Stagiaire> inscrits;
	private int nbInscrits = 0;
	private int nbPlacesDispo = NB_PLACES_DEFAUT;
	private Formateur formateurAssocie;

	/**
	 * @param dateDebut
	 * @param lieu
	 */
	public Session(Date dateDebut, String lieu, Formation formation) {
		this.dateDebut = dateDebut;
		this.lieu = lieu;
		this.formationDispensee = formation;
		inscrits = new TreeSet<Stagiaire>();
	}

	public void ajoute(Stagiaire s) throws Exception {
		if (inscrits.size() < nbPlacesDispo) {
			if (!inscrits.add(s)) {
				throw new Exception("Stagiaire déja inscrit " + s.toString());
			}
		} else {
			throw new OverQuotaException("Plus de places disponibles", nbPlacesDispo);
		}
	}

	public List<Personne> getParticipants() {
		List<Personne> resultat = new ArrayList<Personne>(inscrits);
		// Personne[] resultat = Arrays.copyOf(inscrits, nbInscrits+1,
		// Personne[].class);
		resultat.add(formateurAssocie);
		return resultat;
	}

	public void ajoute(Formateur f) {
		formateurAssocie = f;

	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public Formation getFormationDispensee() {
		return formationDispensee;
	}

	public void setFormationDispensee(Formation formationDispensee) {
		this.formationDispensee = formationDispensee;
	}

	public Collection<Stagiaire> getInscrits() {
		return inscrits;
	}

	public int getNbInscrits() {
		return nbInscrits;
	}

	public void setNbInscrits(int nbInscrits) {
		this.nbInscrits = nbInscrits;
	}

	public int getNbPlacesDispo() {
		return nbPlacesDispo;
	}

	public void setNbPlacesDispo(int nbPlacesDispo) {
		this.nbPlacesDispo = nbPlacesDispo;
	}

	public Formateur getFormateurAssocie() {
		return formateurAssocie;
	}

	@Override
	public BigDecimal calculMontantTTC() {
		BigDecimal montant = BigDecimal.ZERO;
		BigDecimal montantFormation = formationDispensee.calculMontantTTC();
		// parcours des stagiaires
		for (Stagiaire stagiaire : inscrits) {
			if (stagiaire != null) {
				BigDecimal montantApresRemise = Utils.appliqueRemise(montantFormation, stagiaire);
				montant = montant.add(montantApresRemise);
			}
		}
		// return nbInscrits * formationDispensee.calculMontantTTC();
		return Utils.appliqueRemise(montant, this);
	}

	@Override
	public BigDecimal calculTauxRemise() {
		BigDecimal remise = BigDecimal.ZERO;
		boolean memeSociete = true;
		if (inscrits.size() > 1) {

			String societe = null;
			for (Stagiaire stagiaire : inscrits) {
				if (societe == null) {
					societe = stagiaire.getSociete();
				} else {
					if (!societe.equals(stagiaire.getSociete())) {
						memeSociete = false;
						break;
					}
				}
			}

			if (memeSociete && societe != null) {
				remise = new BigDecimal("0.1");
			}
		}

		return remise;
	}

}
