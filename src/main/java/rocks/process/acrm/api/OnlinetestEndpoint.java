/*
 * Copyright (c) 2019. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package rocks.process.acrm.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import rocks.process.acrm.business.service.OnlinetestService;
import rocks.process.acrm.data.domain.Onlinetest;

import javax.validation.ConstraintViolationException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class OnlinetestEndpoint {
    @Autowired
    private OnlinetestService onlinetestService;

	//TODO uncomment
    @PostMapping(path = "/onlinetest", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Onlinetest> postOnlinetest(@RequestBody Onlinetest onlinetest) {
        try {
			// TODO create online test
            onlinetest = onlinetestService.editOnlinetest(onlinetest);
        } catch (ConstraintViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getConstraintViolations().iterator().next().getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{onlinetestId}")
                .buildAndExpand(onlinetest.getId()).toUri();

        return ResponseEntity.created(location).body(onlinetest);
    }

	//TODO path = "/onlinetest" // get and read online test data list
    @GetMapping(path = "/onlinetest", produces = "application/json")
    public List<Onlinetest> getOnlinetest() {
		//TODO get all online tests
        return onlinetestService.findAllOnlinetests();
    }

	//TODO uncomment
    @GetMapping(path = "/onlinetest/{onlinetestId}", produces = "application/json")
    //reading customer data
    public ResponseEntity<Onlinetest> getOnlinetest(@PathVariable(value = "onlinetestId") String onlinetestId) throws Exception {
        Onlinetest onlinetest = null;
        try {
			//TODO find an online test by id
            onlinetest = onlinetestService.findOnlinetestById(Long.parseLong(onlinetestId));
        } catch (Exception e) {
			//TODO HTTP Status: not found
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());

        }
        return ResponseEntity.ok(onlinetest);
    }

	//TODO
    @PutMapping(path = "/onlinetest/{onlinetestId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Onlinetest> putOnlinetest(@RequestBody Onlinetest onlinetest, @PathVariable(value = "onlinetestId") String onlinetestId) {
        try {
            onlinetest.setId(Long.parseLong(onlinetestId));
			//TODO edit online test
            onlinetest = onlinetestService.editOnlinetest(onlinetest);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        }
        return ResponseEntity.accepted().body(onlinetest);
    }

	//TODO delete mapping
    @DeleteMapping(path = "/onlinetest/{onlinetestId)")
    public ResponseEntity<Void> deleteOnlinetest(@PathVariable(value = "onlinetestId") String onlinetestId) {
        try {
			//TODO delete online test
            onlinetestService.deleteOnlinetest(Long.parseLong(onlinetestId));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        }
        return ResponseEntity.accepted().build();
    }
}
