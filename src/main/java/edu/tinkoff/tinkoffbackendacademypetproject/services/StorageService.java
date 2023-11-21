package edu.tinkoff.tinkoffbackendacademypetproject.services;

import edu.tinkoff.tinkoffbackendacademypetproject.model.File;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface StorageService {

    void init();

    File store(MultipartFile file);

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();

    String getInitialFileName(String filename);

}