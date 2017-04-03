package org.oopdev.xbalancer.proxy.balancers;

import io.undertow.server.HttpServerExchange;
import org.oopdev.xbalancer.proxy.host.Host;

import java.util.Map;

/**
 * Created by kamilbukum on 03/04/2017.
 */
@FunctionalInterface
public interface HostFilter {
    Map<String, Host> filter(HttpServerExchange exchange, Map<String, Host> hostMap);
}
