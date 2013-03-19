package de.twenty11.skysail.server.ext.dbviewer.experimental;

public class Algorithm {

    public Algorithm() {
        // TODO Auto-generated constructor stub
    }

    public void runAlgorithm() {
        System.out.println("running the algorithm");
        try {
            // do something here -
            // simulate some real time-consuming operation here
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
