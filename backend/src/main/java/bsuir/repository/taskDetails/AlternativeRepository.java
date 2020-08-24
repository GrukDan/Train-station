package bsuir.repository.taskDetails;

import bsuir.model.taskDetails.Alternative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlternativeRepository extends JpaRepository<Alternative,Long> {
}
