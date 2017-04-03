package org.oopdev.xbalancer.service.proxy;

import org.oopdev.xbalancer.common.exception.ErrorCode;
import org.oopdev.xbalancer.common.exception.XbCommonException;
import org.oopdev.xbalancer.domain.proxy.ProxyServerAddress;
import org.oopdev.xbalancer.persistence.repository.proxy.ProxyServerRepository;
import org.oopdev.xbalancer.proxy.ProxyServer;
import org.oopdev.xbalancer.service.jpa.JpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kamilbukum on 01/04/2017.
 */
@Service
public class ProxyServerService extends JpaService<ProxyServerAddress, String> {

    final ProxyServerRepository repository;

    @Autowired
    public ProxyServerService(ProxyServerRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public ProxyServerAddress update(ProxyServer proxyServer, ProxyServerAddress proxyServerAddress) {
        try {
            proxyServerAddress = update(proxyServerAddress);
            return proxyServerAddress;
        } catch (Exception e) {
            proxyServer.stop();
            throw new XbCommonException(ErrorCode.HTTP_507, "Couldn't update data !");
        }
    }
}
