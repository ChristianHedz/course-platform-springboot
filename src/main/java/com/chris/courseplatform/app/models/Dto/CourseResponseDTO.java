package com.chris.courseplatform.app.models.Dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class CourseResponseDTO {
    private Long id;
    private String title;
    private String description;
    private Category category;
    private BigDecimal price;
    private String image;
    private UserDTO instructor;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}