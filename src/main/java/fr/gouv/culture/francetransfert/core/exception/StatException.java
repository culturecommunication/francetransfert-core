package fr.gouv.culture.francetransfert.core.exception;

public class StatException extends Exception {

	public StatException(String message) {
		super(message);
	}

	public StatException(Throwable ex) {
		super(ex);
	}

	public StatException(String message, Throwable ex) {
		super(message, ex);
	}

}
