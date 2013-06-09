package de.twenty11.skysail.server.ext.facebook.util.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import org.junit.Test;

import de.twenty11.skysail.server.ext.facebook.util.FacebookStringUtils;

public class FacebookStringUtilsTest {

    @Test
    public void extracts_token_from_TokenQuery() {
        String strContainingToken = "access_token={access-token}&expires={seconds-til-expiration}";
        String token = FacebookStringUtils.extractAccessTokenFromFbResponse(strContainingToken);
        assertThat(token, equalTo("{access-token}"));
    }

    @Test
    public void extracts_null_from_null_TokenQuery() {
        String strContainingToken = null;
        String token = FacebookStringUtils.extractAccessTokenFromFbResponse(strContainingToken);
        assertThat(token, is(nullValue()));
    }

}
