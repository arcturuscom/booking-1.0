package pakiet;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import sinan.database.ConnectionHandlerException;
import sinan.database.ResultSet;

/**
 * class testing booking
 * @author Anass
 *
 */
public class BookingTest {
	private static final Logger LOGGER = Logger.getLogger(BookingTest.class.getName());
	private Booking booking;

	/**
	 * method invoked before starting test
	 * connect to the db on test begin
	 * @throws Exception
	 * @throws ConnectionHandlerException
	 */
	@Before
	public void setUp()  {
		booking = new Booking();
		booking.connect();
	}
	
	/**
	 * method invoked after test finished
	 * disconect from db after the test has finished
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		booking.disconnect();
	}

	/**
	 * test If the given username is busy.
	 */
	@Test
	public void testCheckLogin() {
		boolean isLoginChecked = booking.checkLogin("benyazidanass@gmail.com");
		boolean isLoginChecked2 = booking.checkLogin("benyanass@gmail.com");
		Assert.assertTrue(isLoginChecked2);
		Assert.assertFalse(isLoginChecked);
	}

	/**
	 * test getEmail method
	 */
	@Test
	public void testGetEmail() {
		String email = booking.getEmail("02:e3e962de-396a-4381-8c8c-a53bff227020");
		Assert.assertEquals(email, "benyazidanass@gmail.com");
	}

	/**
	 * test get all events
	 */
	@Test
	public void testGetEvents() {
		ResultSet rs = booking.getEvents();
		Assert.assertNotNull(rs);
		if (rs != null)
			try {
				Assert.assertTrue(rs.next());
			} catch (SQLException e) {
				LOGGER.log(Level.SEVERE, e.getMessage(), e);
				e.printStackTrace();
			}
	
	}

	/**
	 * test get news method
	 */
	@Test
	public void testGetNews() {
		ResultSet rs = booking.getNews("news");
		try {
			Assert.assertNotNull(rs);
			if (rs != null)
				Assert.assertFalse(rs.next());
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			e.printStackTrace();
		}
	}

}
