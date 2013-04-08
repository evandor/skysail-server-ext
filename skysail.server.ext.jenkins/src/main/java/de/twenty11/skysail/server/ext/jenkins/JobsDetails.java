package de.twenty11.skysail.server.ext.jenkins;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnore;

import de.twenty11.skysail.common.Presentable;
import de.twenty11.skysail.common.PresentableHeader;

public class JobsDetails implements Presentable {

    private String name;
    private String url;
    private String color;

    public JobsDetails(String name, String url, String color) {
        this.name = name;
        this.url = url;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getColor() {
        return color;
    }

    @Override
    @JsonIgnore
    public PresentableHeader getHeader() {
        return new PresentableHeader.Builder(name + " / " + url).build();
    }

    @Override
    @JsonIgnore
    public Map<String, Object> getContent() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("name", name);
        result.put("url", url);
        result.put("color", color);
        return result;
    }

}
