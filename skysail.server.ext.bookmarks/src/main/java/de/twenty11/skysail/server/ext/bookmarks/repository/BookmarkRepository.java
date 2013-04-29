package de.twenty11.skysail.server.ext.bookmarks.repository;

import javax.persistence.EntityManagerFactory;

import de.twenty11.skysail.server.SkysailRepository;
import de.twenty11.skysail.server.ext.bookmarks.domain.Bookmark;

public class BookmarkRepository extends SkysailRepository<Bookmark> {

    public BookmarkRepository(EntityManagerFactory emf) {
        super(emf);// = emf.createEntityManager();
    }

    @Override
    protected Class<Bookmark> getEntityClass() {
        return Bookmark.class;
    }

}
