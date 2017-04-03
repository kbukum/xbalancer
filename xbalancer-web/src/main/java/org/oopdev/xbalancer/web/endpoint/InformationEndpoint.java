package org.oopdev.xbalancer.web.endpoint;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Created by kamilbukum on 04/04/2017.
 */
@PermitAll
@RestController
public class InformationEndpoint {

    @RequestMapping(method = RequestMethod.GET)
    private String home() {
        return readMd("/docs/home.md");
    }


    public String readMd(String mdPath) {
        try {
            Parser parser = Parser.builder().build();
            Reader reader = new InputStreamReader(InformationEndpoint.class.getResourceAsStream(mdPath));
            Node document = parser.parseReader(reader);
            HtmlRenderer renderer = HtmlRenderer.builder().build();
            return renderer.render(document);  // "<p>This is <em>Sparta</em></p>\n"
        } catch (Exception e) {
            return "Information couldn read from md. Please check xbalancer/xbalancer-web/src/main/resources/docs path and read documents";
        }
    }
}
