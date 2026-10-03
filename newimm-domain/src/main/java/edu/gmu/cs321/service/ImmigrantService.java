// Contributed by Mostafa Mahtab
// This file helps with all the status update services throughout the workflow
package edu.gmu.cs321.service;

import edu.gmu.cs321.model.ImmigrantEntity;
import edu.gmu.cs321.repository.ImmigrantRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ImmigrantService {

    
    private ImmigrantRepository repo;

    @Autowired
    public ImmigrantService(ImmigrantRepository repo) {
        this.repo = repo;
    }

    public ImmigrantEntity saveOrUpdate(ImmigrantEntity e) {
        return repo.save(e);
    }

    public ImmigrantEntity findById(String id) {
        return repo.findById(id).orElse(null);
    }

    public void updateStatus(String id, String status) {
        ImmigrantEntity e = findById(id);
        if (e == null) return;
        e.setStatus(status);
        repo.save(e);
    }




        @Transactional
    public ImmigrantEntity openFormForReview(String applicantId) {
        ImmigrantEntity immigrant = repo.findById(applicantId)
                .orElseThrow(() -> new RuntimeException("Form not found: " + applicantId));
        if ("DATA_ENTRY_SUBMITTED".equals(immigrant.getStatus())) {
            immigrant.setStatus("IN_REVIEW");
            repo.save(immigrant);
        }
        return immigrant;
    }


    // DATA ENTRY → REVIEW → APPROVER
    public List<ImmigrantEntity> getReviewerQueue() {
        return repo.findByStatusIn(List.of(
                "DATA_ENTRY_SUBMITTED",
                "REJECTED"     // ← THIS FIXES REJECTION LOOP
        ));
    }

    public List<ImmigrantEntity> getApproverQueue() {
        return repo.findByStatus("REVIEWED");
    }
}
