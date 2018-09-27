


import javax.mail.PasswordAuthentication;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail 
{

    public  static void send(String to, String sub,String msg, final String user, final String pass) 
    {
         Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
          });

        try {

            Message mmessage = new MimeMessage(session);
            mmessage.setContent(msg, "text/html; charset=utf-8");
            mmessage.setFrom(new InternetAddress(user));
            mmessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            mmessage.setSubject(sub);
            

            Transport.send(mmessage);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
      
        
       
        
    }
}