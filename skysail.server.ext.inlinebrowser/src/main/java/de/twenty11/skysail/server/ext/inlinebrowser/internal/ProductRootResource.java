package de.twenty11.skysail.server.ext.inlinebrowser.internal;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.common.messages.GridData;
import de.twenty11.skysail.server.SkysailServerResource;

public class ProductRootResource extends SkysailServerResource<GridData> {

    /** slf4j based logger implementation */
    Logger logger = LoggerFactory.getLogger(this.getClass());

   public ProductRootResource() {
       super(new GridData());
       setTemplate("skysail.product.osgimonitor:root.ftl");
   }
    
//    protected Representation dispatchGet(Variant variant) {
//        List<LinkData> links = new ArrayList<LinkData>();
//        //addLink(links, "components/", "Installed Components");
//        //addLink(links, "dbviewer/", "Database Viewer");
//        addLink(links, OsgiBundlesConstants.RESTLET_BUNDLE_CONTEXT_ID + "/", "Installed Bundles");
//        addLink(links, OsgiBundlesConstants.PACKAGES + "/", "Available Packages");
//        addLink(links, OsgiBundlesConstants.SERVICES + "/", "Services");
//        //addLink(links, "logs/", "Current Logs");
//        CommunicationUtils commUtils = new CommunicationUtils("skysail.server.osgi.bundles:home.ftl");
//        return commUtils.createLinkRepresentation(links, variant, getQuery(), getRequest(), "root");
//    }

//    @Override
//    public GridData getData() {
//        GridData grid = new GridData();
//        RowData rowData = new RowData();
//        
//        grid.addRowData(rowData );
//        
//        return null;
//    }

    @Override
    public void setColumns(GridData data) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<Object> getFilteredData() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int handlePagination() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public GridData currentPageResults(List<?> filterResults, int pageSize) {
        // TODO Auto-generated method stub
        return null;
    }
}
