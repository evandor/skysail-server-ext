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

package de.twenty11.skysail.server.ext.eclipselink;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import org.osgi.service.jdbc.DataSourceFactory;

/**
 * The bundles activator, taking care of creating a new restlet component,
 * application and url mapping listener.
 * 
 * @author carsten
 * 
 */
public class Activator implements BundleActivator {

	/**
	 * bundles life cycle start method, creating a new restlet component, a
	 * restlet application and the urlMapping listener.
	 * 
	 * @param context
	 *            the osgi bundle context
	 * @throws Exception
	 *             starting the component may trigger an Exception
	 * 
	 */
	public final void start(final BundleContext context) throws Exception {

		ServiceReference[] serviceReferences = context.getServiceReferences(
				DataSourceFactory.class.toString(), "("
						+ DataSourceFactory.OSGI_JDBC_DRIVER_CLASS
						+ "=org.apache.derby.jdbc.EmbeddedDriver)");
		if (serviceReferences != null) {
			DataSourceFactory dsf = (DataSourceFactory) context
					.getService(serviceReferences[0]);
			Properties props = new Properties();
			props.put(DataSourceFactory.JDBC_URL,
					"jdbc:derby:derbyDB;create=true");

			DataSource ds = dsf.createDataSource(props);

			Connection conn = ds.getConnection();
			Statement stat = conn.createStatement();
			stat.execute("INSERT INTO event-log VALUES (1, \"123\")");
			stat.close();
			conn.close();

		}
	}

	/**
	 * stop lifecycle method, removing the urlMapping listener and stopping the
	 * component.
	 * 
	 * @param context
	 *            the bundleContext
	 * @throws Exception
	 *             stopping the component might trigger an exception
	 * 
	 */
	public final void stop(final BundleContext context) throws Exception {
	}

}
