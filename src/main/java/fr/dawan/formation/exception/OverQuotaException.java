package fr.dawan.formation.exception;

public class OverQuotaException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7145847194854282684L;

	private int quota;

	public OverQuotaException(String message, int quota) {
		super(message);
		this.quota = quota;
	}

	public int getQuota() {
		return quota;
	}

}
