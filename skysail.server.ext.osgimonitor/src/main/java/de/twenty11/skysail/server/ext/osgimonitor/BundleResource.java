package de.twenty11.skysail.server.ext.osgimonitor;

import java.util.Arrays;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;

import de.twenty11.skysail.common.commands.Command;
import de.twenty11.skysail.common.ext.osgimonitor.BundleDetails;
import de.twenty11.skysail.common.ext.osgimonitor.RestfulBundle;
import de.twenty11.skysail.common.navigation.LinkedPage;
import de.twenty11.skysail.common.responses.FailureResponse;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.ext.osgimonitor.commands.StartCommand;
import de.twenty11.skysail.server.ext.osgimonitor.commands.StopCommand;
import de.twenty11.skysail.server.ext.osgimonitor.commands.UpdateCommand;
import de.twenty11.skysail.server.restlet.UniqueResultServerResource;

/**
 * Restlet Resource class for handling a Bundle.
 * 
 */
public class BundleResource extends UniqueResultServerResource<BundleDetails> implements RestfulBundle {

    private String bundleId;
    private String action;

    public BundleResource() {
        setName("osgimonitor bundle resource");
        setDescription("The resource containing bundle detail information");
    }

    @Override
    protected void doInit() throws ResourceException {
        bundleId = (String) getRequest().getAttributes().get("bundleId");
        Form form = new Form(getRequest().getEntity());
        action = form.getFirstValue("action");
    }

    @Override
    @Get("html|json")
    public SkysailResponse<BundleDetails> getBundle() {
        OsgiMonitorViewerApplication app = (OsgiMonitorViewerApplication) getApplication();
        BundleContext bundleContext = app.getBundleContext();
        Bundle bundle = bundleContext.getBundle(Long.parseLong(bundleId));
        BundleDetails details = new BundleDetails(bundle);
        registerCommand("start", new StartCommand(bundle));
        registerCommand("stop", new StopCommand(bundle));
        registerCommand("update", new UpdateCommand(bundle));
        registerLinkedPage(new LinkedPage() {
            @Override
            public boolean applicable() {
                return true;
            }

            @Override
            public String getHref() {
                return bundleId + "/headers";
            }

            @Override
            public String getLinkText() {
                return "show Headers";
            }
        });
        return getEntity(details);
    }

    @Get("putform")
    public Representation formWithPut() {
        if (Arrays.asList("start", "stop", "update").contains(action)) {
            String start = "<html><head><title>form to issue PUT request</title></head>\n<body>\n";
            String stop = "</body></html>";
            StringRepresentation stringRepresentation = new StringRepresentation(
                    start
                            + "<form action='?method=PUT' method='POST'><input type='text' name='media' value='json'><input type='text' name='testname' value='testvalue'><input type='submit'></form>"
                            + stop);
            stringRepresentation.setMediaType(MediaType.TEXT_HTML);
            return stringRepresentation;
        } else {
            return new StringRepresentation("only 'start','stop' and 'update' are allowed as action");
        }
    }

    /**
     * parameter needed for restlet!
     * 
     * @param entity
     * @return
     */
    @Put("html|json")
    public SkysailResponse<BundleDetails> startOrStopBundle(Representation entity) {
        Command command = getCommand(action);
        if (command != null) {
            try {
                command.execute();
            } catch (Exception e) {
                return new FailureResponse<BundleDetails>(e);
            }
        }
        setMessage("Success");
        return getBundle();
    }

}
