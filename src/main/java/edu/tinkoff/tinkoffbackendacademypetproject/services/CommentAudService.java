package edu.tinkoff.tinkoffbackendacademypetproject.services;


import edu.tinkoff.tinkoffbackendacademypetproject.model.CommentAudit;
import edu.tinkoff.tinkoffbackendacademypetproject.model.CommentEntity;
import edu.tinkoff.tinkoffbackendacademypetproject.repositories.CommentAudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentAudService {
    private final CommentAudRepository commentAudRepository;

    public List<CommentAudit> getAll(Long commentId, Long revisionNumber) {
        return commentAudRepository.getAll(commentId, revisionNumber);
    }
}
