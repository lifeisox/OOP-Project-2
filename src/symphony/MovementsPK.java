package symphony;

import java.io.Serializable;

/**
 * Define class of Movements primary key
 * @author Byung Seon Kim
 */
@SuppressWarnings("serial")
public class MovementsPK implements Serializable {
	
	/* CONSTRUCTOR	------------------------------------------------------- the skeleton from Reginald Dyer */
	/** Default constructor. */
	public MovementsPK() {}

	/**
	 * Constructor to build a primary key from a composer and movements name.
	 * @param composer	The composer name.
	 * @param compositionName The composition name
	 * @param movementNumber The movement number
	 * @param movementName The movement name
	 */
	public MovementsPK(String composer, String compositionName, int movementNumber, String movementName) { 
		this.composer = composer;		
		this.compositionName = compositionName;
		this.movementNumber = movementNumber;
		this.movementName = movementName;
	}

	/**
	 *	Constructor to build a primary key from a another MovementsPK argument.
	 *	@param primarykey A MovementsPK object.
	 */
	public MovementsPK(MovementsPK primarykey)	{ 
		this.composer = primarykey.composer;		
		this.compositionName = primarykey.compositionName;	
		this.movementName = primarykey.movementName;
		this.movementNumber = primarykey.movementNumber;
	}

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
	/**
	 * Get the movement number
	 * @return the movement number
	 */
	public int getMovementNumber() { return movementNumber; }
	/**
	 * Get the movement name
	 * @return the movement name
	 */
	public String getMovementName() { return movementName; }

	/* MODIFIERS	------------------------------------------------------- the skeleton from Reginald Dyer */
	/* NORMAL BEHAVIOR	--------------------------------------------------- the skeleton from Reginald Dyer */
	/**
	 * Convert this primary key object into a meaningful string.
	 * @return This object as a string.
	 */
	public String toString() { return "Composer: " + composer + " Movements name: " + compositionName 
			+ " Movement number: " + movementNumber + " Movement name: " + movementName; }

	/**
	 * Implemenation of the "object" equals method.
	 * @return True if the fields of this primary key object equal the
	 * contents of the fields from the passed primary key object, otherwise
	 * false, they are not equal.
	 */
	public boolean equals(Object obj)	{
		return obj instanceof MovementsPK
				&& getComposer().equals(((MovementsPK) obj).getComposer())
				&& getCompositionName().equals(((MovementsPK) obj).getCompositionName())
				&& getMovementNumber() == (((MovementsPK) obj).getMovementNumber())
				&& getMovementName().equals(((MovementsPK) obj).getMovementName());
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
		return	getComposer().toString().concat( getCompositionName().concat( 
				((Integer)getMovementNumber()).toString().concat( getMovementName().toString() ))).hashCode();
	}

	/* HELPER METHODS	--------------------------------------------------- the skeleton from Reginald Dyer */
	/* ENTRY POINT for STAND-ALONE OPERATION	--------------------------- the skeleton from Reginald Dyer */
	/* ATTRIBUTES	------------------------------------------------------- the skeleton from Reginald Dyer */
	/* Movements Entity PRIMARY KEY FIELDS -------------------------------------------------------------- */
	/** Composer name */
	private String composer;
	/** Movements name */
	private String compositionName;
	/** Movement number */
	private int movementNumber;
	/** Movement name */
	private String movementName;

} /* End of Class: MovementsPK.java */