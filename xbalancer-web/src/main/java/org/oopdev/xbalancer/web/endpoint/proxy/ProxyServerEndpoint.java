package org.oopdev.xbalancer.web.endpoint.proxy;

import org.oopdev.xbalancer.domain.proxy.ProxyServerAddress;
import org.oopdev.xbalancer.service.proxy.ProxyServerService;
import org.oopdev.xbalancer.web.endpoint.CrudBaseEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "proxyServers")
public class ProxyServerEndpoint extends CrudBaseEndpoint<ProxyServerAddress, String> {

    private final ProxyServerService proxyServerService;

    @Autowired
    public ProxyServerEndpoint(ProxyServerService proxyServerService) {
        super(proxyServerService);
        this.proxyServerService = proxyServerService;
    }

    /*
    @RequestMapping(method = RequestMethod.GET, value = "start/{oid}")
    public ProxyServerAddress start(@PathVariable("oid") String id) {
        return proxyServerService.start(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "stop/{oid}")
    public ProxyServerAddress stop(@PathVariable("oid") String id) {
        return proxyServerService.start(id);
    }
    */

}
