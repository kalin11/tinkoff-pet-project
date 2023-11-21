package edu.tinkoff.tinkoffbackendacademypetproject.services;

import edu.tinkoff.tinkoffbackendacademypetproject.exceptions.EntityModelNotFoundException;
import edu.tinkoff.tinkoffbackendacademypetproject.model.File;
import edu.tinkoff.tinkoffbackendacademypetproject.model.Publication;
import edu.tinkoff.tinkoffbackendacademypetproject.repositories.PublicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Publication createPublication(Publication publication, List<MultipartFile> files) throws EntityModelNotFoundException {
        var filesInPublication = new ArrayList<File>();
        for (var file : files) {
            var fileInPublication = fileService.store(file);
            fileInPublication.setPublication(publication);
            filesInPublication.add(fileInPublication);
        }
        publication.setSubjectTopic(subjectTopicService.getSubjectTopic(publication.getSubjectTopic().getId()));
        publication.setFiles(filesInPublication);
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
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return publicationRepository.findBySubjectTopic_Id(subjectTopicId, pageable);
    }

}