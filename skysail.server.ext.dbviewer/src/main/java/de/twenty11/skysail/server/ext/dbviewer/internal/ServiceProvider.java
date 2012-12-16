///**
// *  Copyright 2011 Carsten Gräf
// *
// *  Licensed under the Apache License, Version 2.0 (the "License");
// *  you may not use this file except in compliance with the License.
// *  You may obtain a copy of the License at
// *
// *   http://www.apache.org/licenses/LICENSE-2.0
// *
// *  Unless required by applicable law or agreed to in writing, software
// *  distributed under the License is distributed on an "AS IS" BASIS,
// *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// *  See the License for the specific language governing permissions and
// *  limitations under the License.
// * 
// */
//
//package de.twenty11.skysail.server.ext.dbviewer.internal;
//
//import org.osgi.service.component.ComponentContext;
//import org.restlet.Server;
//import org.restlet.data.Protocol;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import de.twenty11.skysail.server.ext.dbviewer.Constants;
//import de.twenty11.skysail.server.services.ConfigService;
//
//public class ServiceProvider {
//
//    private static ConfigService configService;
//    /** slf4j based logger implementation */
//    private Logger logger = LoggerFactory.getLogger(this.getClass());
//    private DbViewerComponent dbViewerComponent;
//    private Server server;
//
//    protected void activate(ComponentContext ctxt) {
//        logger.error("Activating DbViewer ServiceProvider");
//        if (startStandaloneServer()) {
//            String port = configService.getString(Constants.STANDALONE_PORT, "8554");
//            logger.info("starting standalone dbviewer server on port {}", port);
//            dbViewerComponent = new DbViewerComponent();
//            startStandaloneServer(port);
//        }
//    }
//
//    protected void deactivate(ComponentContext ctxt) {
//        try {
//            server.stop();
//        } catch (Exception e) {
//            logger.error("Exception when trying to stop standalone server", e);
//        }
//    }
//
//    private void startStandaloneServer(String portAsString) {
//        try {
//            server = new Server(Protocol.HTTP, Integer.valueOf(portAsString), dbViewerComponent);
//            server.start();
//        } catch (Exception e) {
//            logger.error("Exception when starting standalone server", e);
//        }
//    }
//
//    private boolean startStandaloneServer() {
//        String standalone = configService.getString(Constants.STANDALONE, "false");
//        if (!"true".equals(standalone)) {
//            logger.info("not starting standalone server, as {} is set to false or not configured", Constants.STANDALONE);
//            return false;
//        }
//        return true;
//
//    }
//
//    public synchronized void setConfigService(ConfigService configService) {
//        ServiceProvider.configService = configService;
//    }
//
//    public static ConfigService getConfigService() {
//        return configService;
//    }
//
//}
