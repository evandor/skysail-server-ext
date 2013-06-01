package de.twenty11.skysail.server.ext.bookmarks.repository;

import javax.persistence.EntityManagerFactory;

import de.twenty11.skysail.server.core.SkysailRepository;
import de.twenty11.skysail.server.ext.bookmarks.domain.Folder;

public class FolderRepository extends SkysailRepository<Folder> {

    public FolderRepository(EntityManagerFactory emf) {
        super(emf);// = emf.createEntityManager();
    }

    @Override
    protected Class<Folder> getEntityClass() {
        return Folder.class;
    }

}
