package com.abhinavgarg.microservice1;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.badRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "visitors")
public class VistorController {
	
	private final VisitorRepository repo;
	
	@Autowired
	public VistorController(final VisitorRepository repo) {
		this.repo = repo;
	}
	
	@RequestMapping(path = "", method = RequestMethod.POST)
	public ResponseEntity<?> writeDownVisitor(final @RequestBody Map<String, Object> input) {
		
		if (isInputValid(input)) {
			saveVisitor(input);
			return ok(flattenVisitorList());
		} 
		else 
			return badRequest().body("'name' is required.");
		
	}
	
	private boolean isInputValid(final Map<String, Object> input) {
        return input.containsKey("name") && input.get("name") != null;
    }
	
	private Iterable<String> flattenVisitorList() {
        final List<String> visitorList = new ArrayList<>();
        final Iterable<VisitorEntity> visitors = repo.findAll();
        visitors.forEach(v -> visitorList.add(v.getName()));
        return visitorList;
    }
	
	private void saveVisitor(final Map<String, Object> input) {
        final VisitorEntity visitor = new VisitorEntity();
        visitor.setId(UUID.randomUUID().toString());
        visitor.setName(input.get("name").toString());
        repo.save(visitor);
    }
	
}
