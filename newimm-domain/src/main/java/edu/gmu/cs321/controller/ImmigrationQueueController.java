// Contributed by Mostafa Mahtab
// This file helps with the data entry to reviewer queue
package edu.gmu.cs321.controller;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import edu.gmu.cs321.model.ImmigrantEntity;
import edu.gmu.cs321.repository.ImmigrantRepository;

@RestController
@RequestMapping("/immigration")
@CrossOrigin(origins = """
        http://localhost/immigrant-data_entry.html
        http://localhost/dataEntry-review_queue.html
        http://localhost/approver-queue.html
        http://localhost/immigrant-approver.html""")
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
