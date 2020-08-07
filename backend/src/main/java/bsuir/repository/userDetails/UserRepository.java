package bsuir.repository.userDetails;

import bsuir.model.userDetails.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    
    Page<User> findAllByNameOrSurnameOrEmail(String search, PageRequest pageRequest);

    User findByEmailAndPassword(String email, String password);

    @Query(value = "SELECT * FROM user u " +
            "JOIN user_roles u_r " +
            "ON u.id_user = u_r.user_id " +
            "JOIN role r " +
            "ON r.id_role = u_r.role_id " +
            "WHERE r.role = :roleName",
    nativeQuery = true)
    List<User> findAllByRoleName(@Param("roleName") String roleName);

}
