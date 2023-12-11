package edu.tinkoff.tinkoffbackendacademypetproject.services;

import edu.tinkoff.tinkoffbackendacademypetproject.model.CommentAudEntity;
import edu.tinkoff.tinkoffbackendacademypetproject.repositories.CommentAudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentAudService {
    private final CommentAudRepository commentAudRepository;

    public Page<CommentAudEntity> getCommentHistory(Integer pageNumber, Integer pageSize, Long commentId) {
        return commentAudRepository.findByComment_Id(commentId, PageRequest.of(pageNumber, pageSize, Sort.by("id").reverse()));
    }
}
