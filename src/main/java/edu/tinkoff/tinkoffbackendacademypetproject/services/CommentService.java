package edu.tinkoff.tinkoffbackendacademypetproject.services;

import edu.tinkoff.tinkoffbackendacademypetproject.exceptions.DepthThreadException;
import edu.tinkoff.tinkoffbackendacademypetproject.exceptions.EntityModelNotFoundException;
import edu.tinkoff.tinkoffbackendacademypetproject.exceptions.NotThreadException;
import edu.tinkoff.tinkoffbackendacademypetproject.model.Account;
import edu.tinkoff.tinkoffbackendacademypetproject.model.CommentEntity;
import edu.tinkoff.tinkoffbackendacademypetproject.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PublicationService publicationService;

    @Transactional
    public CommentEntity createComment(CommentEntity comment, Account account) throws EntityModelNotFoundException {
        comment.setPublication(publicationService.getPublication(comment.getPublication().getId()));
        comment.setAccount(account);
        return commentRepository.save(comment);
    }

    public CommentEntity getComment(Long id) throws EntityModelNotFoundException {
        return commentRepository.findById(id).orElseThrow(() -> new EntityModelNotFoundException("Комментария", "id", Long.toString(id)));
    }

    @Transactional
    public CommentEntity updateComment(CommentEntity comment) throws EntityModelNotFoundException {
        var changeComment = getComment(comment.getId());
        changeComment.setContent(comment.getContent());
        return commentRepository.save(changeComment);
    }

    @Transactional
    public void deleteComment(Long id) throws EntityModelNotFoundException {
        commentRepository.delete(getComment(id));
    }

    public Page<CommentEntity> getCommentsOnThePublication(Integer pageNumber, Integer pageSize, Long publicationId) {
        return commentRepository.findByParentAndPublication_Id(null, publicationId, PageRequest.of(pageNumber, pageSize));
    }

    @Transactional
    public void deleteOldComment() {
        var allComments = commentRepository.findAll();
        for (var comment : allComments) {
            if (ChronoUnit.YEARS.between(comment.getCreatedAt(), LocalDateTime.now()) > 2) {
                commentRepository.delete(comment);
            }
        }
    }

    @Transactional
    public CommentEntity createCommentThread(CommentEntity comment, Account account) throws EntityModelNotFoundException {
        var parentComment = getComment(comment.getParent().getId());
        var publication = publicationService.getPublication(parentComment.getPublication().getId());
        if (publication.getIsThread()) {
            comment.setPublication(publication);
            comment.setAccount(account);
            if (parentComment.getParent() == null){
                comment.setParent(parentComment);
                return commentRepository.save(comment);
            }
            else {
                throw new DepthThreadException();
            }
        } else {
            throw new NotThreadException();
        }
    }

    public Page<CommentEntity> getThreadsOnTheComment(Integer pageNumber, Integer pageSize, Long commentId) {
        return commentRepository.findByParent_Id(commentId, PageRequest.of(pageNumber, pageSize, Sort.by("id")));
    }
}