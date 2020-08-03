package bsuir.controller.userDetails;


import bsuir.model.userDetails.Message;
import bsuir.service.userDetails.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/create", method = RequestMethod.PUT)
    public ResponseEntity<Message> save(@RequestBody Message message) {
        log.info("PUT request [{URL: " +"/api/messages/create},{body:" + message +"}];");
        Message savedMessage = messageService.save(message);
        return savedMessage != null ?
                new ResponseEntity<>(savedMessage,
                        null,
                        HttpStatus.CREATED)
                : new ResponseEntity<>(savedMessage,
                null,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/get-page", method = RequestMethod.GET)
    public ResponseEntity<List<Message>> getMessageSorted(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("parameter") String parameter,
            @RequestParam("direction") boolean direction,
            @RequestParam("sender") long sender,
            @RequestParam("recipient") long recipient) {
        log.info("GET request [{URL: " +"/api/users/get-page}];");
        return ResponseEntity.ok(
                messageService.getAllBySenderAndRecipientSorted(
                        page,
                        size,
                        parameter,
                        direction,
                        sender,
                        recipient));
    }
}
