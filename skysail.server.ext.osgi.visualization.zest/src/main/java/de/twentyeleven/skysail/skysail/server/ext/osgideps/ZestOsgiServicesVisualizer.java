package de.twentyeleven.skysail.skysail.server.ext.osgideps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.zest.core.widgets.Graph;
import org.eclipse.zest.core.widgets.GraphConnection;
import org.eclipse.zest.core.widgets.GraphNode;
import org.eclipse.zest.core.widgets.ZestStyles;
import org.eclipse.zest.layouts.LayoutStyles;
import org.eclipse.zest.layouts.algorithms.SpringLayoutAlgorithm;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZestOsgiServicesVisualizer extends JFrame {

    private static final long serialVersionUID = 7110246569384550189L;

    private static final Logger logger = LoggerFactory.getLogger(ZestOsgiServicesVisualizer.class);

    private Map<String, Object> verticesMap = new HashMap<String, Object>();

    private Graph graph;

    public ZestOsgiServicesVisualizer() {
        super("skysail OSGi services visualization");
    }

    public void buildGraphFromOsgiContext(BundleContext bundleContext) {
        Display d = new Display();
        Shell shell = new Shell(d);
        shell.setText("GraphSnippet1");
        shell.setLayout(new FillLayout());
        shell.setSize(400, 400);

        graph = new Graph(shell, SWT.NONE);
        // Now a few nodes
        GraphNode node1 = new GraphNode(graph, SWT.NONE, "Jim");
        GraphNode node2 = new GraphNode(graph, SWT.NONE, "Jack");
        GraphNode node3 = new GraphNode(graph, SWT.NONE, "Joe");
        GraphNode node4 = new GraphNode(graph, SWT.NONE, "Bill");
        // Lets have a directed connection
        new GraphConnection(graph, ZestStyles.CONNECTIONS_DIRECTED, node1, node2);
        // Lets have a dotted graph connection
        new GraphConnection(graph, ZestStyles.CONNECTIONS_DOT, node2, node3);
        // Standard connection
        new GraphConnection(graph, SWT.NONE, node3, node1);
        // Change line color and line width
        GraphConnection graphConnection = new GraphConnection(graph, SWT.NONE, node1, node4);
        graphConnection.changeLineColor(shell.getDisplay().getSystemColor(SWT.COLOR_GREEN));
        // Also set a text
        graphConnection.setText("This is a text");
        graphConnection.setHighlightColor(shell.getDisplay().getSystemColor(SWT.COLOR_RED));
        graphConnection.setLineWidth(3);

        graph.setLayoutAlgorithm(new SpringLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING), true);
        // Selection listener on graphConnect or GraphNode is not supported
        // see https://bugs.eclipse.org/bugs/show_bug.cgi?id=236528
        graph.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                System.out.println(e);
            }

        });
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
        addInitialVertices(parent, skysailBundles);
        addEdges(allServiceReferences);
        removeEdgesWithoutReferences();
    }

    private void addInitialVertices(Object parent, List<Bundle> skysailBundles) {
        for (Bundle bundle : skysailBundles) {
            addVertex(bundle);
        }
    }

    private void addVertex(Bundle bundle) {
        // if (!verticesMap.containsKey(bundle.getSymbolicName())) {
        // logger.info("adding {} to vertices", bundle.getSymbolicName());
        // String vertexName = bundle.getSymbolicName() + " [" + bundle.getBundleId() + "]";
        // Object vertex = graph.insertVertex(shell, null, vertexName, 20, 20, 180, 30);
        // verticesMap.put(bundle.getSymbolicName(), vertex);
        // }
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

            // graph.insertEdge(graph.getDefaultParent(), null, edgeLabel,
            // verticesMap.get(referencingBundle.getSymbolicName()),
            // verticesMap.get(registeringBundle.getSymbolicName()));
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
        // for (String bundleName : verticesMap.keySet()) {
        // mxCell bundle = (mxCell) verticesMap.get(bundleName);
        // Object[] allEdges = graph.getAllEdges(new mxCell[] { bundle });
        // if (allEdges.length == 0) {
        // logger.info("removing vertex for '{}' as it does not have any edges attached", bundleName);
        // // graph.removeCells(new mxCell[] { bundle });
        // }
        // }
    }

}
