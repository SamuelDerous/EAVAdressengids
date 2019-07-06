/**
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * Deze klasse wordt gebruikt voor het generen van een mail met de verlofdata
 * 
 */
package creatie;



import java.util.ArrayList;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author zenodotus
 */
public class MailSend {

    private String host;
    private String van;
    private String[] naar;
    private String onderwerp;
    private String bericht;

    public MailSend(String host, String van, String[] naar,
            String onderwerp, String bericht) {
        this.host = host;
        this.van = van;
        this.naar = naar;
        this.onderwerp = onderwerp;
        this.bericht = bericht;
    }

    public void verzend(ArrayList<String> bestand) {
        Properties mailProperties = System.getProperties();
        mailProperties.setProperty("mail.smtp.host", host);
        Session session = Session.getDefaultInstance(mailProperties);
        session.getDebugOut();
        MimeMessage message = new MimeMessage(session);
        try {
            InternetAddress[] adressen = new InternetAddress[naar.length];
            for(int i = 0; i < naar.length; i++) {
                adressen[i] = new InternetAddress(naar[i]);
            }
            message.setFrom(new InternetAddress(van));
            message.addRecipients(MimeMessage.RecipientType.TO, adressen);
            message.setSubject(onderwerp);
            //message.setHeader("Content-Type", "text/html");
            BodyPart messageBodyPart = new MimeBodyPart();
            
            messageBodyPart.setText(bericht);
            messageBodyPart.setHeader("Content-Type", "text/html");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
         
         if (bestand.size() > 0) {
             for(int i = 0; i < bestand.size(); i++) {
                messageBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(bestand.get(i));
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(bestand.get(i));
                multipart.addBodyPart(messageBodyPart);
            }
         }
           message.setContent(multipart);
           Transport.send(message);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

   /* public static void main(String[] args) {
        MailSend newMail = new MailSend("smtp.scarlet.be", "detalisman@hotmail.com",
                {"samuel.derous@scarlet.be"}, "Testbericht", "Dit is een test");
        newMail.verzend();
        Properties mail = System.getProperties();
        System.out.println(mail.toString());
         
    }*/
    
    
}
