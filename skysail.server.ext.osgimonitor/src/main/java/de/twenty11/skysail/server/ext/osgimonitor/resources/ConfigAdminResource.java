package de.twenty11.skysail.server.ext.osgimonitor.resources;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.restlet.data.Form;
import org.restlet.resource.ResourceException;

import de.twenty11.skysail.common.Presentation;
import de.twenty11.skysail.common.PresentationStyle;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.core.restlet.ListServerResource2;
import de.twenty11.skysail.server.ext.osgimonitor.OsgiMonitorViewerApplication;
import de.twenty11.skysail.server.ext.osgimonitor.domain.ConfigDescriptor;

@Presentation(preferred = PresentationStyle.LIST2)
public class ConfigAdminResource extends ListServerResource2<ConfigDescriptor> {

    private OsgiMonitorViewerApplication app;

    @Override
    protected void doInit() throws ResourceException {
        app = (OsgiMonitorViewerApplication) getApplication();
    }

    @Override
    protected List<ConfigDescriptor> getData() {
        List<ConfigDescriptor> result = new ArrayList<ConfigDescriptor>();
        ConfigurationAdmin configadmin = app.getConfigadmin();
        try {
            Configuration[] listConfigurations = configadmin.listConfigurations(null);
            for (Configuration configuration : listConfigurations) {
                result.add(new ConfigDescriptor(configuration));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ConfigDescriptor getData(Form form) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SkysailResponse<?> addEntity(ConfigDescriptor entity) {
        // TODO Auto-generated method stub
        return null;
    }

}
