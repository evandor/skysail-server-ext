package de.twenty11.skysail.server.ext.regprox;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

/**
 * Restlet Root Resource for regprox application.
 * 
 */
public class UrlResource extends ServerResource {

    private String urlAsString;

    public UrlResource() {
        // setAutoDescribing(false);
        // setName("regprox url resource");
        // setDescription("The url resource of the regprox application");
        // getVariants().add(new Variant(MediaType.APPLICATION_JSON));
        // getVariants().add(new Variant(MediaType.TEXT_HTML));
    }

    @Override
    protected void doInit() throws ResourceException {
        urlAsString = (String) getRequest().getAttributes().get("url");
    }

    // @Override
    // protected Representation get(Variant variant) throws ResourceException {
    // StringRepresentation sr = new StringRepresentation(getDefaultPage());
    // sr.setMediaType(MediaType.TEXT_HTML);
    // return sr;
    // }

    @Get("html")
    public Representation getDefaultPage() {
        StringRepresentation sr;
        try {
            // http://localhost:8554/regprox/http%3A%2F%2Fwww.heise.de
            String decodedUrl = URLDecoder.decode(urlAsString, "UTF-8");
            Document doc = Jsoup.connect(decodedUrl).get();
            String html = doc.html();

            Elements links = doc.select("a[href]");
            Elements media = doc.select("[src]");
            Elements imports = doc.select("link[href]");

            print("\nMedia: (%d)", media.size());
            for (Element src : media) {
                if (src.tagName().equals("img")) {
                    print(" * %s: <%s> %sx%s (%s)", src.tagName(), src.attr("abs:src"), src.attr("width"),
                            src.attr("height"), trim(src.attr("alt"), 20));
                    // prefixLink(src);
                } else {
                    print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
                    // prefixLink(src);
                }
            }

            print("\nImports: (%d)", imports.size());
            for (Element link : imports) {
                print(" * %s <%s> (%s)", link.tagName(), link.attr("abs:href"), link.attr("rel"));
                String originalLink = link.attr("href");
                String newLink = "http://localhost:8554/regprox/";

                try {
                    URL url = new URL(originalLink);
                    newLink += URLEncoder.encode(url.getProtocol() + "://" + url.getHost() + ":" + url.getPort() + "/"
                            + url.getPath() + "?" + url.getQuery(), "UTF-8");
                } catch (MalformedURLException e) {
                    newLink += urlAsString + URLEncoder.encode(originalLink, "UTF-8");

                }
                link.attr("href", link.attr("abs:href"));
            }

            print("\nLinks: (%d)", links.size());
            for (Element link : links) {
                print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
                prefixLink(link);
            }

            // doc.select("a").attr("href", "http://www.test.de");
            sr = new StringRepresentation(doc.html());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            sr = new StringRepresentation("Error: " + e.getMessage());
        }
        sr.setMediaType(MediaType.TEXT_HTML);
        return sr;
    }

    private void prefixLink(Element link) throws UnsupportedEncodingException {
        String originalLink = link.attr("href");
        String newLink = "http://localhost:8554/regprox/";

        try {
            URL url = new URL(originalLink);
            newLink += URLEncoder.encode(
                    url.getProtocol() + "://" + url.getHost() + ":" + url.getPort() + "/" + url.getPath() + "?"
                            + url.getQuery(), "UTF-8");
        } catch (MalformedURLException e) {
            newLink += urlAsString + URLEncoder.encode(originalLink, "UTF-8");

        }
        link.attr("href", newLink);
    }

    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width - 1) + ".";
        else
            return s;
    }

}
