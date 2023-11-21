package edu.tinkoff.tinkoffbackendacademypetproject.services;

import edu.tinkoff.tinkoffbackendacademypetproject.configuration.StorageProperties;
import edu.tinkoff.tinkoffbackendacademypetproject.exceptions.StorageException;
import edu.tinkoff.tinkoffbackendacademypetproject.exceptions.StorageFileNotFoundException;
import edu.tinkoff.tinkoffbackendacademypetproject.model.File;
import edu.tinkoff.tinkoffbackendacademypetproject.repositories.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService implements StorageService {
    private final StorageProperties storageProperties;
    private final FileRepository fileRepository;
    private Path rootLocation;

    @Override
    public File store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }
            UUID uuid = UUID.randomUUID();
            Path destinationFile = this.rootLocation.resolve(
                            Paths.get(uuid + file.getOriginalFilename()))
                    .normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file outside current directory.");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }

            return new File(null, uuid + file.getOriginalFilename(), file.getOriginalFilename(), null);
        } catch (IOException e) {
            throw new StorageException("Failed to store file.", e);
        }
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init() {
        if (storageProperties.getLocation().trim().isEmpty()) {
            throw new StorageException("File upload location can not be Empty.");
        }
        this.rootLocation = Paths.get(storageProperties.getLocation());
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

    @Override
    public String getInitialFileName(String filename) {
        return fileRepository.findByFileNameInDirectory(filename).getInitialFileName();
    }
}