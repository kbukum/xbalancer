package org.oopdev.xbalancer.persistence.repository.proxy;

import org.oopdev.xbalancer.domain.proxy.ProxyPointer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kamilbukum on 01/04/2017.
 */
@Repository
public interface ProxyPointerRepository extends JpaRepository<ProxyPointer, String> {
    public ProxyPointer findByPath(String path);
}
