package fr.gouv.culture.francetransfert.core.exception;

/**
 * Exception générique pendant une opération de la Metaload API.
 */
public class MetaloadException extends Exception {

	/**
	 * Serialization id généré.
	 */
	private static final long serialVersionUID = 5657417082244325824L;

	/**
	 * {@inheritDoc}
	 * 
	 * @param message Description spécifique
	 */
	public MetaloadException(String message) {
		super(message);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param message Description spécifique
	 * @param e       Exception parente
	 */
	public MetaloadException(String message, Throwable e) {
		super(message, e);
	}

	public MetaloadException(Throwable e) {
		super(e);
	}
}