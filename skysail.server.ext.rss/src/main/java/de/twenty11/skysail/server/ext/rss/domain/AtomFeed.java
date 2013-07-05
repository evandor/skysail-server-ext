package de.twenty11.skysail.server.ext.rss.domain;

import java.util.List;

import org.restlet.ext.atom.Entry;
import org.restlet.ext.atom.Link;

public class AtomFeed {

    private String title;
    private String summary;
    private List<Link> links;

    public AtomFeed(Entry syndEntry) {
        title = syndEntry.getTitle().getContent();
        summary = syndEntry.getSummary();
        links = syndEntry.getLinks();
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getSummary() {
        return summary;
    }
        
    public List<Link> getLinks() {
        return links;
    }

}
