package edu.tinkoff.tinkoffbackendacademypetproject.services;

import edu.tinkoff.tinkoffbackendacademypetproject.exceptions.EntityModelNotFoundException;
import edu.tinkoff.tinkoffbackendacademypetproject.model.Account;
import edu.tinkoff.tinkoffbackendacademypetproject.model.Comment;
import edu.tinkoff.tinkoffbackendacademypetproject.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PublicationService publicationService;

    @Transactional
    public Comment createComment(Comment comment, Account account) throws EntityModelNotFoundException {
        comment.setPublication(publicationService.getPublication(comment.getPublication().getId()));
        comment.setAccount(account);
        return commentRepository.save(comment);
    }

    public Comment getComment(Long id) throws EntityModelNotFoundException {
        return commentRepository.findById(id).orElseThrow(() -> new EntityModelNotFoundException("Комментария", "id", id));
    }

    @Transactional
    public Comment updateComment(Comment comment) throws EntityModelNotFoundException {
        var changeComment = getComment(comment.getId());
        changeComment.setContent(comment.getContent());
        return commentRepository.save(changeComment);
    }

    @Transactional
    public void deleteComment(Long id) throws EntityModelNotFoundException {
        commentRepository.delete(getComment(id));
    }

    public Page<Comment> getCommentsOnThePublication(Integer pageNumber, Integer pageSize, Long publicationId) {
        return commentRepository.findByPublication_Id(publicationId, PageRequest.of(pageNumber, pageSize));
    }

}