package de.twenty11.skysail.server.ext.dbviewer;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.validation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.bootstrap.GenericBootstrap;

import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.twenty11.skysail.common.SkysailData;
import de.twenty11.skysail.common.ext.dbviewer.ConnectionDetails;
import de.twenty11.skysail.common.ext.dbviewer.RestfulConnections;
import de.twenty11.skysail.common.responses.FailureResponse;
import de.twenty11.skysail.common.responses.Response;
import de.twenty11.skysail.common.responses.SuccessResponse;
import de.twenty11.skysail.server.ext.dbviewer.internal.SkysailApplication;
import de.twenty11.skysail.server.restlet.GenericServerResource;

public class ConnectionsResource extends GenericServerResource<List<ConnectionDetails>> implements RestfulConnections {

    /** slf4j based logger implementation */
    private static Logger logger = LoggerFactory.getLogger(ConnectionsResource.class);

    private Validator validator;

    public ConnectionsResource() {
        GenericBootstrap validationProvider = Validation.byDefaultProvider();
        Configuration<?> config = validationProvider.providerResolver(new OSGiServiceDiscoverer())
                .configure();
        ValidatorFactory factory = config.buildValidatorFactory();
        validator = factory.getValidator();
    }

    @Override
    @Get
    public Response<List<ConnectionDetails>> getConnections() {
        Response<List<ConnectionDetails>> response;
        try {
            response = new SuccessResponse<List<ConnectionDetails>>(getFilteredData());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response = new FailureResponse<List<ConnectionDetails>>(e);
        }
        return response;
    }

    @Override
    @Post
    public Response<?> addConnection(ConnectionDetails entity) {
        Response<?> skysailResponse;
        Set<ConstraintViolation<ConnectionDetails>> constraintViolations = validator.validate(entity);
        int size = constraintViolations.size();
        if (size > 0) {
            logger.warn("contraint violations found on {}: {}", entity, constraintViolations);
            skysailResponse = new FailureResponse(constraintViolations.toString());
        } else {
            logger.info("trying to persist connection {}", entity);
            try {
                EntityManager em = ((SkysailApplication) getApplication()).getEntityManager();
                em.getTransaction().begin();
                em.persist(entity);
                em.getTransaction().commit();
                em.close();
            } catch (Exception e) {
                e.printStackTrace();
                skysailResponse = new FailureResponse(e.getMessage());
            }
            skysailResponse = new SuccessResponse<SkysailData>();
        }
        return skysailResponse;
    }

    @Override
    public void buildGrid() {
        setMessage("all Connections");
        EntityManager em = ((SkysailApplication) getApplication()).getEntityManager();
        @SuppressWarnings("unchecked")
        List<ConnectionDetails> resultList = em.createQuery("SELECT c FROM ext_dbv_connections c").getResultList();
        setSkysailData(resultList);
    }

}
