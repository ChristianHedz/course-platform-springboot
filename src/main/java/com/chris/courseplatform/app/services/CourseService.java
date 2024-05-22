package com.chris.courseplatform.app.services;

import com.chris.courseplatform.app.models.Dto.CourseRequest;
import com.chris.courseplatform.app.models.Dto.CourseResponseDTO;

public interface CourseService {
    CourseResponseDTO createCourse(CourseRequest courseRequest);

    CourseResponseDTO updateCourse(Long id, CourseRequest courseRequest);

    void deleteCourse(Long id);
}
