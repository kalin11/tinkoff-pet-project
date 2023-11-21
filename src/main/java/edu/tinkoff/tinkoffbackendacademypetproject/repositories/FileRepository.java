package edu.tinkoff.tinkoffbackendacademypetproject.repositories;

import edu.tinkoff.tinkoffbackendacademypetproject.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
    File findByFileNameInDirectory(String filename);
}
