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
public class SubjectTopic {
    /**
     * Идентификатор топика
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Тип топика
     */
    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private TopicType type;

    /**
     * Предмет, к которому относится топик
     */
    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private Subject subject;

    /**
     * Курс, к которому относится топик
     */
    @ManyToOne
    @JoinColumn(name = "course_number", referencedColumnName = "courseNumber")
    private Course course;

    /**
     * Посты, которые относятся к топику
     */
    @OneToMany(mappedBy = "subjectTopic", fetch = FetchType.LAZY)
    private List<Publication> publications;
}
