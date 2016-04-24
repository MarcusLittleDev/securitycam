package securitycamTest;

import static org.junit.Assert.*;

import org.junit.Test;

import securitycam.NotificationSystem;

public class NotificationSystemTest {

	@Test
	public void testSetSubject() {
		
		NotificationSystem notification = new NotificationSystem();
		notification.setSubject("subject");
	    assertEquals("subject", notification.subject);
	}
	
	@Test
	public void testGetSubject() {
		
		NotificationSystem notification = new NotificationSystem("hi", "hello", "hola");
		assertEquals("hello", notification.getSubject());
	}
	
	@Test
	public void testSetBody() {
		
		NotificationSystem notification = new NotificationSystem();
		notification.setBody("body");
	    assertEquals("body", notification.body);
		
	}
	
	@Test
	public void testGetBody() {
		
		NotificationSystem notification = new NotificationSystem("hi", "hello", "hola");
		assertEquals("hola", notification.getBody());
	}
	
	@Test
	public void testSetTo() {
		
		NotificationSystem notification = new NotificationSystem();
		notification.setTo("to");
	    assertEquals("to", notification.to);
	}
	
	@Test
	public void testGetTo() {
		
		NotificationSystem notification = new NotificationSystem("hi", "hello", "hola");
		assertEquals("hi", notification.getTo());
	}


}
