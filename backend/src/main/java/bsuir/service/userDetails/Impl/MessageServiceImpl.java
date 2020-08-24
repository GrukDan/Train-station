package bsuir.service.userDetails.Impl;

import bsuir.model.userDetails.Message;
import bsuir.repository.userDetails.MessageRepository;
import bsuir.service.userDetails.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public void delete(long id) {
        messageRepository.deleteById(id);
    }

    @Override
    public Message getById(long id) {
        return messageRepository.findById(id).orElse(null);
    }

    @Override
    public List<Message> getAllBySender(long sender) {
        return messageRepository.findAllBySender(sender);
    }

    @Override
    public List<Message> getAllByRecipient(long recipient) {
        return messageRepository.findAllByRecipient(recipient);
    }

    @Override
    public List<Message> getAllBySenderAndRecipient(long sender, long recipient) {
        return messageRepository.findAllBySenderAndRecipient(sender, recipient);
    }

    @Override
    public List<Message> getAllBySenderAndRecipientSorted(int page, int size, String parameter, boolean direction, long sender, long recipient) {
        return direction ? messageRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, parameter))).getContent()
                : messageRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, parameter))).getContent();
    }
}
