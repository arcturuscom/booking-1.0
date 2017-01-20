package sinan.database;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConnectionHandlerTest {
	
	private static final Logger LOGGER = Logger.getLogger(ConnectionHandlerTest.class.getName());
	
	ConnectionHandler connectionHandler;
	
	/**
	 * invoking method on test setup
	 * @throws Exception
	 * @throws ConnectionHandlerException
	 */
	@Before
	public void setUp() throws Exception {
		connectionHandler = new ConnectionHandler();
		try {
			connectionHandler.connect();
		} catch (ConnectionHandlerException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			e.printStackTrace();
		}
	}

	/**
	 * invoking method after test has finished
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception, ConnectionHandlerException {
		connectionHandler.disconnect();
	}

	/**
	 * test get connected databases method
	 */
	@Test
	public final void testGetConnectedDatabases() {
		List<Database> connectedDatabases = connectionHandler.getConnectedDatabases();
		Assert.assertNotNull(connectedDatabases);
		Assert.assertFalse(connectedDatabases.isEmpty());
	}

	/**
	 * test excuteQuery method, same as excuteUpdate method@
	 */
	@Test
	public final void testExecuteQuery() {
		String query = "SELECT * FROM events";
		try {
			ResultSet result = connectionHandler.executeQuery("01", query);
			Assert.assertNotNull(result);
		} catch (ConnectionHandlerException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			e.printStackTrace();
		}
	}



}
