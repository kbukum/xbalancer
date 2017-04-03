package org.oopdev.xbalancer.web.endpoint.proxy;

import org.oopdev.xbalancer.domain.proxy.ProxyAddress;
import org.oopdev.xbalancer.service.proxy.ProxyAddressService;
import org.oopdev.xbalancer.web.endpoint.CrudBaseEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "proxyAddresses")
public class ProxyAddressEndpoint extends CrudBaseEndpoint<ProxyAddress, String> {
    @Autowired
    public ProxyAddressEndpoint(ProxyAddressService proxyAddressService) {
        super(proxyAddressService);
    }
}
