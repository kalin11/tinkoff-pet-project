package edu.tinkoff.tinkoffbackendacademypetproject.services;

import edu.tinkoff.tinkoffbackendacademypetproject.exceptions.EntityModelNotFoundException;
import edu.tinkoff.tinkoffbackendacademypetproject.model.Account;
import edu.tinkoff.tinkoffbackendacademypetproject.model.File;
import edu.tinkoff.tinkoffbackendacademypetproject.model.Publication;
import edu.tinkoff.tinkoffbackendacademypetproject.repositories.PublicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PublicationService {
    private final PublicationRepository publicationRepository;
    private final SubjectTopicService subjectTopicService;
    private final FileService fileService;

    @Transactional
    public Publication createPublication(Publication publication, Account account, List<MultipartFile> files) throws EntityModelNotFoundException {
        if (files != null) {
            var filesInPublication = new ArrayList<File>();
            for (var file : files) {
                var fileInPublication = fileService.store(file);
                fileInPublication.setPublication(publication);
                filesInPublication.add(fileInPublication);
            }
            publication.setFiles(filesInPublication);
        } else {
            publication.setFiles(new ArrayList<>());
        }
        publication.setSubjectTopic(subjectTopicService.getSubjectTopic(publication.getSubjectTopic().getId()));
        publication.setAccount(account);
        return publicationRepository.save(publication);
    }

    public Publication getPublication(Long id) throws EntityModelNotFoundException {
        return publicationRepository.findById(id).orElseThrow(() -> new EntityModelNotFoundException("Публикации", "id", id));
    }

    @Transactional
    public void deletePublication(Long id) throws EntityModelNotFoundException {
        publicationRepository.delete(getPublication(id));
    }

    public Page<Publication> getPublicationsInOneCategory(Integer pageNumber, Integer pageSize, Long subjectTopicId) {
        return publicationRepository.findBySubjectTopic_Id(subjectTopicId, PageRequest.of(pageNumber, pageSize, Sort.by("id")));
    }

}