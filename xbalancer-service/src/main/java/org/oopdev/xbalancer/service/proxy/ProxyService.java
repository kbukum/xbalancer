package org.oopdev.xbalancer.service.proxy;

import org.oopdev.xbalancer.domain.proxy.ProxyAddress;
import org.oopdev.xbalancer.domain.proxy.ProxyPointer;
import org.oopdev.xbalancer.proxy.host.Host;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by kamilbukum on 03/04/2017.
 */
@Service
public class ProxyService {
    @Autowired
    ProxyPointerService proxyPointerService;
    @Autowired
    ProxyAddressService proxyAddressService;

    public Map<String, Host> filter(String relativePath) {

        ProxyPointer pointer = proxyPointerService.findByPath(relativePath);

        Map<String, Host> filteredMap = new LinkedHashMap<>();
        if (pointer != null) {
            for (ProxyAddress address : pointer.getProxyAddressList()) {
                filteredMap.put(address.getName(), new Host(address.getName(), address.getHost(), address.getPort()));
            }
        }

        if (filteredMap.size() == 0) {
            ProxyAddress address = proxyAddressService.findByName(relativePath);
            if (address != null) {
                filteredMap.put(address.getName(), new Host(address.getName(), address.getHost(), address.getPort()));
            }
        }
        return filteredMap;
    }

}
