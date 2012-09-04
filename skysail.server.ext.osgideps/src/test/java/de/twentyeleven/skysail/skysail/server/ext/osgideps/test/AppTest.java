package de.twentyeleven.skysail.skysail.server.ext.osgideps.test;

import org.jgraph.JGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
    /**
     * Create the test case
     * 
     * @param testName
     *            name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    public void testApp() {
        DefaultDirectedGraph<String, DefaultEdge> g = new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
        // Add vertices, e.g. equations.
        g.addVertex("a");
        g.addVertex("b");
        g.addVertex("c");
        g.addVertex("d");
        g.addVertex("e");

        // Add edges, e.g. dependencies.
        g.addEdge("b", "a");
        g.addEdge("c", "b");
        System.out.println(g.toString());
        
        JGraphModelAdapter adapter = new JGraphModelAdapter(g);
        JGraph jgraph = new JGraph(adapter);
        
        assertTrue(true);
    }
}
