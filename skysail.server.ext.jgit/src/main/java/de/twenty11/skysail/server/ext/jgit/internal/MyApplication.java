package de.twenty11.skysail.server.ext.jgit.internal;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;

import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.component.ComponentContext;
import org.restlet.Context;
import org.restlet.routing.Router;
import org.restlet.routing.Template;

import de.twenty11.skysail.common.commands.Command;
import de.twenty11.skysail.server.core.restlet.RouteBuilder;
import de.twenty11.skysail.server.ext.jgit.AddLocalRepositoryResource;
import de.twenty11.skysail.server.ext.jgit.ExecuteMavenCommand;
import de.twenty11.skysail.server.ext.jgit.ListDirResource;
import de.twenty11.skysail.server.ext.jgit.LocalRepositoriesResource;
import de.twenty11.skysail.server.ext.jgit.LocalRepositoryResource;
import de.twenty11.skysail.server.ext.jgit.MyRootResource;
import de.twenty11.skysail.server.ext.jgit.ShowFileResource;
import de.twenty11.skysail.server.restlet.SkysailApplication;
import de.twenty11.skysail.server.services.ApplicationProvider;

/**
 * @author carsten
 * 
 */
public class MyApplication extends SkysailApplication implements ApplicationProvider{

    private DbRepository repository;
    private EntityManagerFactory emf;

    public MyApplication() {
//        if (getContext() != null) {
//            setContext(getContext().createChildContext());
//        }
        setDescription("RESTful Jenkins bundle");
        setOwner("twentyeleven");
        setName("jgit");
        //repository = new Repository(emf);
    }
    
    protected void attach() {

        // make sure to match proper resource even if request url contains add. information
        router.setDefaultMatchingMode(Template.MODE_STARTS_WITH);
        router.setRoutingMode(Router.MODE_LAST_MATCH);

        // @formatter:off
        router.attach(new RouteBuilder("", MyRootResource.class).setVisible(false));
        router.attach(new RouteBuilder("/repos", LocalRepositoriesResource.class).setText("Local Repositories"));
        router.attach(new RouteBuilder("/repos/", AddLocalRepositoryResource.class).setVisible(false));
        router.attach(new RouteBuilder("/repos/{id}", LocalRepositoryResource.class).setVisible(false));
        router.attach(new RouteBuilder("/repos/{id}/cloneform", CloneFormResource.class).setVisible(false));
        router.attach(new RouteBuilder("/repos/{id}/listdir/", ListDirResource.class).setVisible(false));
        router.attach(new RouteBuilder("/repos/{id}/showfile/", ShowFileResource.class).setVisible(false));
        router.attach(new RouteBuilder("/repos/{id}/maven", MavenFormResource.class).setVisible(false));
        router.attach(new RouteBuilder("/repos/{id}/executed", ExecutedCommandsResource.class).setVisible(false));
        router.attach(new RouteBuilder("/repos/{id}/executed/{timestamp}", ExecutedCommandResource.class).setVisible(false));
        // @formatter:on
    }

    public synchronized DbRepository getRepository() {
        if (this.repository == null) {
            this.repository = new Repository(emf);
        }
        return this.repository;
    }

    @SuppressWarnings("unchecked")
    public void addExecutedCommand(long timeInMillis, ExecuteMavenCommand command) {
        Map<Long, Command> executedCommands = (Map<Long, Command>) getContext().getAttributes().get(
                "skysail.executedCommands");
        if (executedCommands == null) {
            executedCommands = new HashMap<Long, Command>();
        }
        executedCommands.put(timeInMillis, command);
        getContext().getAttributes().put("skysail.executedCommands", executedCommands);
    }
    
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

}
