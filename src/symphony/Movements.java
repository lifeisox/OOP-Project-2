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
 * Movements class
 * @author Byung Seon Kim
 *
 */
public class Movements {
	/* STATIC PRE-OBJECT BEHAVIOR	-------------------------------------- the skeleton from Reginald Dyer */
	/* CREATER	---------------------------------------------------------- the skeleton from Reginald Dyer */
	/* FINDERS	---------------------------------------------------------- the skeleton from Reginald Dyer */
	/**
	 * Find a movement by primary key.
	 * @return An instance of a movement entity.
	 * @param primarykey	The primary key for entity to find.
	 * @throws FinderException FinderException
	 * @throws NoSuchEntityException NoSuchEntityException
	 */
	public static Movements findByPrimarykey(MovementsPK primarykey)
			throws FinderException, NoSuchEntityException			{
		if (isDebugging()) System.out.println("Movements.findByPrimarykey(" + primarykey + ")");

		MovementsModel model = null;
		Movements composition = null;
		MovementsDAO dao = null;
		try	{
			dao = (MovementsDAO) DAOFactory.getDAO(className);
			model = (MovementsModel) dao.dbSelectByPrimaryKey(primarykey);
			composition = new Movements(model);
		} catch (Exception ex)	{
			if (isDebugging())	{
				System.out.println("Movements.findByPrimarykey(" + primarykey
						+ ")" + "\n\t" + ex.toString());
				ex.printStackTrace(System.out);
			}
			throw new FinderException(ex.getMessage());
		}
		return composition;
	}

	/**
	 * Find a movement by given query
	 * @param query the query to find
	 * @return An instance of a movement entity.
	 * @throws FinderException FinderException
	 * @throws CreateException CreateException
	 */
	public static Collection<Movements> findQuery(String query) throws FinderException, CreateException {
		ArrayList<Movements> listOfComposers = new ArrayList<>();
		MovementsDAO dao = null;

		try	{
			dao = (MovementsDAO) DAOFactory.getDAO(className);
			System.out.println(query);
			Collection<MovementsPK> c = dao.dbSelectAll(query);
			Iterator<MovementsPK> itr = c.iterator();
			while (itr.hasNext())	{
				MovementsPK bpk = itr.next();
				try	{
					listOfComposers.add(Movements.findByPrimarykey(bpk));
				} catch (Exception ex)	{
					System.err.println("Movements: Error processing list <" + ex.toString());
				}
			}
		} catch (Exception sqlex)	{
			throw new FinderException(sqlex.getMessage());
		}
		return listOfComposers;
	}
	
	/**
	 * A select query to count the number of records in the database (the number of Movements).
	 * @param composer The composer name
	 * @param composition The composition name
	 * @return the number of Composers.
	 * @throws DAOSysException DAOSysException
	 */
	public static int size( String composer, String composition ) throws DAOSysException {
		MovementsDAO dao = null;
		int size;
		
		try {
			dao = (MovementsDAO) DAOFactory.getDAO(className);
			size = dao.dbCountTotalEntities( composer, composition );
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
	 *	@param	movementNumber the movement number
	 *	@param	movementName the movement name
	 */
	public Movements(String composer, String compositionName, int movementNumber, String movementName) {
		this(new MovementsModel(composer, compositionName, movementNumber, movementName));
	}

	/**
	 *	Parameterized constructor.
	 *	@param model The MovementsModel object
	 */
	public Movements(MovementsModel model)	{ setModel(model); }

	/* ACCESSORS	--------------------------------------------------	*/
	/**
	 * Get the MovementsModel object
	 * @return the MovementsModel object
	 */
	public MovementsModel getModel() { return model; }
	/**
	 * Get the MovementsPK primary key
	 * @return the MovementsPK primary key
	 */
	public MovementsPK getPrimaryKey() { return getModel().getPrimarykey(); }
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
	/**
	 * Get the movement number
	 * @return the movement number
	 */
	public int getMovementNumber() { return getPrimaryKey().getMovementNumber(); }
	/**
	 * Get the movement name
	 * @return the movement name
	 */
	public String getMovementName() { return getPrimaryKey().getMovementName(); }

	/* MODIFIERS	--------------------------------------------------	*/
	/**
	 * Set the movement model
	 * @param m MovementsModel
	 */
	public void setModel(MovementsModel m) { model = m; }

	/* BEHAVIOR	-----------------------------------------------------	*/
	/**
	 *	Implemenation of the "object" equals method.  Movements objects are equal
	 *	if their primary key's are equal.
	 *	@return	True if the fields of this primary key object equal the
	 *	contents of the fields from the passed primary key object, otherwise
	 *	false, they are not equal.
	 */
	public boolean equals(Object obj)	{
		return	obj instanceof Movements
				&&	(getComposer().equals(((Movements) obj).getComposer()))
				&&	(getCompositionName().equals(((Movements) obj).getCompositionName()))
				&&	(getMovementNumber() == (((Movements) obj).getMovementNumber()))
				&&	(getMovementName().equals(((Movements) obj).getMovementName()));
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
		return	getComposer().toString().concat( getCompositionName().concat( 
				((Integer)getMovementNumber()).toString().concat( getMovementName().toString() ))).hashCode();
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
		return "Composer: " + getComposer() + " Movements name: " + getCompositionName() 
				+ " Movement number: " + getMovementNumber() + " Movement name: " + getMovementName();
	}

	/* HELPER METHODS	--------------------------------------------------- the skeleton from Reginald Dyer */
	public static boolean isDebugging() { return _debug; }

	/* ATTRIBUTES	------------------------------------------------------- the skeleton from Reginald Dyer */
	private static final boolean _debug = true;
	/** Persistence model for a composition object.									*/
	private MovementsModel model;
	/** Class name for static method purposes.								*/
	private static String className = "symphony.Movements";
} /* End of CLASS: Movements.java */
