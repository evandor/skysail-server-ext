package de.twenty11.skysail.server.ext.notes.repos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

import de.twenty11.skysail.server.ext.notes.NotesApplication;
import de.twenty11.skysail.server.ext.notes.domain.Folder;

public class FolderRepository implements ComponentRepository<Folder> {

    private final EntityManager entitiyManager;
    private NotesApplication application;

    public FolderRepository(EntityManagerFactory emf, NotesApplication application) {
        this.application = application;
        this.entitiyManager = emf.createEntityManager();
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.twenty11.skysail.server.ext.notes.repos.ComponentRepository#getById(java.lang.Long)
     */
    @Override
    public Folder getById(Long id) {
        TypedQuery<Folder> query = entitiyManager.createQuery(
                "SELECT c FROM Folder c WHERE c.pid = :pid AND c.owner = :owner", Folder.class);
        query.setParameter("pid", id);
        query.setParameter("owner", application.getCurrentUser());

        List<Folder> results = query.getResultList();
        if (results.isEmpty()) {
            throw new IllegalArgumentException("Missing entity or authorization error");
        }
        if (results.size() == 1) {
            return results.get(0);
        }
        throw new NonUniqueResultException();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.twenty11.skysail.server.ext.notes.repos.ComponentRepository#add(de.twenty11.skysail.server.ext.notes.domain
     * .Folder)
     */
    @Override
    public void add(Folder entity) {
        entitiyManager.getTransaction().begin();
        entitiyManager.persist(entity);
        entitiyManager.getTransaction().commit();
    }

    @Override
    public void update(Folder entity) {
        entitiyManager.getTransaction().begin();
        entitiyManager.merge(entity);
        entitiyManager.getTransaction().commit();
    }

    @Override
    public List<Folder> getComponents() {
        TypedQuery<Folder> query = entitiyManager.createQuery("SELECT c FROM Folder c WHERE c.owner = :owner",
                Folder.class);
        query.setParameter("owner", application.getCurrentUser());
        return query.getResultList();
    }

    @Override
    public void delete(Long folderId) {
        entitiyManager.getTransaction().begin();
        entitiyManager.remove(getById(folderId));
        entitiyManager.getTransaction().commit();
    }

}
