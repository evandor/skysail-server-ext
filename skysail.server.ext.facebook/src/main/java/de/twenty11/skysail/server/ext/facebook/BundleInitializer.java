//package de.twenty11.skysail.server.ext.facebook;
//
//import javax.persistence.EntityManagerFactory;
//
//import org.osgi.service.cm.ConfigurationException;
//import org.osgi.service.component.ComponentContext;
//import org.restlet.Application;
//import org.restlet.Component;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import de.twenty11.skysail.server.config.ServerConfiguration;
//import de.twenty11.skysail.server.services.ApplicationProvider;
//import de.twenty11.skysail.server.services.ComponentProvider;
//
///**
// * OSGi life-cycle management class for this bundle, configured in init.xml.
// * 
// * This class provides the facebook skysail application once the preconditions (see init.xml) are met.
// * 
// * 
// */
//public class BundleInitializer implements ApplicationProvider {
//
//    private static Logger logger = LoggerFactory.getLogger(BundleInitializer.class);
//    private ComponentProvider componentProvider;
//    private Component component;
//    private FacebookApplication application;
//    private EntityManagerFactory emf;
//    private ServerConfiguration config;
//
//    protected void activate(ComponentContext componentContext) throws ConfigurationException {
//        logger.info("Creating Application for Skysail Facebook Extension");
//        component = componentProvider.getComponent();
//        application = new FacebookApplication(component.getContext(), emf);
//    }
//
//    protected void deactivate(ComponentContext componentContext) {
//        logger.info("Deactivating Application for Skysail Faceboook Extension");
//        component.getDefaultHost().detach(application);
//        application = null;
//    }
//
//    public void setComponentProvider(ComponentProvider componentProvider) {
//        this.componentProvider = componentProvider;
//    }
//
//    @Override
//    public Application getApplication() {
//        return application;
//    }
//
//    public synchronized void setEntityManager(EntityManagerFactory emf) {
//        this.emf = emf;
//    }
//
//    public void setServerConfiguration(ServerConfiguration config) {
//        this.config = config;
//    }
//
// }
