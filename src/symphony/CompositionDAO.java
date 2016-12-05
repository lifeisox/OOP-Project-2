package symphony;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import sql.CoreDAO;
import sql.CoreDAOImpl;
import sql.DAOSysException;
import sql.NoSuchEntityException;

/**
 * Data access object for a Composition entity.
 * @author Byung Seon Kim
 */
public class CompositionDAO extends CoreDAOImpl<CompositionModel, CompositionPK> {

	/* CONSTRUCTOR	------------------------------------------------------- the skeleton from Reginald Dyer */
	/** Creates a new instance of CompositionDAO */
	public CompositionDAO() {
		this( CoreDAO.DRIVER_NAME, CoreDAO.URL, CoreDAO.USER, CoreDAO.PASSWORD );
	}

	/**
	 * Parameterized constructor.  When extending this class the
	 * derived class must invoke one of this classes constructors
	 * for proper initialization.
	 * @param drivername Driver name 
	 * @param url URL
	 * @param user Database user name.
	 * @param password Database password for access.
	 */
	public CompositionDAO( String drivername, String url, String user, String password) {
		super( drivername, url, user, password );
	}

	/* ACCESSORS	------------------------------------------------------- the skeleton from Reginald Dyer */
	/* MODIFIERS	------------------------------------------------------- the skeleton from Reginald Dyer */
	/* NORMAL BEHAVIOR	--------------------------------------------------- the skeleton from Reginald Dyer */
	
	/**
	 * Called by findByPrimaryKey() to retrieve entity data by the primary key.
	 *	@param	primarykey The primary key for the entity.
	 *	@throws	DAOSysException DAOSysException
	 *	@throws	NoSuchEntityException NoSuchEntityException
	 */
	@Override
	public CompositionModel dbSelectByPrimaryKey(CompositionPK primarykey) 
			throws DAOSysException, NoSuchEntityException {
		 return dbSelectByPrimaryKey(primarykey, SELECT_STATEMENT);
	}

	/**
	 * Called by findByPrimaryKey() to retrieve entity data by the primary key.
	 * @param primarykey The primary key for the entity.
	 * @param selectStm	Statement to retrieved the entity data from the data store.
	 * @return The persistence model for the entity.
	 * @throws DAOSysException DAOSysException
	 * @throws NoSuchEntityException NoSuchEntityException
	 */
	@Override
	public CompositionModel dbSelectByPrimaryKey(CompositionPK primarykey, String selectStm)
			throws DAOSysException, NoSuchEntityException {
		CompositionPK pk = (CompositionPK) primarykey;
		Connection connection = null;
		PreparedStatement preparedStm = null;
		ResultSet rs = null;
		CompositionModel model = null;
		boolean result = false;
		if (selectStm == null) {
			selectStm = SELECT_STATEMENT;
		}

		try {
			connection = connectToDB();
			preparedStm = connection.prepareStatement(selectStm);
			preparedStm.setString(1, pk.getComposer());
			preparedStm.setString(2, pk.getCompositionName());
			rs = preparedStm.executeQuery();

			result = rs.next();
			if (result) {
				model = new CompositionModel();
				model.setPrimarykey(new CompositionPK(rs.getString(1), rs.getString(2)));
			} else {
				throw new NoSuchEntityException("Composer <" + primarykey.getComposer()
						+ "> and Composition name <" + primarykey.getCompositionName()
						+ "> is not found in the database.");
			}

		} catch (SQLException sex) {
			throw new DAOSysException(
				 "dbSelectByPrimaryKey() SQL Exception\n" + sex.getMessage());

		} finally {
			try {
				releaseAll(rs, preparedStm, connection);
			} catch (Exception ex) {
				System.err.println("Error releasing resources <" + ex.toString());
			}
		}
		return model;
	}

	/**
	 * Called by findAll() to find all entities in the data store.
	 * @return A collection of primary keys representing all of the entities.
	 * @throws DAOSysException DAOSysException
	 */
	@Override
	public Collection<CompositionPK> dbSelectAll() throws DAOSysException {
		return dbSelectAll(SELECT_ALL_STATEMENT);
	}

	/**
	 * Called by findAll() to find all entities.
	 * @param selectStm query
	 * @return A collection of primary keys representing all of the entities.
	 * @throws DAOSysException DAOSysException
	 */
	@Override
	public Collection<CompositionPK> dbSelectAll(String selectStm) throws DAOSysException {
		Connection connection = null;
		PreparedStatement preparedStm = null;
		ResultSet rs = null;
		ArrayList<CompositionPK> list = null;
		if (selectStm == null) {
			selectStm = SELECT_ALL_STATEMENT;
		}

		try {
			connection = connectToDB();
			preparedStm = connection.prepareStatement(selectStm);
			rs = preparedStm.executeQuery();
			list = new ArrayList<CompositionPK>();
			while (rs.next()) {
				list.add(new CompositionPK(rs.getString(1), rs.getString(2)));
			}

		} catch (SQLException sex) {
				throw new DAOSysException(
				 "dbSelectAll() SQL Exception\n" + sex.getMessage());
		} finally {
			try {
				releaseAll(rs, preparedStm, connection);
			} catch (Exception ex) {
				System.err.println("Error releasing resources <" + ex.toString());
			}
		}

		return list;
	}

	/**
	* Called by getTotalBoats(). A select query to count the number of records in the
	* database (the number of Composer).
	* @param composer the composer name
	* @return the size of composer table
	* @throws DAOSysException DAOSysException
	*/
	public int dbCountTotalEntities( String composer ) throws DAOSysException {
		String selectStm = SELECT_COMPOSER_STATEMENT;
		Connection connection = null;
		PreparedStatement preparedStm = null;
		ResultSet rs = null;
		int count = 0;

		try {
			connection = connectToDB();
			/*	Request a resultset that is scrollable to easily count rows	*/
			preparedStm = connection.prepareStatement( selectStm, ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
			preparedStm.setString(1, composer);
			rs = preparedStm.executeQuery();
			/*	Go to the last row and get its row number							*/
			rs.last();
			count = rs.getRow();
		} catch (SQLException sex) {
			throw new DAOSysException(
			 "dbCountTotalBoats() SQL Exception\n" + sex.getMessage());
		} finally {
			try {
				releaseAll(rs, preparedStm, connection);
			} catch (SQLException sqlex) {
				throw new DAOSysException(sqlex.toString());
			}
		}
		 return count;
	}

	@Override
	public int dbCountTotalEntities() throws DAOSysException { return 0; }
	
	@Override
	public void dbInsert(CompositionModel model) throws DAOSysException { }

	@Override
	public void dbInsert(CompositionModel model, String insertStm) throws DAOSysException { }

	@Override
	public void dbUpdate(CompositionModel data) throws DAOSysException { }

	@Override
	public void dbUpdate(CompositionModel model, String updateStm) throws DAOSysException { }

	@Override
	public int dbRemove(CompositionPK primarykey) throws DAOSysException { return 0; }

	@Override
	public int dbRemove(CompositionPK primarykey, String deleteStm) throws DAOSysException { return 0; }

	/* ATTRIBUTES	-----------------------------------------------	*/
	private final static String SELECT_COMPOSER_STATEMENT =
		"SELECT " + " composer, compositionName " + " FROM " + " Composition " 
				+ " WHERE composer = ? ORDER BY compositionName;";
	private final static String SELECT_ALL_STATEMENT =
			"SELECT " + " composer, compositionName " + " FROM " + " Composition " 
					+ " ORDER BY compositionName;";
	private final static String SELECT_STATEMENT =
		"SELECT " + "composer, compositionName " + "FROM " + "Composition" 
				+ " WHERE composer = ? AND compositionName = ? ";
}	/*	End of Class: CompositionDAO.java */