package de.twenty11.skysail.server.ext.rss.resources;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;

import de.twenty11.skysail.server.core.restlet.ListServerResource2;
import de.twenty11.skysail.server.ext.rss.domain.RssFeed;

public class RssResource extends ListServerResource2<RssFeed> {

    @Override
    protected List<RssFeed> getData() {
        URL feedUrl;
        List<RssFeed> result = new ArrayList<RssFeed>();
        try {
            feedUrl = new URL("http://www.tagesschau.de/xml/tagesschau-meldungen/");
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new InputStreamReader(feedUrl.openStream()));
            List<SyndEntry> entries = feed.getEntries();
            for (SyndEntry syndEntry : entries) {
                result.add(new RssFeed(syndEntry));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

}
