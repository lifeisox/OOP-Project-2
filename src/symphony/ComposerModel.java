package symphony;

import sql.CorePersistenceModel;

/**
 * Composer Model
 * @author Byung Seon Kim
 */
public class ComposerModel extends CorePersistenceModel<ComposerPK> {
	
	/* CONSTRUCTOR	------------------------------------------------------- the skeleton from Reginald Dyer */
	/** Default constructor: Creates a new instance of ComposerModel */
	public ComposerModel() { super(); }

	/**
	 * Parameterized constructor.
	 * @param composer The composer name
	 * @param compositionName The composition name
	 */
	public ComposerModel( String composer, String compositionName ) {
		this( new ComposerPK(composer), compositionName );
	}

	/** 
	 * Creates a new instance of ComposerModel 
	 * @param primaryKey The composer name
	 * @param compositionName The composition name
	 */
	public ComposerModel( ComposerPK primaryKey, String compositionName ) { 
		super( primaryKey );
		setCompositionName(compositionName);
	}
	
	/* ACCESSORS	------------------------------------------------------- the skeleton from Reginald Dyer */
	/**
	 * get primary key in ComposerPK type
	 * @return ComposerPK primary key
	 */
	public ComposerPK getPrimarykey() { return (ComposerPK) super.getPrimarykey(); }
	/**
	 * get composer name
	 * @return composer name
	 */
	public String getComposer() { return getPrimarykey().getComposer(); }
	/** 
	 * get composition name
	 * @return composition name
	 */
	public String getCompositionName() { return compositionName; }

	/* MODIFIERS	------------------------------------------------------- the skeleton from Reginald Dyer */
	/**
	 * set primary key
	 * @param pk ComposerPK primary key
	 */
	public void setPrimarykey(ComposerPK pk) { super.setPrimarykey(pk); }
	/**
	 * set composition name
	 * @param compositionName composition name
	 */
	public void setCompositionName(String compositionName) { this.compositionName = compositionName; }

	/* NORMAL BEHAVIOR	--------------------------------------------------- the skeleton from Reginald Dyer */
	/* HELPER METHODS	--------------------------------------------------- the skeleton from Reginald Dyer */
	/* ENTRY POINT for STAND-ALONE OPERATION	--------------------------- the skeleton from Reginald Dyer */
	/* ATTRIBUTES	------------------------------------------------------- the skeleton from Reginald Dyer */
	/** The composition name */
	private String compositionName;
	/* REFERENCE ATTRIBUTES	----------------------------------------------- the skeleton from Reginald Dyer */

} /* End of CLASS: ComposerModel.java */