package de.twenty11.skysail.server.ext.bookmarks.resources;

import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnore;

import de.twenty11.skysail.common.Presentable;
import de.twenty11.skysail.common.PresentableHeader;

public class BookmarkNode implements Presentable {

    @Override
    @JsonIgnore
    public PresentableHeader getHeader() {
        return new PresentableHeader.Builder("hi").build();
    }

    @Override
    @JsonIgnore
    public Map<String, Object> getContent() {
        return null;
    }

}
