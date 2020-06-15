package bsuir.service.userDetails;

import bsuir.model.userDetails.Message;

import java.util.List;

public interface MessageService {

    Message save(Message message);

    void delete(long id);

    Message getById(long id);

    List<Message> getAllBySender(long sender);

    List<Message> getAllByRecipient(long recipient);

    List<Message> getAllBySenderAndRecipient(long sender, long recipient);

    List<Message> getAllBySenderAndRecipientSorted(int page, int size, String parameter, boolean direction, long sender, long recipient);

}
