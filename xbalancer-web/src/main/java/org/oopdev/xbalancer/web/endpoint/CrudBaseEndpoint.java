package org.oopdev.xbalancer.web.endpoint;

import org.oopdev.xbalancer.service.jpa.JpaService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kamilbukum on 02/04/2017.
 */

public abstract class CrudBaseEndpoint<E, I extends Serializable> {
    private JpaService<E, I> service;

    public CrudBaseEndpoint(JpaService<E, I> service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET, value = {"{oid}"})
    public E get(@PathVariable("oid") I id) {
        return service.findOne(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<E> getAll() {
        return service.findAll();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public E create(@RequestBody E proxyAddress) {
        return service.create(proxyAddress);
    }

    @RequestMapping(method = RequestMethod.POST)
    public E update(@RequestBody E proxyAddress) {
        return service.update(proxyAddress);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{oid}")
    public E delete(@PathVariable("oid") I id) {
        return service.delete(id);
    }
}
