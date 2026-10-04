// Contributed by Shane Birckhead
// Cleanup to meet the ci/cd pipeline requirements with javadoc, explicit import by Mostafa Mahtab.
// This file define all the private fields and the getter and setter methods for alien relatives,
package edu.gmu.cs321.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Entity class representing an Alien Relative linked to an immigrant application.
 */
@Entity
@Table(name = "alien_relative")
public class AlienRelative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Primary key

    private String fullName;
    private String dob;
    private String address;
    private String email;
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "immigrant_id")
    @JsonBackReference
    private ImmigrantEntity immigrant;

    // --- Getters & Setters ---

    /** Gets the relative record primary key ID. */
    public Long getId() {
        return id;
    }

    /** Sets the relative record primary key ID. */
    public void setId(final Long id) {
        this.id = id;
    }

    /** Gets the relative's full name. */
    public String getFullName() {
        return fullName;
    }

    /** Sets the relative's full name. */
    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }

    /** Gets the relative's date of birth. */
    public String getDob() {
        return dob;
    }

    /** Sets the relative's date of birth. */
    public void setDob(final String dob) {
        this.dob = dob;
    }

    /** Gets the relative's residential address. */
    public String getAddress() {
        return address;
    }

    /** Sets the relative's residential address. */
    public void setAddress(final String address) {
        this.address = address;
    }

    /** Gets the relative's email address. */
    public String getEmail() {
        return email;
    }

    /** Sets the relative's email address. */
    public void setEmail(final String email) {
        this.email = email;
    }

    /** Gets the relative's phone number. */
    public String getPhone() {
        return phone;
    }

    /** Sets the relative's phone number. */
    public void setPhone(final String phone) {
        this.phone = phone;
    }

    /** Gets the associated primary immigrant applicant. */
    public ImmigrantEntity getImmigrant() {
        return immigrant;
    }

    /** Sets the associated primary immigrant applicant reference. */
    public void setImmigrant(final ImmigrantEntity immigrant) {
        this.immigrant = immigrant;
    }
}
