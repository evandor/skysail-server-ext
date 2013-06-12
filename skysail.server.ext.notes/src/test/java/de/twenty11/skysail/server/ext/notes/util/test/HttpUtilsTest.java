package de.twenty11.skysail.server.ext.notes.util.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import org.junit.Before;
import org.junit.Test;

import de.twenty11.skysail.server.ext.notes.util.HttpUtils;

public class HttpUtilsTest {

    private HttpUtils httpUtils;

    @Before
    public void setUp() throws Exception {
        httpUtils = new HttpUtils();
    }

    @Test
    public void getGoogleStartPage() throws Exception {
        String result = httpUtils.get("http://www.google.de");
        assertThat(result, is(not(nullValue())));
        assertThat(result, containsString("<title>Google</title>"));
    }

}
