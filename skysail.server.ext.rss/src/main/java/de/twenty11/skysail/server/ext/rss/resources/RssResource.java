//package de.twenty11.skysail.server.ext.rss.resources;
//
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.restlet.resource.ClientResource;
//
//import com.sun.syndication.feed.atom.Entry;
//import com.sun.syndication.feed.atom.Feed;
//import com.sun.syndication.feed.synd.SyndEntry;
//import com.sun.syndication.feed.synd.SyndFeed;
//import com.sun.syndication.io.SyndFeedInput;
//import com.sun.syndication.io.XmlReader;
//
//import de.twenty11.skysail.server.core.restlet.ListServerResource2;
//import de.twenty11.skysail.server.ext.rss.domain.RssFeed;
//
//public class RssResource extends ListServerResource2<RssFeed> {
//
//    @Override
//    protected List<RssFeed> getData() {
//        URL feedUrl;
//        List<RssFeed> result = new ArrayList<RssFeed>();
//        try {
//            // feedUrl = new URL("http://www.tagesschau.de/xml/tagesschau-meldungen/");
//            // SyndFeedInput input = new SyndFeedInput();
//            // //SyndFeed feed = input.build(new InputStreamReader(feedUrl.openStream()));
//            //
//            // SyndFeed feed = input.build(new XmlReader(feedUrl));
//            //
//            // System.out.println(feed);
//
//            ClientResource mailClient = new ClientResource("http://www.tagesschau.de/xml/tagesschau-meldungen/?media=rss");
//            SyndFeed rssFeed = mailClient.get(SyndFeed.class);
//            System.out.println("\nRSS feed: " + rssFeed.getTitle() + "\n");
//            @SuppressWarnings("unchecked")
//            List<SyndEntry> entries = (List<SyndEntry>) rssFeed.getEntries();
//            for (SyndEntry entry : entries) {
//                System.out.println("Title : " + entry.getTitle());
//                System.out.println("Summary: " + entry.getDescription().getValue());
//                result.add(new RssFeed(entry));
//            }
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return result;
//    }
//
//}
