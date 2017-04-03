package org.oopdev.xbalancer.web.endpoint.proxy;

import org.oopdev.xbalancer.domain.proxy.ProxyPointer;
import org.oopdev.xbalancer.service.proxy.ProxyPointerService;
import org.oopdev.xbalancer.web.endpoint.CrudBaseEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "proxyPointers")
public class ProxyPointerEndpoint extends CrudBaseEndpoint<ProxyPointer, String> {
    @Autowired
    public ProxyPointerEndpoint(ProxyPointerService proxyPointerService) {
        super(proxyPointerService);
    }
}
