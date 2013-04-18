package de.twenty11.skysail.server.ext.mail;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import de.twenty11.skysail.server.ext.mail.internal.MyApplication;
import de.twenty11.skysail.server.restlet.ListServerResource2;

public class AccountsResource extends ListServerResource2<AccountDescriptor> {

    @Override
    protected List<AccountDescriptor> getData() {
        registerLinkedPage(new AddAccountPage());
        MyApplication application = (MyApplication) getApplication();
        EntityManagerFactory emf = application.getEmf();
        EntityManager em = emf.createEntityManager();
        List<AccountDescriptor> resultList = em.createQuery("SELECT c FROM AccountDescriptor c").getResultList();
        List<AccountDescriptor> filteredResults = new ArrayList<AccountDescriptor>();
        for (AccountDescriptor details : resultList) {
            if (filterMatches(details)) {
                filteredResults.add(details);
            }
        }
        return filteredResults;
    }

}
