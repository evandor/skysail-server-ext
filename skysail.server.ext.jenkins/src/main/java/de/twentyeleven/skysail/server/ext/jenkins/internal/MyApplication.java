package de.twentyeleven.skysail.server.ext.jenkins.internal;

import org.osgi.framework.FrameworkUtil;
import org.restlet.Request;
import org.restlet.Response;

import de.twenty11.skysail.server.listener.UrlMappingServiceListener;
import de.twenty11.skysail.server.restlet.RestletOsgiApplication;

/**
 * @author carsten
 * 
 */
public class MyApplication extends SkysailApplication {

    private static MyApplication self;

    /**
     * @param staticPathTemplate
     */
    public MyApplication(String staticPathTemplate) {
        super(MyApplicationDescriptor.APPLICATION_NAME, staticPathTemplate);
        setDescription("RESTful skysail.server.ext.jenkins bundle");
        setOwner("twentyeleven");
        self = this;
    }

    /**
     * this is done to give osgi a chance to inject serives to restlet; should be changed to some javax.inject approach
     * (like using InjectedServerResource) once this is available.
     * 
     * @return
     */
    public static MyApplication get() {
        return self;
    }

    @Override
    public void handle(Request request, Response response) {
        super.handle(request, response);
    }

    // TODO proper place for this here? what about multiple instances?
    protected void attach() {
        if (FrameworkUtil.getBundle(SkysailApplication.class) != null) {
            new UrlMappingServiceListener(this);
            //new SkysailApplicationServiceListener(this);
        }
    }


}
