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
//package de.twenty11.skysail.server.ext.atmosphere.tail;
//
//import javax.ws.rs.FormParam;
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.Produces;
//
//import org.atmosphere.annotation.Broadcast;
//import org.atmosphere.cpr.Broadcaster;
//import org.atmosphere.jersey.Broadcastable;
//import org.atmosphere.jersey.SuspendResponse;
//import org.cometd.server.CometdServlet;
//import org.restlet.resource.Get;
//import org.restlet.resource.ServerResource;
//
//@Path("/pubsub/{topic}")
//@Produces("text/html;charset=ISO-8859-1")
//public class LogFileResource extends ServerResource {
//
//    private @PathParam("topic") Broadcaster topic;
//    private LogViewerHandler logViewerHandler;
//    
//    public LogFileResource() {
//        logViewerHandler = new LogViewerHandler(getQuery());
//        
//        CometdServlet cometdServlet = new CometdServlet();
//        //cometdServlet.
//        
//    }
//
//    @Get("html")
//    public SuspendResponse<String> subscribe() {
//        
//       // logViewerHandler.onRequest(resource)
//        
//        return new SuspendResponse.SuspendResponseBuilder<String>().broadcaster(topic).outputComments(true)
//                        .addListener(new EventsLogger()).build();
//    }
//
//    @POST
//    @Broadcast
//    public Broadcastable publish(@FormParam("message") String message) {
//        return new Broadcastable(message, "", topic);
//    }
//}
