package com.chris.courseplatform.app.models;

import com.chris.courseplatform.app.models.Dto.Category;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private Category category;
    private BigDecimal price;
    private String image;
    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private User instructor;
    private String duration;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Lesson> lessons;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Review> reviews;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Question> questions;

    @ManyToMany(mappedBy = "enrolledCourses")
    private Set<User> estudiantes;
}
