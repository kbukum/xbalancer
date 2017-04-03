package org.oopdev.xbalancer.service.proxy;

import org.oopdev.xbalancer.domain.proxy.ProxyPointer;
import org.oopdev.xbalancer.persistence.repository.proxy.ProxyPointerRepository;
import org.oopdev.xbalancer.service.jpa.JpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kamilbukum on 01/04/2017.
 */
@Service
public class ProxyPointerService extends JpaService<ProxyPointer, String> {
    ProxyPointerRepository repository;

    @Autowired
    public ProxyPointerService(ProxyPointerRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public ProxyPointer findByPath(String path) {
        return repository.findByPath(path);
    }
}
