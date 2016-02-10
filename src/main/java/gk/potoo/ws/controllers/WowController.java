package gk.potoo.ws.controllers;

import gk.potoo.documents.Wow;
import gk.potoo.ws.services.WowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/wows")
public class WowController {

    @Autowired
    private WowService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<Wow> getAll() {
        return service.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Wow create(@RequestBody Wow data) {
        return service.create(data);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Wow update(@PathVariable String id, @RequestBody Wow data) {
        return service.update(id, data);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Wow get(@PathVariable String id) {
        return service.find(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

}
