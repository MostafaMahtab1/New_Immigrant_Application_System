// Contributed by Mostafa Mahtab
// Defines the private fields and getter and setters for the primary immigrant applicant.

package edu.gmu.cs321.model;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity class representing a Primary Immigrant Applicant.
 */
@Entity
@Table(name = "immigrant_entity")
public class ImmigrantEntity {

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

    /** Gets the applicant ID. */
    public String getApplicantId() {
        return applicantId;
    }

    /** Sets the applicant ID. */
    public void setApplicantId(final String applicantId) {
        this.applicantId = applicantId;
    }

    /** Gets the full name. */
    public String getFullName() {
        return fullName;
    }

    /** Sets the full name. */
    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }

    /** Gets the date of birth. */
    public String getDob() {
        return dob;
    }

    /** Sets the date of birth. */
    public void setDob(final String dob) {
        this.dob = dob;
    }

    /** Gets the address. */
    public String getAddress() {
        return address;
    }

    /** Sets the address. */
    public void setAddress(final String address) {
        this.address = address;
    }

    /** Gets the phone number. */
    public String getPhone() {
        return phone;
    }

    /** Sets the phone number. */
    public void setPhone(final String phone) {
        this.phone = phone;
    }

    /** Gets the email address. */
    public String getEmail() {
        return email;
    }

    /** Sets the email address. */
    public void setEmail(final String email) {
        this.email = email;
    }

    /** Gets the pipeline workflow status. */
    public String getStatus() {
        return status;
    }

    /** Sets the pipeline workflow status. */
    public void setStatus(final String status) {
        this.status = status;
    }

    /** Gets the list of associated alien relatives. */
    public List<AlienRelative> getAlienRelatives() {
        return alienRelatives;
    }

    /** Sets the list of associated alien relatives and syncs references. */
    public void setAlienRelatives(final List<AlienRelative> alienRelatives) {
        this.alienRelatives.clear();
        if (alienRelatives != null) {
            for (AlienRelative ar : alienRelatives) {
                ar.setImmigrant(this);
                this.alienRelatives.add(ar);
            }
        }
    }
}