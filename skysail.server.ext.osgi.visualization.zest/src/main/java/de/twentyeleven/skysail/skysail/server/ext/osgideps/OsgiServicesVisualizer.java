//package de.twentyeleven.skysail.skysail.server.ext.osgideps;
//
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.Rectangle;
//import java.awt.geom.Rectangle2D;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//
//import org.jgraph.JGraph;
//import org.jgraph.graph.DefaultGraphCell;
//import org.jgraph.graph.GraphConstants;
//import org.jgrapht.ext.JGraphModelAdapter;
//import org.jgrapht.graph.DefaultEdge;
//import org.jgrapht.graph.ListenableDirectedGraph;
//import org.osgi.framework.Bundle;
//import org.osgi.framework.BundleContext;
//import org.osgi.framework.InvalidSyntaxException;
//import org.osgi.framework.ServiceReference;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class OsgiServicesVisualizer {
//
//    private ListenableDirectedGraph<String, ?> g;
//
//    private JGraph jgraph;
//
//    private JGraphModelAdapter m_jgAdapter;
//
//    private static final Dimension DEFAULT_SIZE = new Dimension(1030, 820);
//
//    private static final Logger logger = LoggerFactory.getLogger(OsgiServicesVisualizer.class);
//
//    public void buildGraphFromOsgiContext(BundleContext bundleContext) {
//        g = new ListenableDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
//
//        // create a visualization using JGraph, via an adapter
//        m_jgAdapter = new JGraphModelAdapter(g);
//        jgraph = new JGraph(m_jgAdapter);
//        adjustDisplaySettings(jgraph);
//
//        List<Bundle> relevantBundles = determineSkysailBundles(bundleContext);
//
//        try {
//            ServiceReference[] allServiceReferences = bundleContext.getAllServiceReferences(null, null);
//            for (ServiceReference ref : allServiceReferences) {
//                analyseServiceReference(relevantBundles, ref);
//            }
//        } catch (InvalidSyntaxException e) {
//            e.printStackTrace();
//        }
//
//        for (Bundle bundle : relevantBundles) {
//            ServiceReference[] registeredServices = bundle.getRegisteredServices();
//            if (registeredServices == null) {
//                continue;
//            }
//            for (ServiceReference serviceReference : registeredServices) {
//                Bundle referencedBundle = serviceReference.getBundle();
//                if (!relevantBundles.contains(referencedBundle)) {
//                    addToVertices(relevantBundles, referencedBundle);
//                }
//                addEdge(bundle, referencedBundle);
//            }
//        }
//
//        // position vertices nicely within JGraph component
//        // positionVertexAt("v1", 130, 40);
//        // positionVertexAt("v2", 60, 200);
//        // positionVertexAt("v3", 310, 230);
//        // positionVertexAt("v4", 380, 70);
//
//    }
//
//    private void addEdge(Bundle from, Bundle to) {
//        logger.info("adding edge from {} to {}", from.getSymbolicName(), to.getSymbolicName());
//        DefaultEdge addedEdge = (DefaultEdge) g.addEdge(from.getSymbolicName(), to.getSymbolicName());
//        // addedEdge.
//    }
//
//    private void analyseServiceReference(List<Bundle> relevantBundles, ServiceReference ref) {
//        Bundle registeringBundle = ref.getBundle();
//
//        Bundle[] usingBundles = ref.getUsingBundles();
//        if (usingBundles == null) {
//            return;
//        }
//
//        logger.info("checking {} for services", registeringBundle.getSymbolicName());
//        if (!relevantBundles.contains(registeringBundle.getSymbolicName())) {
//            addToVertices(relevantBundles, registeringBundle);
//        }
//
//        for (Bundle referencingBundle : usingBundles) {
//            if (!relevantBundles.contains(referencingBundle)) {
//                addToVertices(relevantBundles, referencingBundle);
//            }
//            addEdge(referencingBundle, registeringBundle);
//        }
//    }
//
//    private List<Bundle> determineSkysailBundles(BundleContext bundleContext) {
//        List<Bundle> relevantBundles = new ArrayList<Bundle>();
//        Bundle[] bundles = bundleContext.getBundles();
//        for (Bundle bundle : bundles) {
//            if (bundle.getSymbolicName().contains("skysail")) {
//                addToVertices(relevantBundles, bundle);
//            }
//        }
//        return relevantBundles;
//    }
//
//    private void addToVertices(List<Bundle> relevantBundles, Bundle bundle) {
//        logger.info("adding {} to vertices", bundle.getSymbolicName());
//        g.addVertex(bundle.getSymbolicName());
//        relevantBundles.add(bundle);
//    }
//
//    private void adjustDisplaySettings(JGraph jg) {
//        jg.setPreferredSize(DEFAULT_SIZE);
//        jg.setBackground(Color.GRAY);
//    }
//
//    private void positionVertexAt(Object vertex, int x, int y) {
//        DefaultGraphCell cell = m_jgAdapter.getVertexCell(vertex);
//        Map attr = cell.getAttributes();
//        Rectangle2D b = GraphConstants.getBounds(attr);
//
//        GraphConstants.setBounds(attr, new Rectangle(x, y, (int) b.getWidth(), (int) b.getHeight()));
//
//        Map cellAttr = new HashMap();
//        cellAttr.put(cell, attr);
//        m_jgAdapter.edit(cellAttr, null, null, null);
//    }
//
//    public void run() {
//        JFrame f = new JFrame();
//        f.setSize(500, 500);
//        JPanel p = new JPanel();
//        p.setSize(500, 500);
//        f.add(p);
//        p.add(jgraph);
//        f.setVisible(true);
//    }
//
// }
