package de.twenty11.skysail.server.ext.jgit.internal;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import de.twenty11.skysail.server.ext.jgit.LocalRepositoryDescriptor;

public class Repository implements DbRepository {

    private EntityManager entitiyManager;

    public Repository(EntityManagerFactory emf) {
        this.entitiyManager = emf.createEntityManager();
    }

    @Override
    public List<LocalRepositoryDescriptor> getLocalRepositoryDescriptors() {
        // EntityManager em = ((MyApplication) getApplication()).getEntityManager();
        List<LocalRepositoryDescriptor> resultList = entitiyManager.createQuery(
                "SELECT c FROM LocalRepositoryDescriptor c").getResultList();
        List<LocalRepositoryDescriptor> filteredResults = new ArrayList<LocalRepositoryDescriptor>();
        for (LocalRepositoryDescriptor details : resultList) {
            // if (filterMatches(details)) {
            filteredResults.add(details);
            // }
        }
        return filteredResults;

    }

    @Override
    public LocalRepositoryDescriptor getLocalRepositoryDescriptor(String id) {
        TypedQuery<LocalRepositoryDescriptor> query = entitiyManager.createQuery(
                "SELECT c FROM LocalRepositoryDescriptor c WHERE c.name = :name", LocalRepositoryDescriptor.class);
        query.setParameter("name", id);
        LocalRepositoryDescriptor result = (LocalRepositoryDescriptor) query.getSingleResult();
        return result;

    }

    @Override
    public void addLocalRepositoryDescriptor(LocalRepositoryDescriptor entity) {
        entitiyManager.getTransaction().begin();
        entitiyManager.persist(entity);
        entitiyManager.getTransaction().commit();
        // entitiyManager.close();
    }

}
