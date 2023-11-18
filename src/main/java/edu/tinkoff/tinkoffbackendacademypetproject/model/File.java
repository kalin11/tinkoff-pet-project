package edu.tinkoff.tinkoffbackendacademypetproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Вложение (файл)
 */
@Entity
@Table(name = "file")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class File {
    /**
     * Идентификатор вложения (файла)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Ссылка на вложение (файл)
     */
    @Column(name = "file_url")
    private String fileUrl;

    /**
     * Расширение вложения (файла)
     */
    @Column(name = "extension")
    private String extension;

    /**
     * К какому посту было приложено
     */
    @ManyToOne
    @JoinColumn(name = "publication_id", referencedColumnName = "id")
    private Publication publication;
}
