package com.chris.courseplatform.app.mappers;

import com.chris.courseplatform.app.models.Course;
import com.chris.courseplatform.app.models.Dto.CourseRequest;
import com.chris.courseplatform.app.models.Dto.CourseResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CourseMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lessons", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "questions", ignore = true)
    @Mapping(target = "students", ignore = true)
    Course courseRequestToCourse(CourseRequest courseRequest);

    CourseResponseDTO courseToCourseResponseDTO(Course course);

    void updateCourseFromRequest(CourseRequest request, @MappingTarget Course course);
}