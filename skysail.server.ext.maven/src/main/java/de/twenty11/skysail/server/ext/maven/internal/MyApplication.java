package de.twenty11.skysail.server.ext.maven.internal;

import org.restlet.Context;

import de.twenty11.skysail.server.ext.maven.MyRootResource;
import de.twenty11.skysail.server.restlet.SkysailApplication;

/**
 * @author carsten
 * 
 */
public class MyApplication extends SkysailApplication {

    /**
     * @param staticPathTemplate
     */
    public MyApplication(Context componentContext) {
        super(componentContext == null ? null : componentContext.createChildContext());
        setDescription("RESTful skysail.server.ext.maven bundle");
        setOwner("twentyeleven");
        setAuthor("twenty11");
    }

	@Override
	protected void attach() {
		router.attach("", MyRootResource.class);
		router.attach("/", MyRootResource.class);
	}



}
