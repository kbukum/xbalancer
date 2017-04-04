package org.oopdev.xbalancer.dummy;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import io.undertow.Undertow;
import io.undertow.util.Headers;

/**
 * Created by kamilbukum on 04/04/2017.
 */
public class Application {
    @Parameter(names = {"--name", "-n"})
    private String name;
    @Parameter(names = {"--host", "-h"})
    private String host;
    @Parameter(names = {"--port", "-p"})
    private int port;

    public static void main(String[] args) {
        Application main = new Application();
        new JCommander(main, args);
        main.run();
    }

    public void run() {
        final Undertow server1 = Undertow.builder()
                .addHttpListener(port, host)
                .setHandler((exchange) -> {
                    exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
                    exchange.getResponseSender().send("This is " + name + " server");
                })
                .build();
        server1.start();
    }
}
