package edu.tinkoff.tinkoffbackendacademypetproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Пост
 */
@Entity
@Table(name = "publication")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PublicationEntity {
    /**
     * Идентификатор поста
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Описание поста
     */
    @Column(name = "description")
    private String description;

    @Column(name = "title")
    private String title;

    /**
     * Дата создания поста
     */
    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    /**
     * К какому топику относится пост
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_topic_id", referencedColumnName = "id")
    private SubjectTopicEntity subjectTopic;

    /**
     * Вложенные файлы в пост
     */
    @OneToMany(mappedBy = "publication", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<FileEntity> files;

    /**
     * Комментарии к посту
     */
    @OneToMany(mappedBy = "publication", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CommentEntity> comments;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;
}
