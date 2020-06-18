package bsuir.repository.userDetails;

import bsuir.model.userDetails.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    
    Page<User> findAllByNameOrSurnameOrEmail(String search, PageRequest pageRequest);

    User findByEmailAndPassword(String email, String password);
}
