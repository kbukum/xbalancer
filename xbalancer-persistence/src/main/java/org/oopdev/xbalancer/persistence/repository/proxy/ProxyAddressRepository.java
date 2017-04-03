package org.oopdev.xbalancer.persistence.repository.proxy;

import org.oopdev.xbalancer.domain.proxy.ProxyAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kamilbukum on 01/04/2017.
 */
@Repository
public interface ProxyAddressRepository extends JpaRepository<ProxyAddress, String> {
    ProxyAddress findByName(String relativePath);
}
