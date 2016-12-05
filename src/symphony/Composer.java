package symphony;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import sql.CreateException;
import sql.DAOFactory;
import sql.DAOSysException;
import sql.FinderException;
import sql.NoSuchEntityException;

/**
 * Composer class
 * @author Byung Seon Kim
 *
 */
public class Composer {
	/* STATIC PRE-OBJECT BEHAVIOR	-------------------------------------- the skeleton from Reginald Dyer */
	/* CREATER	---------------------------------------------------------- the skeleton from Reginald Dyer */
	/* FINDERS	---------------------------------------------------------- the skeleton from Reginald Dyer */
	/**
	 * Find a boat by primary key.
	 * @return An instance of a boat entity.
	 * @param primarykey The primary key for entity to find.
	 * @throws FinderException FinderException
	 * @throws NoSuchEntityException NoSuchEntityException
	 */
	public static Composer findByPrimarykey(ComposerPK primarykey)
			throws FinderException, NoSuchEntityException			{
		if (isDebugging()) System.out.println("Composer.findByPrimarykey(" + primarykey + ")");

		ComposerModel model = null;
		Composer composer = null;
		ComposerDAO dao = null;
		try	{
			dao = (ComposerDAO) DAOFactory.getDAO(className);
			model = (ComposerModel) dao.dbSelectByPrimaryKey(primarykey);
			composer = new Composer(model);
		} catch (Exception ex)	{
			if (isDebugging())	{
				System.out.println("Composer.findByPrimarykey(" + primarykey
						+ ")" + "\n\t" + ex.toString());
				ex.printStackTrace(System.out);
			}
			throw new FinderException(ex.getMessage());
		}
		return composer;
	}

	/**
	 * Find composer entities as given query.
	 * @param query query
	 * @return A collection of boat instances.
	 * @throws FinderException FinderException
	 * @throws CreateException CreateException
	 */
	public static Collection<Composer> findQuery(String query) throws FinderException, CreateException {
		ArrayList<Composer> listOfComposers = new ArrayList<Composer>();
		ComposerDAO dao = null;

		try	{
			dao = (ComposerDAO) DAOFactory.getDAO(className);
			Collection<ComposerPK> c = dao.dbSelectAll(query);
			Iterator<ComposerPK> itr = c.iterator();
			while (itr.hasNext())	{
				ComposerPK bpk = itr.next();
				try	{
					listOfComposers.add(Composer.findByPrimarykey(bpk));
				} catch (Exception ex)	{
					System.err.println("Composer: Error processing list <" + ex.toString());
				}
			}

		} catch (Exception sqlex)	{
			throw new FinderException(sqlex.getMessage());
		}

		return listOfComposers;
	}
	
	/**
	 * A select query to count the number of records in the database (the number of Composers).
	 * @return the number of Composers.
	 * @throws DAOSysException DAOSysException
	 */
	public static int size() throws DAOSysException {
		ComposerDAO dao = null;
		int size;
		
		try {
			dao = (ComposerDAO) DAOFactory.getDAO(className);
			size = dao.dbCountTotalEntities();
		} catch (Exception de) {
			throw new DAOSysException(de.getLocalizedMessage());
		}
		return size;
	}
	
	/* CONSTRUCTORS	-----------------------------------------------	*/
	/**
	 * Parameterized constructor.
	 * @param composer The composer name
	 * @param compositionName The composition name
	 */
	public Composer(String composer, String compositionName) {
		this(new ComposerModel(composer, compositionName));
	}

	/**
	 * Parameterized constructor.
	 * @param model the object of Composer
	 */
	public Composer(ComposerModel model)	{
		if (isDebugging()) System.out.println("B: Composer: composer (" + model.getComposer() + ")");
		setModel(model);
	}

	/* ACCESSORS	--------------------------------------------------	*/
	/**
	 * Get Composer model object
	 * @return ComposerModel object
	 */
	public ComposerModel getModel() { return model; }
	/**
	 * Ger Primary key
	 * @return primary key
	 */
	public ComposerPK getPrimaryKey() { return getModel().getPrimarykey(); }
	/**
	 * Get composer name
	 * @return composer name
	 */
	public String getComposer() { return getPrimaryKey().getComposer(); }

	/* MODIFIERS	--------------------------------------------------	*/
	/**
	 * Set composer model
	 * @param m composer model
	 */
	public void setModel(ComposerModel m) { model = m; }

	/**
	 *	Implemenation of the "object" equals method.  Composer objects are equal
	 *	if their primary key's are equal.
	 *	@return	True if the fields of this primary key object equal the
	 *	contents of the fields from the passed primary key object, otherwise
	 *	false, they are not equal.
	 */
	public boolean equals(Object obj)	{
		return	obj instanceof Composer
				&&	(getComposer().equals(((Composer) obj).getComposer()));
	}

	/**
	 *	Implementation of the "object"hashCode()" method.
	 * Whenever it is invoked on the same object more than once during
	 * an execution of a Java application, the hashCode method
	 * must consistently return the same integer, provided no information
	 * used in equals comparisons on the object is modified.
	 *	@return	A hash code value for the object.
	 */
	public int hashCode() {
		return	getComposer().hashCode();
	}

	/**
	 *	Convert this boat object to a meaningful string.
	 *	@return	This object as a string.
	 */
	public String toString()		{
		return this.toString(", ");
	}

	/**
	 * Convert this boat object to a meaningful string.
	 * @param sep String object
	 * @return	This object as a string.
	 */
	public String toString(String sep)		{
		return "composer=" + sep + getComposer();
	}

	/* HELPER METHODS	--------------------------------------------------- the skeleton from Reginald Dyer */
	/**
	 * check if debug mode
	 * @return if debug mode, return true
	 */
	public static boolean isDebugging() { return _debug; }

	/* ATTRIBUTES	------------------------------------------------------- the skeleton from Reginald Dyer */
	private static final boolean _debug = true;
	/** Persistence model for a composition object.									*/
	private ComposerModel model;
	/** Class name for static method purposes.								*/
	private static String className = "symphony.Composer";
} /* End of CLASS: Composer.java */
