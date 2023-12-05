package edu.tinkoff.tinkoffbackendacademypetproject.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Топик предмета
 */
@Entity
@Table(name = "subject_topic")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubjectTopicEntity {
    /**
     * Идентификатор топика
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Тип топика
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private TopicTypeEntity type;

    /**
     * Предмет, к которому относится топик
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private SubjectEntity subject;

    /**
     * Посты, которые относятся к топику
     */
    @OneToMany(mappedBy = "subjectTopic", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
    private List<PublicationEntity> publications;
}
