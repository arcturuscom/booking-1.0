package pakiet;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
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
		boolean isLoginChecked = booking.checkLogin("pezhman1024@gmail.com");
		boolean isLoginChecked2 = booking.checkLogin("benyanass@gmail.com");
		Assert.assertTrue(isLoginChecked2);
		Assert.assertFalse(isLoginChecked);
	}
	
	/**
	 * assert user registration
	 */
	@Test
	public void testRegister(){
		boolean registered = booking.register("benyazidanass@gmail.com", "77963b7a931377ad4ab5ad6a9cd718aa", "Klient");
		Assert.assertTrue(registered);
	}
	
	/**
	 * Assert login usecase
	 */
	@Test
	public void testLog(){
		ResultSet rs = booking.log("ddd@ddd.pl", "77963b7a931377ad4ab5ad6a9cd718aa");
		Assert.assertNotNull(rs);
		try {
			Assert.assertTrue(rs.next());
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
	}
	
	/**
	 * Assert add Event
	 */
	@Test
	public void testAddEvent(){
		boolean addEvent = booking.addEvent("01:12:2dsfe5", "event_1", "malmo", "place_1", new Timestamp(new Date().getTime()), 3, 5456);
		Assert.assertTrue(addEvent);
	}
	
	/**
	 * Assert edit event
	 */
	@Test
	public void testEditEvent(){
		boolean editEvent = booking.editEvent("01:12", "edited_event", "Paris", "place_edited", new Timestamp(new Date().getTime()), 2, 500);
		Assert.assertTrue(editEvent);
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
	
	@Test
	public void testGetEventsByUserId(){
		ResultSet rs = booking.getEvents("01:12");
		Assert.assertNotNull(rs);
		if (rs != null)
			try {
				Assert.assertFalse(rs.next());
			} catch (SQLException e) {
				LOGGER.log(Level.SEVERE, e.getMessage(), e);
				e.printStackTrace();
			}
	}
	
	@Test
	public void testGetEventById(){
		ResultSet rs = booking.getEvent("01:01348b92-d3ae-4b53-abf3-5e84615d9b0d");
		Assert.assertNotNull(rs);
		if (rs != null)
			try {
				Assert.assertTrue(rs.next());
			} catch (SQLException e) {
				LOGGER.log(Level.SEVERE, e.getMessage(), e);
				e.printStackTrace();
			}
	}

	@Test
	public void testBookTickets(){
		boolean bookTicket = booking.bookTickets("01:01348b92-d3ae-4b53-abf3-5e84615d9b0d", "01:12", 5);
		Assert.assertTrue(bookTicket);
	}
	
	@Test
	public void removeEvent(){
		boolean eventRemoved = booking.removeEvent("01:12:2dsfe5");
		Assert.assertTrue(eventRemoved);
	}
	
	@Test
	public void getReservationList(){
		ResultSet rs = booking.getReservationList("01:12");
		Assert.assertNotNull(rs);
		if (rs != null)
			try {
				Assert.assertTrue(rs.next());
			} catch (SQLException e) {
				LOGGER.log(Level.SEVERE, e.getMessage(), e);
				e.printStackTrace();
			}
	}
	
	@Test
	public void testGetReservationsEvent() {
		ResultSet rs = booking.getReservationsEvents("01:01348b92-d3ae-4b53-abf3-5e84615d9b0d");
		Assert.assertNotNull(rs);
		if (rs != null)
			try {
				Assert.assertTrue(rs.next());
			} catch (SQLException e) {
				LOGGER.log(Level.SEVERE, e.getMessage(), e);
				e.printStackTrace();
			}

	}
	
	@Test
	public void testGetReservations(){
		ResultSet rs = booking.getReservations("01:15e77c37-8a14-4bde-ad5a-d0246ee322e8");
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
	 * test getEmail method
	 */
	@Test
	public void testGetEmail() {
		String email = booking.getEmail("01:256d5775-1b2d-4ecb-a22b-603024297771");
		Assert.assertEquals(email, "pezhman1024@gmail.com");
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
