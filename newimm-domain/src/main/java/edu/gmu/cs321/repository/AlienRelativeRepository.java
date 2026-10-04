// Contributor - Mostafa Mahtab
// Interface for AlienRelative repository extending JpaRepository for CRUD (create, read, update, delete) operations.
// Implements list data structure to find AlienRelatives
package edu.gmu.cs321.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.gmu.cs321.model.AlienRelative;

public interface AlienRelativeRepository extends JpaRepository<AlienRelative, Long> {
    List<AlienRelative> findByImmigrantApplicantId(String immigrantId);
}
