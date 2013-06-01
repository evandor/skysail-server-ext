package de.twenty11.skysail.server.ext.bookmarks.repository;

import javax.persistence.EntityManagerFactory;

import com.mysema.query.Tuple;
import com.mysema.query.jpa.impl.JPAQuery;

import de.twenty11.skysail.server.core.SkysailRepository;
import de.twenty11.skysail.server.ext.bookmarks.domain.*;

import java.util.ArrayList;
import java.util.List;

public class BookmarkRepository extends SkysailRepository<Bookmark> {

    public BookmarkRepository(EntityManagerFactory emf) {
        super(emf);
    }

    @Override
    protected Class<Bookmark> getEntityClass() {
        return Bookmark.class;
    }

    public List<BookmarkOrFolder> getAll(Folder parent) {

        JPAQuery query = new JPAQuery (getEntityManager());
        QBookmark bookmark = QBookmark.bookmark;
        QFolder folder = QFolder.folder;
        JPAQuery from = query.from(bookmark, folder);
        List<Tuple> list = from.list();
        //JPAQuery query = new JPAQuery(em);

        //List<T> resultList = getEntityManager().createQuery(getAllForFolderQuery()).getResultList();
        //List<T> filteredResults = new ArrayList<T>();
        //for (T details : resultList) {
            // if (filterMatches(details)) {
        //    filteredResults.add(details);
            // }
        //}
        return null;//filteredResults;
    }

    public String getAllForFolderQuery() {
        return "SELECT c FROM Bookmark c, Folder  WHERE c.name = :name";
    }
}
