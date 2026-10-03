// Contributed by Mostafa Mahtab
// It defines interface ImmigrantRepository and force implementation of List data structure
package edu.gmu.cs321.repository;

import edu.gmu.cs321.model.ImmigrantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ImmigrantRepository extends JpaRepository<ImmigrantEntity, String> {
    List<ImmigrantEntity> findByStatus(String status);
    List<ImmigrantEntity> findByStatusIn(List<String> statuses);
}
