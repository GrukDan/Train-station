package bsuir.service.taskDetails.Impl;

import bsuir.model.taskDetails.Comment;
import bsuir.repository.taskDetails.CommentRepository;
import bsuir.service.taskDetails.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void delete(long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public List<Comment> getPageByTask(int page, int size, boolean direction, long idTask) {
        return direction ?
                commentRepository
                        .findAllByPlace(idTask, PageRequest.of(page, size, Sort.by(Sort.Direction.DESC))).getContent()
                : commentRepository
                .findAllByPlace(idTask, PageRequest.of(page, size, Sort.by(Sort.Direction.ASC))).getContent();
    }
}
