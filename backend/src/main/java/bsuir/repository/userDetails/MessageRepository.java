package bsuir.repository.userDetails;

import bsuir.model.userDetails.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {
    List<Message> findAllBySender(long sender);

    List<Message> findAllByRecipient(long recipient);

    List<Message> findAllBySenderAndRecipient(long sender, long recipient);

}
