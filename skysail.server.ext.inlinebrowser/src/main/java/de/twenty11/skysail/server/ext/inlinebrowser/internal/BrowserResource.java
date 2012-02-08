package de.twenty11.skysail.server.ext.inlinebrowser.internal;

import org.restlet.data.MediaType;
import org.restlet.ext.freemarker.TemplateRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.template.Template;

public class BrowserResource {

    /** slf4j based logger implementation */
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Get("html")
    public Representation getHtml() {
        try {
            //Template ftlTemplate = CommunicationUtils.getFtlTemplate("skysail.server.ext.inlinebrowser:browser.ftl");
            Template ftlTemplate = ServiceProvider.getFreemarkerConfig().getTemplate("skysail.server.ext.inlinebrowser:browser.ftl");
            return new TemplateRepresentation(ftlTemplate, null, MediaType.TEXT_HTML);
        } catch (Exception e) {
            // TODO
            return new StringRepresentation(e.getMessage());
        }
    }
}
