package edu.tinkoff.tinkoffbackendacademypetproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "profile_picture")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProfilePhotoEntity {
    /**
     * Идентификатор фото
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Ссылка на фото
     */
    @Column(name = "file_name_in_directory")
    private String fileNameInDirectory;

    /**
     * Расширение вложения (файла)
     */
    @Column(name = "initial_file_name")
    private String initialFileName;

    @Column(name = "set_date")
    private LocalDateTime dateTime;

    /**
     * К какому посту было приложено
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;
}
