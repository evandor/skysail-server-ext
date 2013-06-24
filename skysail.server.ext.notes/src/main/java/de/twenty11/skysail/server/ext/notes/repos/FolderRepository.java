package de.twenty11.skysail.server.ext.notes.repos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import de.twenty11.skysail.server.ext.notes.domain.Folder;

public class FolderRepository implements ComponentRepository<Folder> {

    private EntityManager entitiyManager;

    public FolderRepository(EntityManagerFactory emf) {
        this.entitiyManager = emf.createEntityManager();
    }

    /* (non-Javadoc)
     * @see de.twenty11.skysail.server.ext.notes.repos.ComponentRepository#getById(java.lang.Long)
     */
    @Override
    public Folder getById(Long id) {
        TypedQuery<Folder> query = entitiyManager
                .createQuery("SELECT c FROM Folder c WHERE c.pid = :pid", Folder.class);
        query.setParameter("pid", id);
        return (Folder) query.getSingleResult();
    }

    /* (non-Javadoc)
     * @see de.twenty11.skysail.server.ext.notes.repos.ComponentRepository#add(de.twenty11.skysail.server.ext.notes.domain.Folder)
     */
    @Override
    public void add(Folder entity) {
        entitiyManager.getTransaction().begin();
        entitiyManager.persist(entity);
        entitiyManager.getTransaction().commit();
    }

}
