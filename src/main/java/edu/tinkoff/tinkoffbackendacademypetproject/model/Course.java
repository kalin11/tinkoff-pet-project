package edu.tinkoff.tinkoffbackendacademypetproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Курс
 */

@Entity
@Table(name = "course")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Course {

    /**
     * Номер курса
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseNumber;

    /**
     * Описание курса
     */
    @Column(name = "description")
    private String description;

    /**
     * Список топиков, которые имеют данный курс
     */
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<SubjectTopic> subjectTopics;
}
