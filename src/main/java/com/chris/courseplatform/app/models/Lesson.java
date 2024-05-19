package com.chris.courseplatform.app.models;

import jakarta.persistence.*;

@Entity
@Table(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String video;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
