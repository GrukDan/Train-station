package bsuir.repository.userDetails;

import bsuir.model.userDetails.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmailAndPassword(String email, String password);

    Optional<User> findByEmail(String email);

    @Query(value = "SELECT * FROM user u " +
            "JOIN user_tasks u_t " +
            "ON u.id_user = u_t.user_id " +
            "JOIN task t " +
            "ON t.id_task = u_t.task_id " +
            "WHERE t.id_task = :idTask",
            nativeQuery = true)
    List<User> findAllByTaskId(@Param("idTask") Long idTask);

    @Query(value = "SELECT * FROM user u " +
            "JOIN user_roles u_r " +
            "ON u.id_user = u_r.user_id " +
            "JOIN role r " +
            "ON r.id_role = u_r.role_id " +
            "WHERE r.role = :roleName",
    nativeQuery = true)
    List<User> findAllByRoleName(@Param("roleName") String roleName);

}
