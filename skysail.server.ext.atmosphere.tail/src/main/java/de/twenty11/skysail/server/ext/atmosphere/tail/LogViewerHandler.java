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

package de.twenty11.skysail.server.ext.atmosphere.tail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.input.Tailer;
import org.apache.commons.io.input.TailerListenerAdapter;
import org.atmosphere.cpr.AtmosphereHandler;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereResourceEvent;
import org.atmosphere.cpr.Broadcaster;
import org.restlet.Client;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.Form;

public class LogViewerHandler extends TailerListenerAdapter implements AtmosphereHandler<Request, Response> {

    private final static String FILE_TO_WATCH = "/var/log/";
    //private final static String FILE_TO_WATCH = "d://temp";
    private static Tailer tailer;
    private Broadcaster GLOBAL_BROADCASTER = null;
    private Form query;
    private static List<String> watchableLogs = new ArrayList<String>();
    
    public LogViewerHandler(Form query) {
        this.query = query;
        
        final File logsDir = new File(FILE_TO_WATCH);
        if (logsDir.exists() && logsDir.isDirectory()) {
            File[] logs = logsDir.listFiles();
            for (File f : logs) {
                if (f.getName().endsWith(".log")) {
                    watchableLogs.add(f.getName());
                }
            }
        } else {
            System.out.println("either logsDir doesn't exist or is not a folder");
        }
    }
    
    @Override
    public void onRequest(AtmosphereResource<Request, Response> resource) throws IOException {
        Request req = resource.getRequest();
        Response res = resource.getResponse();
        Map<String, Object> attributes = req.getAttributes();
        
        Form headers = (Form)attributes.get("org.restlet.http.headers");
        if (headers == null) {
            headers = new Form();
            attributes.put("org.restlet.http.headers", headers);
        }
//        res.setContentType("text/html");
//        res.addHeader("Cache-Control", "private");
//        res.addHeader("Pragma", "no-cache");

//        if (req.getMethod().equalsIgnoreCase("GET")) {

            resource.suspend();
            if (GLOBAL_BROADCASTER == null) {
                GLOBAL_BROADCASTER = resource.getBroadcaster();
            }

            if (watchableLogs.size() != 0) {
                GLOBAL_BROADCASTER.broadcast(asJsonArray("logs", watchableLogs));
            }
//res.
//            res.getWriter().flush();
//        } else { // POST
//
//            // Very lame... req.getParameterValues("log")[0] doesn't work
//            final String postPayload = req.getReader().readLine();
//            if (postPayload != null && postPayload.startsWith("log=")) {
//                tailer = Tailer.create(new File(FILE_TO_WATCH + "//" + postPayload.split("=")[1]), this, 500);
//            }
//            GLOBAL_BROADCASTER.broadcast(asJson("filename", postPayload.split("=")[1]));
//            res.getWriter().flush();
//        }
        
    }

    @Override
    public void onStateChange(AtmosphereResourceEvent<Request, Response> event) throws IOException {
        Response res = event.getResource().getResponse();
        if (event.isResuming()) {
            //res.getWriter().write("Atmosphere closed<br/>");
            //res.getWriter().write("</body></html>");
        } else {
            //res.getWriter().write(event.getMessage().toString());
        }
        //res.getWriter().flush();
        
    }

    @Override
    public void onRequest(AtmosphereResource resource) throws IOException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onStateChange(AtmosphereResourceEvent event) throws IOException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void destroy() {
        tailer.stop();
    }

    protected String asJson(final String key, final String value) {
        return "{\"" + key + "\":\"" + value + "\"}";
    }

    protected String asJsonArray(final String key, final List<String> list) {

        //return ("{\"" + key + "\":" + JSONValue.toJSONString(list) + "}");
        return ("{\"" + key + "\":" + list.toString() + "}");
    }
}
