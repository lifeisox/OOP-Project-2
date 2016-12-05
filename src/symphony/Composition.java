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
 * Composition class
 * @author Byung Seon Kim
 *
 */
public class Composition {
	/* STATIC PRE-OBJECT BEHAVIOR	-------------------------------------- the skeleton from Reginald Dyer */
	/* CREATER	---------------------------------------------------------- the skeleton from Reginald Dyer */
	/* FINDERS	---------------------------------------------------------- the skeleton from Reginald Dyer */
	/**
	 * Find a composition by primary key.
	 * @return	An instance of a composition entity.
	 * @param	primarykey	The primary key for entity to find.
	 * @throws	FinderException FinderException
	 * @throws	NoSuchEntityException NoSuchEntityException
	 */
	public static Composition findByPrimarykey(CompositionPK primarykey)
			throws FinderException, NoSuchEntityException			{
		if (isDebugging()) System.out.println("Composition.findByPrimarykey(" + primarykey + ")");

		CompositionModel model = null;
		Composition composition = null;
		CompositionDAO dao = null;
		try	{
			dao = (CompositionDAO) DAOFactory.getDAO(className);
			model = (CompositionModel) dao.dbSelectByPrimaryKey(primarykey);
			composition = new Composition(model);
		} catch (Exception ex)	{
			if (isDebugging())	{
				System.out.println("Composition.findByPrimarykey(" + primarykey
						+ ")" + "\n\t" + ex.toString());
				ex.printStackTrace(System.out);
			}
			throw new FinderException(ex.getMessage());
		}
		return composition;
	}

	public static Collection<Composition> findQuery(String query) throws FinderException, CreateException {
		ArrayList<Composition> listOfComposers = new ArrayList<>();
		CompositionDAO dao = null;

		try	{
			dao = (CompositionDAO) DAOFactory.getDAO(className);
			System.out.println(query);
			Collection<CompositionPK> c = dao.dbSelectAll(query);
			Iterator<CompositionPK> itr = c.iterator();
			while (itr.hasNext())	{
				CompositionPK bpk = itr.next();
				try	{
					listOfComposers.add(Composition.findByPrimarykey(bpk));
				} catch (Exception ex)	{
					System.err.println("Composition: Error processing list <" + ex.toString());
				}
			}
		} catch (Exception sqlex)	{
			throw new FinderException(sqlex.getMessage());
		}
		return listOfComposers;
	}
	
	/**
	 * A select query to count the number of records in the database (the number of Composition).
	 * @param composer The composer name
	 * @return the number of Composers.
	 * @throws DAOSysException DAOSysException
	 */
	public static int size( String composer ) throws DAOSysException {
		CompositionDAO dao = null;
		int size;
		
		try {
			dao = (CompositionDAO) DAOFactory.getDAO(className);
			size = dao.dbCountTotalEntities( composer );
		} catch (Exception de) {
			throw new DAOSysException(de.getLocalizedMessage());
		}
		return size;
	}

	/* CONSTRUCTORS	-----------------------------------------------	*/
	/**
	 *	Parameterized constructor.
	 *	@param	composer the composer name
	 *	@param	compositionName the composition name
	 */
	public Composition(String composer,	String compositionName) {
		this(new CompositionModel(composer, compositionName));
	}

	/**
	 *	Parameterized constructor.
	 *	@param model The CompositionModel object
	 */
	public Composition(CompositionModel model)	{ setModel(model); }

	/* ACCESSORS	--------------------------------------------------	*/
	/**
	 * Get the CompositionModel object
	 * @return the CompositionModel object
	 */
	public CompositionModel getModel() { return model; }
	/**
	 * Get the CompositionPK primary key
	 * @return the CompositionPK primary key
	 */
	public CompositionPK getPrimaryKey() { return getModel().getPrimarykey(); }
	/**
	 * Get the composer name
	 * @return the composer name
	 */
	public String getComposer() { return getPrimaryKey().getComposer(); }
	/**
	 * Get the composition name
	 * @return the composition name
	 */
	public String getCompositionName() { return getPrimaryKey().getCompositionName(); }

	/* MODIFIERS	--------------------------------------------------	*/
	/**
	 * Set the model in CompositionModel
	 * @param m the model
	 */
	public void setModel(CompositionModel m) { model = m; }

	/* BEHAVIOR	-----------------------------------------------------	*/
	/**
	 *	Implemenation of the "object" equals method.  Composition objects are equal
	 *	if their primary key's are equal.
	 *	@return	True if the fields of this primary key object equal the
	 *	contents of the fields from the passed primary key object, otherwise
	 *	false, they are not equal.
	 */
	public boolean equals(Object obj)	{
		return	obj instanceof Composition
				&&	(getComposer().equals(((Composition) obj).getComposer()))
				&&	(getCompositionName().equals(((Composition) obj).getCompositionName()));
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
		return getComposer().concat( getCompositionName() ).hashCode();
	}

	/**
	 *	Convert this composition object to a meaningful string.
	 *	@return	This object as a string.
	 */
	public String toString()		{
		return this.toString(", ");
	}

	/**
	 * Convert this composition object to a meaningful string.
	 * @param sep String object
	 * @return	This object as a string.
	 */
	public String toString(String sep)		{
		return "composer=" + sep + getComposer() + sep + " compositionName=" + getCompositionName();
	}

	/* HELPER METHODS	--------------------------------------------------- the skeleton from Reginald Dyer */
	public static boolean isDebugging() { return _debug; }

	/* ATTRIBUTES	------------------------------------------------------- the skeleton from Reginald Dyer */
	private static final boolean _debug = true;
	/** Persistence model for a composition object.									*/
	private CompositionModel model;
	/** Class name for static method purposes.								*/
	private static String className = "symphony.Composition";
} /* End of CLASS: Composition.java */
