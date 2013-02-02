//package de.twenty11.skysail.server.ext.dbviewer.test.schemas;
//
//import static org.junit.Assert.assertTrue;
//
//import java.util.Set;
//
//import javax.validation.Configuration;
//import javax.validation.Validation;
//import javax.validation.Validator;
//import javax.validation.ValidatorFactory;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import de.twenty11.skysail.common.ext.dbviewer.SchemaDetails;
//import de.twenty11.skysail.server.ext.dbviewer.OSGiServiceDiscoverer;
//import de.twenty11.skysail.server.ext.dbviewer.internal.Schemas;
//
//public class SchemasTests {
//
//    private Schemas schemas;
//    private SchemaDetails schemaDetails;
//    private Validator validator;
//
//    @Before
//    public void setup() {
//        schemas = new Schemas();
//        schemaDetails = new SchemaDetails("name");
//        Configuration<?> config = Validation.byDefaultProvider().providerResolver(new OSGiServiceDiscoverer())
//                .configure();
//        ValidatorFactory factory = config.buildValidatorFactory();
//        validator = factory.getValidator();
//    }
//
//    @Test
//    public void testSuccessfullAdd() {
//        schemas.add(schemaDetails);
//    }
//
//    @Test
//    public void testList() throws Exception {
//        schemas.add(schemaDetails);
//        Set<String> list = schemas.list();
//        assertTrue(list.size() == 1);
//    }
//
//}
