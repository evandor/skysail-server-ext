package de.twenty11.skysail.server.ext.notes.repos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import de.twenty11.skysail.server.ext.notes.domain.Folder;

public class FolderRepository {

    private EntityManager entitiyManager;

    public FolderRepository(EntityManagerFactory emf) {
        this.entitiyManager = emf.createEntityManager();
    }

    public Folder getById(Long id) {
        TypedQuery<Folder> query = entitiyManager
                .createQuery("SELECT c FROM Folder c WHERE c.pid = :pid", Folder.class);
        query.setParameter("pid", id);
        return (Folder) query.getSingleResult();
    }

}
