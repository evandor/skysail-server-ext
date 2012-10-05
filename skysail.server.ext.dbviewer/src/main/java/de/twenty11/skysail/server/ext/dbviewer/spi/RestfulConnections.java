package de.twenty11.skysail.server.ext.dbviewer.spi;

import org.restlet.representation.Variant;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

import de.twenty11.skysail.common.grids.GridData;
import de.twenty11.skysail.common.responses.SkysailResponse;
import de.twenty11.skysail.server.ext.dbviewer.internal.entities.ConnectionDetails;

/**
 * Restful Http Service for Connections.
 * 
 */
public interface RestfulConnections {

	/**
	 * Returns a GridData Object (which is wrapped in a SkysailResponse) with all available Connections.
	 * @param variant
	 * @return a subclass of SkysailResponse.
	 */
	@Get
	public SkysailResponse<GridData> getConnections(Variant variant);

	/**
	 * @param entity
	 * @return
	 */
	@Post()
	public SkysailResponse<?> addConnection(ConnectionDetails entity);

}
