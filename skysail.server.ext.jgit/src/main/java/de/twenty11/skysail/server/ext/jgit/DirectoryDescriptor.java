package de.twenty11.skysail.server.ext.jgit;

import java.util.Collections;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnore;

import de.twenty11.skysail.common.Presentable;
import de.twenty11.skysail.common.PresentableHeader;

public class DirectoryDescriptor implements Presentable {

    private String filename;
    private String path;

    public DirectoryDescriptor(String filename, String path) {
        this.filename = filename;
        this.path = path;
    }

    public String getFilename() {
        return filename;
    }

    public String getPath() {
        return path;
    }

    @Override
    @JsonIgnore
    public PresentableHeader getHeader() {
        return new PresentableHeader.Builder(filename).setLink(path).build();
    }

    @Override
    @JsonIgnore
    public Map<String, Object> getContent() {
        return Collections.emptyMap();
    }

}
