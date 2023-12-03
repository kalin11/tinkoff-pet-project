package edu.tinkoff.tinkoffbackendacademypetproject.services;

import edu.tinkoff.tinkoffbackendacademypetproject.config.StorageProperties;
import edu.tinkoff.tinkoffbackendacademypetproject.exceptions.EntityModelNotFoundException;
import edu.tinkoff.tinkoffbackendacademypetproject.exceptions.StorageException;
import edu.tinkoff.tinkoffbackendacademypetproject.exceptions.StorageFileNotFoundException;
import edu.tinkoff.tinkoffbackendacademypetproject.model.ProfilePictureEntity;
import edu.tinkoff.tinkoffbackendacademypetproject.repositories.PhotoRepository;
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
public class PhotoService implements StorageService {
    private final StorageProperties storageProperties;
    private final PhotoRepository photoRepository;
    private Path rootLocation;

    @Override
    public ProfilePictureEntity store(MultipartFile photo) {
        try {
            if (photo.isEmpty()) {
                throw new StorageException("Failed to store empty photo.");
            }
            UUID uuid = UUID.randomUUID();
            Path destinationFile = this.rootLocation.resolve(
                            Paths.get(uuid + photo.getOriginalFilename()))
                    .normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                throw new StorageException(
                        "Cannot store photo outside current directory.");
            }
            try (InputStream inputStream = photo.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }

            return new ProfilePictureEntity(null, uuid + photo.getOriginalFilename(), photo.getOriginalFilename(), null);
        } catch (IOException e) {
            throw new StorageException("Failed to store photo", e);
        }
    }

    @Override
    public Path load(String photoName) {
        return rootLocation.resolve(photoName);
    }

    @Override
    public Resource loadAsResource(String photoName) {
        try {
            Path photo = load(photoName);
            Resource resource = new UrlResource(photo.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException(
                        "Фотография: " + photoName + " не найдена");
            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Невозможно прочитать фото: " + photoName, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(Paths.get(storageProperties.getLocationForPhotos()).toFile());
    }

    @Override
    public void init() {
        if (storageProperties.getLocationForPhotos().trim().isEmpty()) {
            throw new StorageException("Photo upload location can not be Empty.");
        }
        this.rootLocation = Paths.get(storageProperties.getLocationForPhotos());
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

    @Override
    public String getInitialFileName(String photoName) {
        ProfilePictureEntity picture = photoRepository.findByPhotoNameInDirectory(photoName).orElseThrow(
                () -> new EntityModelNotFoundException("Фото профиля", "именем", photoName)
        );
        return picture.getInitialPhotoName();
    }

    public void delete(Long id) {
        photoRepository.deleteById(id);
    }
}