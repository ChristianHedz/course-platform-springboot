package com.chris.courseplatform.app.models.Dto;

import com.chris.courseplatform.app.models.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CourseRequest {
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private Category category;
    private BigDecimal price;
    private String image;
    private User instructor;
}
