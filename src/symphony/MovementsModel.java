package symphony;

import sql.CorePersistenceModel;

/**
 * Movements Model
 * @author Byung Seon Kim
 */
public class MovementsModel extends CorePersistenceModel<MovementsPK> {
	
	/* CONSTRUCTOR	------------------------------------------------------- the skeleton from Reginald Dyer */
	/** Default constructor: Creates a new instance of MovementsModel */
	public MovementsModel() { super(); }

	/**
	 * Parameterized constructor.
	 * @param composer The composer name
	 * @param compositionName The composition name
	 * @param movementNumber The movement number
	 * @param movementName The movement name
	 */
	public MovementsModel( String composer, String compositionName, int movementNumber, String movementName ) {
		this( new MovementsPK(composer, compositionName, movementNumber, movementName) );
	}

	/** 
	 * Creates a new instance of MovementsModel 
	 * @param primaryKey MovementsPK object for primary key
	 */
	public MovementsModel( MovementsPK primaryKey ) { super( primaryKey ); }
	
	/* ACCESSORS	------------------------------------------------------- the skeleton from Reginald Dyer */
	/**
	 * get primary key
	 * @return MovementsPK MovementsPK object
	 */
	public MovementsPK getPrimarykey() { return (MovementsPK) super.getPrimarykey(); }
	/**
	 * get the composer name
	 * @return the composer name
	 */
	public String getComposer() { return getPrimarykey().getComposer(); }
	/**
	 * get the composition name
	 * @return the composition name
	 */
	public String getCompositionName() { return getPrimarykey().getCompositionName(); }
	/**
	 * get the movement number
	 * @return the movement number
	 */
	public int getMovementNumber() { return getPrimarykey().getMovementNumber(); }
	/**
	 * get the movement name
	 * @return the movement name
	 */
	public String getMovementName() { return getPrimarykey().getMovementName(); }

	/* MODIFIERS	------------------------------------------------------- the skeleton from Reginald Dyer */
	/**
	 * set primary key
	 * @param pk primary key
	 */
	public void setPrimarykey(MovementsPK pk) { super.setPrimarykey(pk); }

	/* NORMAL BEHAVIOR	--------------------------------------------------- the skeleton from Reginald Dyer */
	/* HELPER METHODS	--------------------------------------------------- the skeleton from Reginald Dyer */
	/* ENTRY POINT for STAND-ALONE OPERATION	--------------------------- the skeleton from Reginald Dyer */
	/* ATTRIBUTES	------------------------------------------------------- the skeleton from Reginald Dyer */
	/* REFERENCE ATTRIBUTES	----------------------------------------------- the skeleton from Reginald Dyer */

} /* End of CLASS: MovementsModel.java */