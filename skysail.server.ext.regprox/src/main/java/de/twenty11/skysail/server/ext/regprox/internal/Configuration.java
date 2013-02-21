/**
 *  Copyright 2011 Carsten Gräf
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * 
 */

package de.twenty11.skysail.server.ext.regprox.internal;

import java.io.IOException;
import java.util.Dictionary;
import java.util.Properties;

import org.jsoup.nodes.Document;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.osgi.service.component.ComponentContext;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.routing.VirtualHost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Configuration implements ManagedService {

    private static Logger logger = LoggerFactory.getLogger(Configuration.class);
    private static ConfigurationAdmin configadmin;
    private MyComponent dbViewerComponent;
    private Server server;
    private ComponentContext context;
    private ServiceRegistration registration;

    protected void activate(ComponentContext ctxt) {
        logger.info("Activating Skysail Ext DbViewer Configuration Component");
        this.context = ctxt;
        if (startStandaloneServer()) {
            String port = "8554";// configService.getString(Constants.STANDALONE_PORT, "8554");
            logger.info("Starting standalone dbviewer server on port {}", port);
            dbViewerComponent = new MyComponent();
            startStandaloneServer(port);
        }
        // not standalone: see restlet book chapter 3.5.6
        VirtualHost virtualHost = createVirtualHost();
        this.registration = ctxt.getBundleContext().registerService("org.restlet.routing.VirtualHost", virtualHost,
                null);

        Document doc;

        // try {
        // WebClient webClient = new WebClient();
        //
        // HtmlPage currentPage = webClient.getPage("http://www.google.com/search?q=htmlunit");
        // final HtmlTable table = currentPage.getHtmlElementById("nav");
        // for (final HtmlTableRow row : table.getRows()) {
        // System.out.println("Found row");
        // for (final HtmlTableCell cell : row.getCells()) {
        // System.out.println("   Found cell: " + cell.asText());
        // }
        // }
        // System.out.println(currentPage.asXml());
        // // Get the first page
        // // HtmlPage page1 = webClient.getPage("http://www.google.de");
        // // System.out.println(page1.asXml());
        // //
        // // HtmlForm form = page1.getFormByName("f");
        // //
        // // HtmlSubmitInput button = form.getInputByValue("Google-Suche");
        // // HtmlTextInput textField = form.getInputByName("q");
        // //
        // // // Change the value of the text field
        // // textField.setValueAttribute("testing");
        // //
        // // // Now submit the form by clicking the button and get back the second page.
        // // final HtmlPage page2 = button.click();
        // // System.out.println(page2.asXml());
        // } catch (Exception e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        // // try {
        // // // doc = Jsoup.connect("http://en.wikipedia.org/").get();
        // // doc =
        // Jsoup.connect("http://www.google.de/search?hl=en&tbo=d&output=search&sclient=psy-ab&q=skysail").get();
        // // System.out.println(doc);
        // // Elements newsHeadlines = doc.select("#ires");
        // // for (Element element : newsHeadlines) {
        // // System.out.println(element);
        // // }
        // // } catch (IOException e) {
        // // // TODO Auto-generated catch block
        // // e.printStackTrace();
        // // }

    }

    private VirtualHost createVirtualHost() {
        VirtualHost vh = new VirtualHost();
        vh.setHostDomain("localhost");
        vh.setHostPort("2013");
        vh.attach(dbViewerComponent);

        return vh;
    }

    protected void deactivate(ComponentContext ctxt) {
        this.context = null;
        try {
            server.stop();
        } catch (Exception e) {
            logger.error("Exception when trying to stop standalone server", e);
        }
        if (registration != null) {
            registration.unregister();
        }
    }

    private boolean startStandaloneServer() {
        // String standalone = configService.getString(Constants.STANDALONE, "false");
        // if (!"true".equals(standalone)) {
        // logger.info("not starting standalone server, as {} is set to false or not configured", Constants.STANDALONE);
        // return false;
        // }
        return true;

    }

    private void startStandaloneServer(String portAsString) {
        try {
            server = new Server(Protocol.HTTP, Integer.valueOf(portAsString), dbViewerComponent);
            server.start();
        } catch (Exception e) {
            logger.error("Exception when starting standalone server", e);
        }
    }

    public synchronized void setConfigAdmin(ConfigurationAdmin configadmin) {
        Configuration.configadmin = configadmin;
    }

    private static String getStringFromConfigAdmin(String configElementName) {
        if (!isConfigAdminAvailable(configElementName))
            return "";
        try {
            return getConfigFromAdmin(configElementName);
        } catch (IOException e) {
            logger.error("could not retrieve key '{}' from configuration", e, configElementName);
            return null;
        }
    }

    private static String getConfigFromAdmin(String configElementName) throws IOException {
        org.osgi.service.cm.Configuration config = configadmin
                .getConfiguration("de.twenty11.skysail.server.config.Configuration");
        Object configObject = config.getProperties().get(configElementName);
        if (configObject == null) {
            logger.warn("could not retrieve configuration for key '{}'", configElementName);
            return null;
        }
        return configObject.toString();
    }

    private static boolean isConfigAdminAvailable(String configElementName) {
        if (configadmin == null) {
            logger.warn("could not retrieve key '{}' for skysail database as configadmin service does not exist",
                    configElementName);
            return false;
        }
        return true;
    }

    @Override
    public void updated(Dictionary properties) throws ConfigurationException {
        Dictionary config = properties == null ? getDefaultConfig() : properties;
        // this.context.getServiceReference().
    }

    private Dictionary getDefaultConfig() {
        return new Properties();
    }

}
