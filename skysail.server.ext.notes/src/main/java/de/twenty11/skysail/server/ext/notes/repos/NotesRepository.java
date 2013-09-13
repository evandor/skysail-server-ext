package de.twenty11.skysail.server.ext.notes.repos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import de.twenty11.skysail.server.ext.notes.domain.Note;

public class NotesRepository implements ComponentRepository<Note> {

    private final EntityManager entitiyManager;

    public NotesRepository(EntityManagerFactory emf) {
        this.entitiyManager = emf.createEntityManager();
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.twenty11.skysail.server.ext.notes.repos.ComponentRepository#getById(java.lang.Long)
     */
    @Override
    public Note getById(Long id) {
        TypedQuery<Note> query = entitiyManager.createQuery("SELECT c FROM Note c WHERE c.pid = :pid", Note.class);
        query.setParameter("pid", id);
        return query.getSingleResult();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.twenty11.skysail.server.ext.notes.repos.ComponentRepository#add(de.twenty11.skysail.server.ext.notes.domain
     * .Folder)
     */
    @Override
    public void add(Note entity) {
        entitiyManager.getTransaction().begin();
        entitiyManager.persist(entity);
        entitiyManager.getTransaction().commit();
    }

    @Override
    public void update(Note entity) {
        entitiyManager.getTransaction().begin();
        entitiyManager.merge(entity);
        entitiyManager.getTransaction().commit();
    }

    @Override
    public List<Note> getComponents() {
        TypedQuery<Note> query = entitiyManager.createQuery("SELECT c FROM Note c", Note.class);
        return query.getResultList();
    }

    @Override
    public void delete(Long folderId) {
        // TODO Auto-generated method stub

    }
}
