package symphony;

import sql.CorePersistenceModel;

/**
 * Composition Model
 * @author Byung Seon Kim
 */
public class CompositionModel extends CorePersistenceModel<CompositionPK> {
	
	/* CONSTRUCTOR	------------------------------------------------------- the skeleton from Reginald Dyer */
	/** Default constructor: Creates a new instance of CompositionModel */
	public CompositionModel() { super(); }

	/**
	 * Parameterized constructor.
	 * @param composer The composer name
	 * @param compositionName The composition name
	 */
	public CompositionModel( String composer, String compositionName ) {
		this( new CompositionPK(composer, compositionName) );
	}

	/** 
	 * Creates a new instance of CompositionModel 
	 * @param primaryKey CompositionPK object for primary key
	 */
	public CompositionModel( CompositionPK primaryKey ) { super( primaryKey ); }
	
	/* ACCESSORS	------------------------------------------------------- the skeleton from Reginald Dyer */
	/**
	 * get primary key
	 * @return CompositionPK CompositionPK object
	 */
	public CompositionPK getPrimarykey() { return (CompositionPK) super.getPrimarykey(); }
	/**
	 * get composer name
	 * @return composer name
	 */
	public String getComposer() { return getPrimarykey().getComposer(); }
	/**
	 * get composition name
	 * @return composition name
	 */
	public String getCompositionName() { return getPrimarykey().getCompositionName(); }

	/* MODIFIERS	------------------------------------------------------- the skeleton from Reginald Dyer */
	/**
	 * set primary key
	 * @param pk primary key
	 */
	public void setPrimarykey(CompositionPK pk) { super.setPrimarykey(pk); }

	/* NORMAL BEHAVIOR	--------------------------------------------------- the skeleton from Reginald Dyer */
	/* HELPER METHODS	--------------------------------------------------- the skeleton from Reginald Dyer */
	/* ENTRY POINT for STAND-ALONE OPERATION	--------------------------- the skeleton from Reginald Dyer */
	/* ATTRIBUTES	------------------------------------------------------- the skeleton from Reginald Dyer */
	/* REFERENCE ATTRIBUTES	----------------------------------------------- the skeleton from Reginald Dyer */

} /* End of CLASS: CompositionModel.java */