package fr.dawan.formation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import fr.dawan.formation.domain.Civilite;
import fr.dawan.formation.domain.PriseEnCharge;
import fr.dawan.formation.domain.Stagiaire;
import fr.dawan.formation.interfaces.IRemisable;

public class Utils {

	public final static String CSV_SEP = ";";

	private static ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>() {

		@Override
		public DateFormat get() {
			return super.get();
		}

		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("dd/MM/yyyy");
		}

		@Override
		public void remove() {
			super.remove();
		}

		@Override
		public void set(DateFormat value) {
			super.set(value);
		}

	};

	public static BigDecimal appliqueRemise(final BigDecimal montant, final IRemisable objetARemiser) {
		BigDecimal result = BigDecimal.ONE.subtract(objetARemiser.calculTauxRemise());
		return montant.multiply(result);
	}

	public static void save(Collection<Stagiaire> donnees, String dirName, String fileName) throws IOException {
		File myDir = new File(dirName);
		if (!myDir.exists()) {
			if (!myDir.mkdirs())
				throw new IOException("Création impossible de " + myDir);
		}

		File myFile = new File(myDir, fileName);
		if (!myFile.exists()) {
			if (myFile.createNewFile())
				throw new IOException("Création impossible de " + myFile);
		}

		BufferedWriter bw = new BufferedWriter(
		        new OutputStreamWriter(new FileOutputStream(myFile), StandardCharsets.UTF_8));
		for (Stagiaire stagiaire : donnees) {
			bw.write(toCSV(stagiaire));
			bw.newLine();
		}
		bw.close();
	}

	private static String toCSV(final Stagiaire stagiaire) {
		StringBuilder sb = new StringBuilder();
		sb.append(stagiaire.getTitre()).append(CSV_SEP).append(stagiaire.getNom()).append(CSV_SEP)
		        .append(stagiaire.getPrenom()).append(CSV_SEP).append(stagiaire.getSociete()).append(CSV_SEP)
		        .append(stagiaire.getPayeur()).append(CSV_SEP).append(df.get().format(stagiaire.getDateNaissance()));
		return sb.toString();
	}

	public static List<Stagiaire> read(final String dirName, final String fileName) throws IOException {
		File myFile = new File(dirName, fileName);
		BufferedReader br = new BufferedReader(
		        new InputStreamReader(new FileInputStream(myFile), StandardCharsets.UTF_8));
		List<Stagiaire> resultat = new ArrayList<Stagiaire>();
		String line = null;
		while ((line = br.readLine()) != null) {
			resultat.add(fromCSV(line));
		}
		br.close();
		return resultat;
	}

	private static Stagiaire fromCSV(final String line) {
		String[] donnees = line.split(CSV_SEP);
		Stagiaire resultat = new Stagiaire();
		resultat.setTitre(Civilite.valueOf(donnees[0]));
		resultat.setNom(donnees[1]);
		resultat.setPrenom(donnees[2]);
		resultat.setSociete(donnees[3]);
		resultat.setPayeur(PriseEnCharge.valueOf(donnees[4]));
		try {
			resultat.setDateNaissance(df.get().parse(donnees[5]));
		} catch (ParseException e) {
			resultat.setDateNaissance(new Date());
		}
		return resultat;
	}
}
