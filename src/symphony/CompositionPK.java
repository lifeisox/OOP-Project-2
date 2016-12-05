package symphony;

import java.io.Serializable;

/**
 * Define class of Composition primary key
 * @author Byung Seon Kim
 */
@SuppressWarnings("serial")
public class CompositionPK implements Serializable {
	
	/* CONSTRUCTOR	------------------------------------------------------- the skeleton from Reginald Dyer */
	/** Default constructor. */
	public CompositionPK() {}

	/**
	 * Constructor to build a primary key from a composer and composition name.
	 * @param composer	The composer name.
	 * @param compositionName The composition name
	 */
	public CompositionPK(String composer, String compositionName) { 
		this.composer = composer;		
		this.compositionName = compositionName;
	}

	/**
	 *	Constructor to build a primary key from a another CompositionPK argument.
	 *	@param primarykey A CompositionPK object.
	 */
	public CompositionPK(CompositionPK primarykey)	{ 
		this.composer = primarykey.composer;		
		this.compositionName = primarykey.compositionName;		}

	/* ACCESSORS	------------------------------------------------------- the skeleton from Reginald Dyer */
	/**
	 * Get the composer name.
	 * @return The composer name.
	 */
	public String getComposer()	{ return composer; }
	
	/**
	 * Get the composition name
	 * @return The composition name
	 */
	public String getCompositionName() { return compositionName; }

	/* MODIFIERS	------------------------------------------------------- the skeleton from Reginald Dyer */
	/* NORMAL BEHAVIOR	--------------------------------------------------- the skeleton from Reginald Dyer */
	/**
	 * Convert this primary key object into a meaningful string.
	 * @return This object as a string.
	 */
	public String toString() { return "Composer: " + composer + " Composition name: " + compositionName; }

	/**
	 * Implemenation of the "object" equals method.
	 * @return True if the fields of this primary key object equal the
	 * contents of the fields from the passed primary key object, otherwise
	 * false, they are not equal.
	 */
	public boolean equals(Object obj)	{
		return obj instanceof CompositionPK
				&& getComposer().equals(((CompositionPK) obj).getComposer())
				&& getCompositionName().equals(((CompositionPK) obj).getCompositionName());
	}

	/**
	 * Implementation of the "object"hashCode()" method.
	 * Whenever it is invoked on the same object more than once during
	 * an execution of a Java application, the hashCode method
	 * must consistently return the same integer, provided no information
	 * used in equals comparisons on the object is modified.
	 * @return A hash code value for the object.
	 */
	public int hashCode() {
		return	getComposer().toString().concat( getCompositionName().toString() ).hashCode();
	}

	/* HELPER METHODS	--------------------------------------------------- the skeleton from Reginald Dyer */
	/* ENTRY POINT for STAND-ALONE OPERATION	--------------------------- the skeleton from Reginald Dyer */
	/* ATTRIBUTES	------------------------------------------------------- the skeleton from Reginald Dyer */
	/* Composition Entity PRIMARY KEY FIELDS -------------------------------------------------------------- */
	/** Composer name */
	private String composer;
	/** Composition name */
	private String compositionName;

} /* End of Class: CompositionPK.java */