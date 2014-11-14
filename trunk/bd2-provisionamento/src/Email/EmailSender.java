/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Email;

/**
 *
 * @author Lucas
 */
import MyExceptions.DaoException;
import provisionamento.model.Usuario;
/*
import Sistema.Dao;
import Sistema.Factoring;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
*/
public class EmailSender {

    public EmailSender(Usuario user) throws DaoException {
    /*
        Properties props = new Properties();
        String senha = "";
        
        Dao<Usuario> daouser = Factoring.getDaoUsuario();
        Usuario usuarioPerdido = daouser.busca(user.getNome());
        
        for(int i = 0; i < usuarioPerdido.getSenha().length; i++){
            senha = senha + "" + usuarioPerdido.getSenha()[i];
        }
        
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("sistema.de.provisionamento@gmail.com", "elieumgato");
            }
        });

        session.setDebug(true);

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("sistema.de.provisionamento@gmail.com")); //Remetente

            Address[] toUser = InternetAddress.parse(user.getEmail());

            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject("Lembramos sua Senha para você! :)");
            message.setText("\n" + 
                            "Saudações, " + user.getNome()  + "!" +
                            "\n\n" +
                            "Poxa, notamos que você esqueceu sua senha...\n" +
                            "Lembramos ela para você, então: " + senha + "\n\n" + 
                            "Mais atenção da próxima vez, combinado? ;) \n" + 
                            "\n\n" + 
                            "Att. Sistema de Provisionamento - POOII");

            Transport.send(message);

            JOptionPane.showMessageDialog(null, "Enviamos um email para você. Talvez ajude a te lembrar da senha! ;)");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
            */
    }
}