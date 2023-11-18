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
public class Publication {
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

    /**
     * Дата создания поста
     */
    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    /**
     * К какому топику относится пост
     */
    @ManyToOne
    @JoinColumn(name = "subject_topic_id", referencedColumnName = "id")
    private SubjectTopic topic;

    /**
     * Вложенные файлы в пост
     */
    @OneToMany(mappedBy = "publication", fetch = FetchType.LAZY)
    private List<File> files;

    /**
     * Комментарии к посту
     */
    @OneToMany(mappedBy = "publication", fetch = FetchType.LAZY)
    private List<Comment> comments;
}
