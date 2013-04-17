package de.twenty11.skysail.server.ext.jgit.internal;

import de.twenty11.skysail.server.ext.jgit.*;

import org.eclipse.jgit.lib.Repository;
import org.restlet.Context;

import de.twenty11.skysail.server.restlet.RouteBuilder;
import de.twenty11.skysail.server.restlet.SkysailApplication;

/**
 * @author carsten
 * 
 */
public class MyApplication extends SkysailApplication {

    private final Repository defaultRepository;

    public MyApplication(Context componentContext, Repository defaultRepository) {
        super(componentContext == null ? null : componentContext.createChildContext());
        setDescription("RESTful Jenkins bundle");
        setOwner("twentyeleven");
        setName("jgit");
        this.defaultRepository = defaultRepository;

    }

    protected void attach() {
        // @formatter:off
        router.attach(new RouteBuilder("", MyRootResource.class).setVisible(false));

        // @formatter:on
    }
}
