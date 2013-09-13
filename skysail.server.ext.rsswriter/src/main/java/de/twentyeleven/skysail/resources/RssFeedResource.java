package de.twentyeleven.skysail.resources;

import java.util.ArrayList;
import java.util.List;

import org.restlet.data.Form;
import org.restlet.ext.atom.Entry;
import org.restlet.ext.atom.Feed;
import org.restlet.ext.atom.Text;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;

import de.twenty11.skysail.common.Presentation;
import de.twenty11.skysail.common.PresentationStyle;
import de.twenty11.skysail.common.navigation.LinkedPage;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.core.restlet.ListServerResource2;
import de.twentyeleven.skysail.MyApplication;
import de.twentyeleven.skysail.domain.RssFeed;

/**
 * Restlet Root Resource for dbViewer application.
 * 
 */
@Presentation(preferred = PresentationStyle.LIST2)
public class RssFeedResource extends ListServerResource2<RssFeed> {

    public RssFeedResource() {
        setName("osgimonitor root resource");
        setDescription("The root resource of the osgimonitor application");
    }

    @Override
    protected void doInit() throws ResourceException {
        super.doInit();
    }

    @Override
    @Get("html|json|csv")
    public SkysailResponse<List<RssFeed>> getEntities() {
        registerLinkedPage(new LinkedPage() {

            @Override
            public String getLinkText() {
                return "new Folder";
            }

            @Override
            public String getHref() {
                return "notes/folder";
            }

            @Override
            public boolean applicable() {
                return true;
            }
        });
        registerLinkedPage(new LinkedPage() {

            @Override
            public String getLinkText() {
                return "new MyEntity";
            }

            @Override
            public String getHref() {
                return "notes/note";
            }

            @Override
            public boolean applicable() {
                return true;
            }
        });
        return getEntities("Folders and Notes");
    }

    @Get("atom")
    public Feed toAtom() throws ResourceException {
        Feed result = new Feed();
        result.setTitle(new Text("Homer's feed"));
        for (int i = 1; i < 11; i++) {
            Entry entry = new Entry();
            entry.setTitle(new Text("Mail #" + i));
            entry.setSummary("Doh! This is the content of mail #" + i);
            result.getEntries().add(entry);
        }
        return result;
    }

    // @Get("rss")
    // public SyndFeed toRss() throws ResourceException {
    // SyndFeed result = new SyndFeedImpl();
    // result.setTitle("Homer's feed");
    // result.setDescription("Homer's feed");
    // result.setLink(getReference().toString());
    // List<SyndEntry> entries = new ArrayList<SyndEntry>();
    // result.setEntries(entries);
    // for (int i = 1; i < 11; i++) {
    // SyndEntry entry = new SyndEntryImpl();
    // entry.setTitle("Mail #" + i);
    // SyndContent description = new SyndContentImpl();
    // description.setValue("Doh! This is the content of mail #" + i);
    // entry.setDescription(description);
    // entries.add(entry);
    // }
    // return result;
    // }

    @Override
    protected List<RssFeed> getData() {
        MyApplication app = (MyApplication) getApplication();
        List<RssFeed> result = new ArrayList<RssFeed>();
        // addNotes(result, app);
        return result;
    }

    @Override
    public RssFeed getData(Form form) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SkysailResponse<?> addEntity(RssFeed entity) {
        // TODO Auto-generated method stub
        return null;
    }

}
