package de.twentyeleven.skysail.domain.test;

import org.junit.After;
import org.junit.Before;

import de.twentyeleven.skysail.domain.RssFeed;

public class RssFeedTest {

    private RssFeed RssFeed;

    @Before
    public void setUp() throws Exception {
        RssFeed = new RssFeed("title", "content");
    }

    @After
    public void tearDown() throws Exception {
    }

//    @Test
//    public void testSingleNote() throws Exception {
//        assertThat(myEntity.getHtml(), containsString("title"));
//        assertThat(myEntity.getHtml(), containsString("content"));
//    }

}
