// Contributed by Mostafa Mahtab
package edu.gmu.cs321.controller;

import edu.gmu.cs321.model.ImmigrantEntity;
import edu.gmu.cs321.service.ImmigrantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ReviewQueueController {

    private final ImmigrantService immigrantService;

    public ReviewQueueController(ImmigrantService immigrantService) {
        this.immigrantService = immigrantService;
    }

    // Show all forms pending review
@GetMapping("/review-queue")
public String reviewQueue(Model model) {
    List<ImmigrantEntity> pendingForms = immigrantService.getReviewerQueue();  // <-- use getReviewerQueue()
    model.addAttribute("immigrants", pendingForms);
    return "reviewQueue";  // Thymeleaf template: reviewQueue.html
}


    // Open a single form for review
    @GetMapping("/review-queue/{applicantId}")
    public String openForm(@PathVariable String applicantId, Model model) {
        ImmigrantEntity form = immigrantService.openFormForReview(applicantId);
        model.addAttribute("immigrant", form);
        return "reviewForm";  // Thymeleaf template: reviewForm.html
    }
}
