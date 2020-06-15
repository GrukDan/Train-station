package bsuir.service.taskDetails;

import bsuir.model.taskDetails.Comment;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CommentService {

    Comment save(Comment comment);

    void delete(long id);

    List<Comment> getPageByTask(int page, int size, boolean direction,long idTask);


}
