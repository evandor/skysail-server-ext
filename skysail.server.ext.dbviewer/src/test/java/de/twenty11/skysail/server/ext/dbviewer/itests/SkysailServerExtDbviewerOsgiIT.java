package de.twenty11.skysail.server.ext.dbviewer.itests;

import static com.jayway.restassured.RestAssured.expect;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.ops4j.pax.exam.CoreOptions.bundle;
import static org.ops4j.pax.exam.CoreOptions.systemProperty;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.Configuration;
import org.ops4j.pax.exam.junit.ExamReactorStrategy;
import org.ops4j.pax.exam.junit.JUnit4TestRunner;
import org.ops4j.pax.exam.spi.reactors.AllConfinedStagedReactorFactory;

import com.jayway.restassured.RestAssured;

import de.twenty11.skysail.common.osgi.PaxExamOptionSet;
import de.twenty11.skysail.server.ext.dbviewer.internal.DbViewerApplicationDescriptor;

/**
 * @author carsten
 * 
 */
@RunWith(JUnit4TestRunner.class)
@ExamReactorStrategy(AllConfinedStagedReactorFactory.class)
public class SkysailServerExtDbviewerOsgiIT {

    private List<PaxExamOptionSet> dependencies = new ArrayList<PaxExamOptionSet>();

    @Configuration
    public Option[] config() {

        dependencies.add(PaxExamOptionSet.BASE);
        dependencies.add(PaxExamOptionSet.DEBUGGING);
        dependencies.add(PaxExamOptionSet.SERVICES_VISUALIZATION);

        SkysailServerExtDbViewerOsgiSetup setup = new SkysailServerExtDbViewerOsgiSetup();
        List<Option> options = setup.getOptions(EnumSet.copyOf(dependencies));

        // _this_ bundle from target directory
        options.add(bundle("file:target/skysail.server.ext.dbviewer-" + setup.getProjectVersion() + ".jar"));

        // options.add(systemProperty("org.osgi.service.http.port").value(
        // "8888" ));
        options.add(systemProperty("jetty.home.bundle").value("skysail.server"));
        options.add(systemProperty("ds.loglevel").value("4"));

        Option[] options2Use = options.toArray(new Option[options.size()]);
        setup.logOptionsUsed(options2Use);
        return options2Use;
    }

    /**
     * @param bc
     *            bundleContext
     * @throws IOException
     *             should not happen
     */
    @Test
    @Ignore
    public void test() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8554;
        expect().body("success", equalTo(true)).given().auth().basic("scott", "tiger").when()
                .get("/" + DbViewerApplicationDescriptor.APPLICATION_NAME +"/?media=json");
    }

    @Test
    @Ignore
    public void testPostConnectionDetails() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8554;

        // RequestSpecification spec = getRequestSpec();

        // @formatter:off
        expect()
            .body(not(equalTo(""))) //"greeting.firstName", equalTo("John"))
        .given()
            .auth().basic("scott", "tiger")
            .contentType("application/json")
            .request().body("{\"connectionName\" : \"there\", \"username\" : \"root\"}")
        .when()
            .post("/"+DbViewerApplicationDescriptor.APPLICATION_NAME+"/");

        //String json = given().auth().basic("scott", "tiger").get("/dbviewer/?media=json").asString();
        
        expect()
            .body("success", equalTo(true))
            .body("message", equalTo("all DataSource"))
            .body("navigation.parent", equalTo("http://localhost:8554/?media=json"))
            .body("pagination.totalResults", equalTo(1))
        .given()
            .auth().basic("scott", "tiger")
        .when()
            .get("/"+DbViewerApplicationDescriptor.APPLICATION_NAME+"/?media=json");

    }

//    private RequestSpecification getRequestSpec() {
//        RequestSpecBuilder builder = new RequestSpecBuilder();
//    }
}
