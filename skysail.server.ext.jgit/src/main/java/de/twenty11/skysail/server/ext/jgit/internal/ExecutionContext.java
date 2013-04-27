package de.twenty11.skysail.server.ext.jgit.internal;

import java.util.Collections;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnore;

import de.twenty11.skysail.common.Presentable;
import de.twenty11.skysail.common.PresentableHeader;

public class ExecutionContext implements Presentable {

    private Long timestamp;

    public ExecutionContext(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    @JsonIgnore
    public PresentableHeader getHeader() {
        return new PresentableHeader.Builder(timestamp.toString()).setLink("executed/" + timestamp.toString()).build();
    }

    @Override
    @JsonIgnore
    public Map<String, Object> getContent() {
        return Collections.emptyMap();
    }

}
