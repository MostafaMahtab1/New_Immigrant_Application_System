// Contributed by Shane Brickhead
// This file define all the private fields and the getter and setter methods for alien relatives,
package edu.gmu.cs321.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

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
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public ImmigrantEntity getImmigrant() { return immigrant; }
    public void setImmigrant(ImmigrantEntity immigrant) { this.immigrant = immigrant; }
}
