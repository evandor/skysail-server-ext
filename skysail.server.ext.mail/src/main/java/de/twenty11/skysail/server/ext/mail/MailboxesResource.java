package de.twenty11.skysail.server.ext.mail;

import java.util.Collections;
import java.util.List;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;
import javax.mail.search.FlagTerm;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.restlet.resource.ResourceException;

import com.sun.mail.pop3.POP3SSLStore;

import de.twenty11.skysail.server.core.restlet.ListServerResource2;
import de.twenty11.skysail.server.ext.mail.internal.MyApplication;

public class MailboxesResource extends ListServerResource2<MailboxDescriptor> {

    private String id;

    @Override
    protected void doInit() throws ResourceException {
        id = (String) getRequest().getAttributes().get("id");
        setDescription("The resource containing the list of mailboxes for '" + id + "'");
    }

    @Override
    protected List<MailboxDescriptor> getData() {
        MyApplication app = (MyApplication) getApplication();
        EntityManager em = app.getEmf().createEntityManager();
        TypedQuery<AccountDescriptor> query = em.createQuery("SELECT c FROM AccountDescriptor c WHERE c.name = :name",
                AccountDescriptor.class);
        query.setParameter("name", id);
        AccountDescriptor account = query.getSingleResult();
        Session session = Session.getDefaultInstance(System.getProperties());

        String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        Properties pop3Props = new Properties();

        pop3Props.setProperty("mail.pop3.socketFactory.class", SSL_FACTORY);
        pop3Props.setProperty("mail.pop3.socketFactory.fallback", "false");
        pop3Props.setProperty("mail.pop3.port", "995");
        pop3Props.setProperty("mail.pop3.socketFactory.port", "995");

        URLName url = new URLName("pop3", "pop.gmail.com", 995, "", "evandor", "34efmb22");

        session = Session.getInstance(pop3Props, null);
        Store store = new POP3SSLStore(session, url);
        try {
            store.connect();
            Folder inbox = store.getFolder("inbox");
            inbox.open(Folder.READ_ONLY);
            Flags seen = new Flags(Flags.Flag.SEEN);
            FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
            Message messages[] = inbox.search(unseenFlagTerm);
            System.out.println(messages);
            // Folder[] sharedNamespaces = store.getSharedNamespaces();
            // System.out.println(sharedNamespaces);

        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

}
