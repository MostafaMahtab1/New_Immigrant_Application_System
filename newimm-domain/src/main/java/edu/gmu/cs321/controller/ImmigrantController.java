// Contributed by Mostafa Mahtab
// helps with the mapping of the ImmigrantService system
package edu.gmu.cs321.controller;

import edu.gmu.cs321.model.ImmigrantEntity;
import edu.gmu.cs321.service.ImmigrantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ImmigrantController {

    private final ImmigrantService service;

    // Constructor Injection (Spring automatically injects the service)
    public ImmigrantController(ImmigrantService service) {
        this.service = service;
    }

    // CREATE (from Data Entry)
    @PostMapping("/immigrant")
    public ResponseEntity<ImmigrantEntity> create(@RequestBody ImmigrantEntity e) {
        return ResponseEntity.ok(service.saveOrUpdate(e));
    }

    // GET one (Reviewer, Approver use this)
    @GetMapping("/immigrant/{id}")
    public ResponseEntity<ImmigrantEntity> get(@PathVariable String id) {
        ImmigrantEntity e = service.findById(id);
        if (e == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(e);
    }

    // UPDATE whole object (Reviewer uses this)
@PutMapping("/immigrant/{id}")
public ResponseEntity<ImmigrantEntity> update(
        @PathVariable String id,
        @RequestBody ImmigrantEntity newData
) {
    ImmigrantEntity existing = service.findById(id);
    if (existing == null) {
        return ResponseEntity.notFound().build();
    }

    // Update fields
    existing.setFullName(newData.getFullName());
    existing.setDob(newData.getDob());
    existing.setAddress(newData.getAddress());
    existing.setEmail(newData.getEmail());
    existing.setPhone(newData.getPhone());

    // Update alien relatives cleanly
    existing.setAlienRelatives(newData.getAlienRelatives());

    // Only update status if it is provided
    if (newData.getStatus() != null) {
        existing.setStatus(newData.getStatus());
    }

    return ResponseEntity.ok(service.saveOrUpdate(existing));
}


// UPDATE ONLY STATUS (Approver uses this)
@PutMapping("/immigrant/{id}/status")
public ResponseEntity<List<ImmigrantEntity>> updateStatus(@PathVariable String id, @RequestParam String status) {
    service.updateStatus(id, status);
    return ResponseEntity.ok(service.getReviewerQueue());
}

    // REVIEWER QUEUE
    @GetMapping("/queue/reviewer")
    public ResponseEntity<List<ImmigrantEntity>> reviewerQueue() {
        return ResponseEntity.ok(service.getReviewerQueue());
    }

    // APPROVER QUEUE
    @GetMapping("/queue/approver")
    public ResponseEntity<List<ImmigrantEntity>> approverQueue() {
        return ResponseEntity.ok(service.getApproverQueue());
    }
}

