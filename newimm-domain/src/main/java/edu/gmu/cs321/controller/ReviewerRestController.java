// Contributed by Mostafa Mahtab
//Helps reviewer to get all/single submitted applications from the data-entry.
package edu.gmu.cs321.controller;

import edu.gmu.cs321.model.ImmigrantEntity;
import edu.gmu.cs321.service.ImmigrantService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@RestController
@RequestMapping("/api/reviewer")
public class ReviewerRestController {

    private final ImmigrantService immigrantService;

    public ReviewerRestController(ImmigrantService immigrantService) {
        this.immigrantService = immigrantService;
    }

    // GET all submitted applications
 // GET all submitted applications
    @GetMapping("/applications")
    public List<ImmigrantEntity> getSubmittedApplications() {
    return immigrantService.getReviewerQueue();  // <-- use getReviewerQueue()
}


    // GET single application
    @GetMapping("/applications/{applicantId}")
    public ImmigrantEntity getApplication(@PathVariable String applicantId) {
        return immigrantService.findById(applicantId);
    }
}
