package de.twenty11.skysail.server.ext.rss.resources;

import java.util.ArrayList;
import java.util.List;

import org.restlet.data.Form;
import org.restlet.ext.atom.Entry;
import org.restlet.ext.atom.Feed;
import org.restlet.resource.ClientResource;

import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.core.restlet.ListServerResource2;
import de.twenty11.skysail.server.ext.rss.domain.AtomFeed;

public class AtomResource extends ListServerResource2<AtomFeed> {

    @Override
    protected List<AtomFeed> getData() {

        List<AtomFeed> result = new ArrayList<AtomFeed>();
        try {
            ClientResource mailClient = new ClientResource("http://en.blog.wordpress.com/feed/atom/");

            Feed atomFeed = mailClient.get(Feed.class);
            System.out.println("\nAtom feed: " + atomFeed.getTitle() + "\n");
            for (Entry entry : atomFeed.getEntries()) {
                System.out.println("Title : " + entry.getTitle());
                System.out.println("Summary: " + entry.getSummary());
                result.add(new AtomFeed(entry));
            }

            // URL feedUrl;
            // List<RssFeed> result = new ArrayList<RssFeed>();
            // SyndFeed rssFeed = mailClient.get(SyndFeed.class);
            // System.out.println("\nRSS feed: " + rssFeed.getTitle() + "\n");
            // @SuppressWarnings("unchecked")
            // List<SyndEntry> entries = (List<SyndEntry>) rssFeed.getEntries();
            // for (SyndEntry entry : entries) {
            // System.out.println("Title : " + entry.getTitle());
            // System.out.println("Summary: " + entry.getDescription().getValue());
            // result.add(new RssFeed(entry));
            // }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public AtomFeed getData(Form form) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SkysailResponse<?> addEntity(AtomFeed entity) {
        // TODO Auto-generated method stub
        return null;
    }

}
