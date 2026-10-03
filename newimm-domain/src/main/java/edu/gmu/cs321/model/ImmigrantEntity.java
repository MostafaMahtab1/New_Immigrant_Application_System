// Contributed by Mostafa Mahtab
// Defines the private fields and getter and setters for the primary immigrant applicant.
package edu.gmu.cs321.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "immigrant_entity")
public class ImmigrantEntity
{

    @Id
    @Column(name = "applicant_id", nullable = false, unique = true)
    private String applicantId; // Primary key

    private String fullName;
    private String dob;
    private String address;
    private String phone;
    private String email;

    private String status = "DRAFT"; // Default status

    @OneToMany(mappedBy = "immigrant", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<AlienRelative> alienRelatives = new ArrayList<>();

    // --- Getters and Setters ---
    public String getApplicantId() { return applicantId; }
    public void setApplicantId(String applicantId) { this.applicantId = applicantId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public List<AlienRelative> getAlienRelatives() { return alienRelatives; }
    public void setAlienRelatives(List<AlienRelative> alienRelatives) {
        this.alienRelatives.clear();
        if (alienRelatives != null)
        {
            for (AlienRelative ar : alienRelatives)
            {
                ar.setImmigrant(this);
                this.alienRelatives.add(ar);
            }
        }
    }
}
