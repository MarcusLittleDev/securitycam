package securitycam;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
	public static void SendEmail(NotificationSystem notification){
		// Sender's email ID needs to be mentioned
	     String from = "alertsystem415@yahoo.com";
	     String pass ="Security415";
	    // Recipient's email ID needs to be mentioned.
	   String host = "smtp.mail.yahoo.com";

	   // Get system properties
	   Properties properties = System.getProperties();
	   // Setup mail server
	   properties.put("mail.smtp.starttls.enable", "true");
	   properties.put("mail.smtp.host", host);
	   properties.put("mail.smtp.user", from);
	   properties.put("mail.smtp.password", pass);
	   properties.put("mail.smtp.port", "587");
	   properties.put("mail.smtp.auth", "true");

	   // Get the default Session object.
	   Session session = Session.getDefaultInstance(properties);

	   try{
	      // Create a default MimeMessage object.
	      MimeMessage message = new MimeMessage(session);

	      // Set From: header field of the header.
	      message.setFrom(new InternetAddress(from));

	      // Set To: header field of the header.
	      message.addRecipient(Message.RecipientType.TO,
	                               new InternetAddress(notification.to));

	      // Set Subject: header field
	      message.setSubject(notification.subject);

	      // Now set the actual message
	      message.setText(notification.body);

	      // Send message
	      Transport transport = session.getTransport("smtp");
	      transport.connect(host, from, pass);
	      transport.sendMessage(message, message.getAllRecipients());
	      transport.close();
	      System.out.println("Sent message successfully....");
	   }catch (MessagingException mex) {
	      mex.printStackTrace();
	   }
	}
	}

