package de.twenty11.skysail.server.ext.bookmarks.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import de.twenty11.skysail.server.ext.bookmarks.domain.Bookmark;

public class BookmarkRepository {

    private EntityManager entitiyManager;

    public BookmarkRepository(EntityManagerFactory emf) {
        this.entitiyManager = emf.createEntityManager();
    }

    public List<Bookmark> getBookmarks() {
        List<Bookmark> resultList = entitiyManager.createQuery("SELECT c FROM Bookmark c").getResultList();
        List<Bookmark> filteredResults = new ArrayList<Bookmark>();
        for (Bookmark details : resultList) {
            // if (filterMatches(details)) {
            filteredResults.add(details);
            // }
        }
        return filteredResults;

    }

    public Bookmark getBookmark(String id) {
        TypedQuery<Bookmark> query = entitiyManager.createQuery("SELECT c FROM Bookmark c WHERE c.name = :name",
                Bookmark.class);
        query.setParameter("name", id);
        Bookmark result = (Bookmark) query.getSingleResult();
        return result;
    }

    public void addBookmark(Bookmark entity) {
        entitiyManager.getTransaction().begin();
        entitiyManager.persist(entity);
        entitiyManager.getTransaction().commit();
    }
}
