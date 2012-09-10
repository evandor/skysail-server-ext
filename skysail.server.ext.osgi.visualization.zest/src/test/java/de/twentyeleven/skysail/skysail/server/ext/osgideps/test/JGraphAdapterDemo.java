//package de.twentyeleven.skysail.skysail.server.ext.osgideps.test;
//
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.Rectangle;
//import java.awt.geom.Rectangle2D;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.swing.JApplet;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//
//import org.jgraph.JGraph;
//import org.jgraph.graph.DefaultGraphCell;
//import org.jgraph.graph.GraphConstants;
//
//import org.jgrapht.ListenableGraph;
//import org.jgrapht.ext.JGraphModelAdapter;
//import org.jgrapht.graph.ListenableDirectedGraph;
//import org.jgrapht.graph.DefaultEdge;
//
//public class JGraphAdapterDemo {
//    
//    
//    private static final Dimension DEFAULT_SIZE = new Dimension(530, 320);
//    private JGraphModelAdapter m_jgAdapter;
//    private JGraph jgraph = null;
//
//    /**
//     * @see java.applet.Applet#init().
//     */
//    public void run() {
//        // create a JGraphT graph
//        ListenableGraph g = new ListenableDirectedGraph(DefaultEdge.class);
//
//        // create a visualization using JGraph, via an adapter
//        m_jgAdapter = new JGraphModelAdapter(g);
//        jgraph = new JGraph(m_jgAdapter);
//        adjustDisplaySettings(jgraph);
//
//        // add some sample data (graph manipulated via JGraphT)
//        g.addVertex("v1");
//        g.addVertex("v2");
//        g.addVertex("v3");
//        g.addVertex("v4");
//
//        g.addEdge("v1", "v2");
//        g.addEdge("v2", "v3");
//        g.addEdge("v3", "v1");
//        g.addEdge("v4", "v3");
//
//        // position vertices nicely within JGraph component
//        positionVertexAt("v1", 130, 40);
//        positionVertexAt("v2", 60, 200);
//        positionVertexAt("v3", 310, 230);
//        positionVertexAt("v4", 380, 70);
//
//        // that's all there is to it!...
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
//    public JGraph getJgraph() {
//        return jgraph;
//    }
//
//    public void setJgraph(JGraph jgraph) {
//        this.jgraph = jgraph;
//    }
//    
//    public static void main(String[] args) {
//        JFrame f = new JFrame();
//        f.setSize(500, 500);
//        JPanel p = new JPanel();
//        p.setSize(500, 500);
//
//        JGraphAdapterDemo g = new JGraphAdapterDemo();
//        g.run();
//
//        JGraph jg = g.getJgraph();
//
//        p.add(jg);
//       
//        f.add(p);
//        f.setVisible(true);
//    }
// }
