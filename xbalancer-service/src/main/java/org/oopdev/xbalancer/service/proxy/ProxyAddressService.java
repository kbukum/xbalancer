package org.oopdev.xbalancer.service.proxy;

import org.oopdev.xbalancer.domain.proxy.ProxyAddress;
import org.oopdev.xbalancer.persistence.repository.proxy.ProxyAddressRepository;
import org.oopdev.xbalancer.service.jpa.JpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kamilbukum on 01/04/2017.
 */
@Service
public class ProxyAddressService extends JpaService<ProxyAddress, String> {
    ProxyAddressRepository proxyAddressRepository;

    @Autowired
    public ProxyAddressService(ProxyAddressRepository repository) {
        super(repository);
        this.proxyAddressRepository = repository;
    }

    public ProxyAddress findByName(String relativePath) {
        return proxyAddressRepository.findByName(relativePath);
    }
}
