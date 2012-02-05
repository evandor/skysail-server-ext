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

package de.twenty11.skysail.server.ext.osgi.eventlogger;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class EventLoggerBundleEvent {

    @Id
    @GeneratedValue
    private int id;

    private String symbolicName;
    private long timestamp;
    private int eventType;
    
    public EventLoggerBundleEvent() {
        Calendar cal = Calendar.getInstance();
        this.timestamp = cal.getTimeInMillis();
    }
    
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("");
        sb.append(timestamp).append(", ").append(symbolicName).append(", ").append(eventType);
        return sb.toString();
    }
    
    public int getId() {
        return this.id;
    }
    public long getTimestamp() {
        return this.timestamp;
    }
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    public String getSymbolicName() {
        return symbolicName;
    }
    public void setSymbolicName(String symbolicName) {
        this.symbolicName = symbolicName;
    }
    public int getEventType() {
        return eventType;
    }
    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

}
