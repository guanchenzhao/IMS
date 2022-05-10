package com.gc.ims.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Email via aws SES
 */
@Component
public class EmailUtil {

    // Replace sender@example.com with your "From" address.
    // This address must be verified.
    static final String FROM = "gczhao@uw.edu";
    static final String FROMNAME = "Inventory Manage Service";

    // Replace recipient@example.com with a "To" address. If your account
    // is still in the sandbox, this address must be verified.
//    static final String TO = "recipient@example.com";

    // Replace smtp_username with your Amazon SES SMTP user name.
    @Value("${spring.mail.username}")
    private String SMTP_USERNAME;

    // Replace smtp_password with your Amazon SES SMTP password.
    @Value("${spring.mail.password}")
    private String SMTP_PASSWORD;

    // The name of the Configuration Set to use for this message.
    // If you comment out or remove this variable, you will also need to
    // comment out or remove the header below.
    static final String CONFIGSET = "ConfigSet";

    // Amazon SES SMTP host name. This example uses the US West (Oregon) region.
    // See https://docs.aws.amazon.com/ses/latest/DeveloperGuide/regions.html#region-endpoints
    // for more information.
    @Value("${spring.mail.host}")
    private String HOST;

    // The port you will connect to on the Amazon SES SMTP endpoint.
    @Value("${spring.mail.properties.mail.smtp.port}")
    private int PORT;


    public void sendEmail(String subject, String body, String toEmail) {
        Transport transport = null;

        try { // Create a Properties object to contain connection configuration information.
            Properties props = System.getProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.port", PORT);
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");

            // Create a Session object to represent a mail session with the specified properties.
            Session session = Session.getDefaultInstance(props);

            // Create a message with the specified information.
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(FROM, FROMNAME));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            msg.setSubject(subject);
            msg.setContent(body, "text/html");

            // Create a transport.
            transport = session.getTransport();

            // Send the message.
            System.out.println("Sending...");

            // Connect to Amazon SES using the SMTP username and password you specified above.
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);

            // Send the email.
            transport.sendMessage(msg, msg.getAllRecipients());
            System.out.println("Email sent!");
        } catch (Exception ex) {
            System.out.println("The email was not sent.");
            System.out.println("Error message: " + ex.getMessage());
        } finally {
            // Close and terminate the connection.
            if (null != transport) {
                try {
                    transport.close();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}

