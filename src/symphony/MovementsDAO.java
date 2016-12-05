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
 * Data access object for a Movements entity.
 * @author Byung Seon Kim
 */
public class MovementsDAO extends CoreDAOImpl<MovementsModel, MovementsPK> {

	/* CONSTRUCTOR	------------------------------------------------------- the skeleton from Reginald Dyer */
	/** Creates a new instance of MovementsDAO */
	public MovementsDAO() {
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
	public MovementsDAO( String drivername, String url, String user, String password) {
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
	public MovementsModel dbSelectByPrimaryKey(MovementsPK primarykey) 
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
	public MovementsModel dbSelectByPrimaryKey(MovementsPK primarykey, String selectStm)
			throws DAOSysException, NoSuchEntityException {
		MovementsPK pk = (MovementsPK) primarykey;
		Connection connection = null;
		PreparedStatement preparedStm = null;
		ResultSet rs = null;
		MovementsModel model = null;
		boolean result = false;
		if (selectStm == null) {
			selectStm = SELECT_STATEMENT;
		}

		try {
			connection = connectToDB();
			preparedStm = connection.prepareStatement(selectStm);
			preparedStm.setString(1, pk.getComposer());
			preparedStm.setString(2, pk.getCompositionName());
			preparedStm.setInt(3, pk.getMovementNumber());
			preparedStm.setString(4, pk.getMovementName());
			rs = preparedStm.executeQuery();

			result = rs.next();
			if (result) {
				model = new MovementsModel();
				model.setPrimarykey(new MovementsPK(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4)));
			} else {
				throw new NoSuchEntityException("Composer <" + primarykey.getComposer()
				+ "> and Composition name <" + primarykey.getCompositionName()
				+ "> and Movement number <" + primarykey.getMovementNumber()
				+ "> and Movement Name <" + primarykey.getMovementName()
				+ " is not found in the database.");
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
	public Collection<MovementsPK> dbSelectAll() throws DAOSysException {
		return dbSelectAll(SELECT_ALL_STATEMENT);
	}

	/**
	 * Called by findAll() to find all entities.
	 * @param selectStm query
	 * @return A collection of primary keys representing all of the entities.
	 * @throws DAOSysException DAOSysException
	 */
	@Override
	public Collection<MovementsPK> dbSelectAll(String selectStm) throws DAOSysException {
		Connection connection = null;
		PreparedStatement preparedStm = null;
		ResultSet rs = null;
		ArrayList<MovementsPK> list = null;
		if (selectStm == null) {
			selectStm = SELECT_ALL_STATEMENT;
		}

		try {
			connection = connectToDB();
			preparedStm = connection.prepareStatement(selectStm);
			rs = preparedStm.executeQuery();
			list = new ArrayList<MovementsPK>();
			while (rs.next()) {
				list.add(new MovementsPK(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4)));
			}

		} catch (SQLException sex) {
			throw new DAOSysException("dbSelectAll() SQL Exception\n" + sex.getMessage());
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
	 * A select query to count the number of records in the
	 * database (the number of Composition).
	 * @param composer the composer name
	 * @param composition the composition name
	 * @return the size of the table
	 * @throws DAOSysException DAOSysException
	 */
	public int dbCountTotalEntities( String composer, String composition ) throws DAOSysException {
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
			preparedStm.setString(2, composition);
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
	public void dbInsert(MovementsModel model) throws DAOSysException { }

	@Override
	public void dbInsert(MovementsModel model, String insertStm) throws DAOSysException { }

	@Override
	public void dbUpdate(MovementsModel data) throws DAOSysException { }

	@Override
	public void dbUpdate(MovementsModel model, String updateStm) throws DAOSysException { }

	@Override
	public int dbRemove(MovementsPK primarykey) throws DAOSysException { return 0; }

	@Override
	public int dbRemove(MovementsPK primarykey, String deleteStm) throws DAOSysException { return 0; }

	/* ATTRIBUTES	-----------------------------------------------	*/
	private final static String SELECT_COMPOSER_STATEMENT =
			"SELECT " + " composer, compositionName, movementNumber, movementName " + " FROM " + " Movements " 
					+ " WHERE composer = ? AND compositionName = ? ORDER BY movementNumber, movementName;";
	private final static String SELECT_ALL_STATEMENT =
			"SELECT " + " composer, compositionName, movementNumber, movementName " + " FROM " + " Movements " 
					+ " ORDER BY movementNumber, movementName;";
	private final static String SELECT_STATEMENT =
			"SELECT " + " composer, compositionName, movementNumber, movementName " + " FROM " + " Movements" 
					+ " WHERE composer = ? AND compositionName = ? AND movementNumber = ? AND movementName = ? ";
}	/*	End of Class: MovementsDAO.java */