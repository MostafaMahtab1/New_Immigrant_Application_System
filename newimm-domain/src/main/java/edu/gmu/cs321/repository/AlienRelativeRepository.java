// Contributor - Mostafa Mahtab
// Interface for AlienRelative repository extending JpaRepository for CRUD (create, read, update, delete) operations.
// Implements list data structure to find AlienRelatives
package edu.gmu.cs321.repository;

import edu.gmu.cs321.model.AlienRelative;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AlienRelativeRepository extends JpaRepository<AlienRelative, Long> {
    List<AlienRelative> findByImmigrant_ApplicantId(String immigrantId);
}
