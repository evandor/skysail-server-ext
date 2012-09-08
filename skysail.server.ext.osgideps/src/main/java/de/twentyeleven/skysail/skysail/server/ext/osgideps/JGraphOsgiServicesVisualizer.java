package de.twentyeleven.skysail.skysail.server.ext.osgideps;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

public class JGraphOsgiServicesVisualizer extends JFrame {

    private static final long serialVersionUID = 7110246569384550189L;

    private static final Logger logger = LoggerFactory.getLogger(JGraphOsgiServicesVisualizer.class);

    private mxGraph graph;

    private Map<String, Object> verticesMap = new HashMap<String, Object>();

    private mxGraphComponent graphComponent;

    public JGraphOsgiServicesVisualizer() {
        super("skysail OSGi services visualization");
    }

    public void buildGraphFromOsgiContext(BundleContext bundleContext) {
        graph = new mxGraph();

        // mxFastOrganicLayout layout = new mxFastOrganicLayout(graph);
        mxCircleLayout layout = new mxCircleLayout(graph);
        layout.setRadius(30);
        // layout.setMinDistanceLimit(10);
        // layout.setInitialTemp(10);
        // layout.setForceConstant(120);
        // layout.setDisableEdgeStyle(true);

        graphComponent = new mxGraphComponent(graph);

        Object parent = graph.getDefaultParent();

        getContentPane().add(BorderLayout.CENTER, graphComponent);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setVisible(true);

        List<Bundle> skysailBundles = determineSkysailBundles(bundleContext);
        ServiceReference[] allServiceReferences = getServiceReferences(bundleContext);
        createGraph(parent, skysailBundles, allServiceReferences);

        layout.execute(graph.getDefaultParent());

    }

    public void run() {
    }

    private List<Bundle> determineSkysailBundles(BundleContext bundleContext) {
        List<Bundle> relevantBundles = new ArrayList<Bundle>();
        Bundle[] bundles = bundleContext.getBundles();
        for (Bundle bundle : bundles) {
            if (bundle.getSymbolicName().contains("skysail")) {
                relevantBundles.add(bundle);
            }
        }
        return relevantBundles;
    }

    private ServiceReference[] getServiceReferences(BundleContext bundleContext) {
        ServiceReference[] allServiceReferences = new ServiceReference[0];
        try {
            allServiceReferences = bundleContext.getAllServiceReferences(null, null);
        } catch (InvalidSyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return allServiceReferences;
    }

    private void createGraph(Object parent, List<Bundle> skysailBundles, ServiceReference[] allServiceReferences) {
        graph.getModel().beginUpdate();
        try {
            addInitialVertices(parent, skysailBundles);
            addEdges(allServiceReferences);
            removeEdgesWithoutReferences();
        } finally {
            graph.getModel().endUpdate();
        }
    }

    private void addInitialVertices(Object parent, List<Bundle> skysailBundles) {
        for (Bundle bundle : skysailBundles) {
            addVertex(bundle);
        }
    }

    private void addVertex(Bundle bundle) {
        if (!verticesMap.containsKey(bundle.getSymbolicName())) {
            logger.info("adding {} to vertices", bundle.getSymbolicName());
            String vertexName = bundle.getSymbolicName() + " [" + bundle.getBundleId() + "]";
            Object vertex = graph.insertVertex(graph.getDefaultParent(), null, vertexName, 20, 20, 180, 30);
            verticesMap.put(bundle.getSymbolicName(), vertex);
        }
    }

    private void addEdges(ServiceReference[] allServiceReferences) {
        for (ServiceReference ref : allServiceReferences) {
            analyseServiceReference(ref);
        }
    }

    private void analyseServiceReference(ServiceReference ref) {
        Bundle registeringBundle = ref.getBundle();

        Bundle[] usingBundles = ref.getUsingBundles();
        if (usingBundles == null) {
            return;
        }

        logger.info("checking {} for services", registeringBundle.getSymbolicName());
        addVertex(registeringBundle);

        for (Bundle referencingBundle : usingBundles) {
            if (!verticesMap.containsKey(referencingBundle.getSymbolicName())) {
                addVertex(registeringBundle);
            }
            String edgeLabel = getEdgeLabel(ref);

            graph.insertEdge(graph.getDefaultParent(), null, edgeLabel,
                    verticesMap.get(referencingBundle.getSymbolicName()),
                    verticesMap.get(registeringBundle.getSymbolicName()));
        }
    }

    private String getEdgeLabel(ServiceReference ref) {
        Long serviceId = (Long) ref.getProperty(Constants.SERVICE_ID);
        String[] serviceClass = (String[]) ref.getProperty(Constants.OBJECTCLASS);
        StringBuilder sb = new StringBuilder();
        sb.append("ServiceID: ");
        sb.append(serviceId).append("\n");
        if (serviceClass.length > 0) {
            for (String classname : serviceClass) {
                String[] split = classname.split("\\.");
                sb.append(split[split.length - 1]);
                sb.append(" / ");
            }
            sb.delete(sb.length() - 3, sb.length() - 1);
        }
        return sb.toString();
    }

    private void removeEdgesWithoutReferences() {
        for (String bundleName : verticesMap.keySet()) {
            mxCell bundle = (mxCell) verticesMap.get(bundleName);
            Object[] allEdges = graph.getAllEdges(new mxCell[] { bundle });
            if (allEdges.length == 0) {
                logger.info("removing vertex for '{}' as it does not have any edges attached", bundleName);
                graph.removeCells(new mxCell[] { bundle });
            }
        }
    }

}
