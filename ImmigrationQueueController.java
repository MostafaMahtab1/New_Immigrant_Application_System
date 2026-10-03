// Contributed by Mostafa Mahtab
// This file helps with the data entry to reviewer queue
package edu.gmu.cs321.controller;

import edu.gmu.cs321.model.ImmigrantEntity;
import edu.gmu.cs321.repository.ImmigrantRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/immigration")
@CrossOrigin(origins = "*")
public class ImmigrationQueueController {

    private final ImmigrantRepository repository;

    public ImmigrationQueueController(ImmigrantRepository repository) {
        this.repository = repository;
    }

    // Return data-entry-submitted forms for reviewer listing
    @GetMapping("/submitted")
    public List<ImmigrantEntity> getSubmittedApplications() {
        return repository.findByStatus("DATA_ENTRY_SUBMITTED");
    }

    // Return single application (used by reviewer UI)
    @GetMapping("/{id}")
    public ImmigrantEntity getApplication(@PathVariable String id) {
        return repository.findById(id).orElse(null);
    }
}
