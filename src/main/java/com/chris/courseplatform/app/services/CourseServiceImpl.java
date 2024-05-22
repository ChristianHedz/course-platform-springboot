package com.chris.courseplatform.app.services;

import com.chris.courseplatform.app.exceptions.ResourceNotFoundException;
import com.chris.courseplatform.app.mappers.CourseMapper;
import com.chris.courseplatform.app.models.Course;
import com.chris.courseplatform.app.models.Dto.CourseRequest;
import com.chris.courseplatform.app.models.Dto.CourseResponseDTO;
import com.chris.courseplatform.app.models.User;
import com.chris.courseplatform.app.repositories.CourseRepository;
import com.chris.courseplatform.app.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{

    private final CourseMapper courseMapper;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Override
    public CourseResponseDTO createCourse(CourseRequest courseRequest) {
        Course course = courseMapper.courseRequestToCourse(courseRequest);
        User instructor = userRepository.findById(courseRequest.getInstructor().getId())
                .orElseThrow(()->new ResourceNotFoundException("instructor","id",courseRequest.getInstructor().getId()));
        course.setInstructor(instructor);
        courseRepository.save(course);
        return courseMapper.courseToCourseResponseDTO(course);
    }

@Override
public CourseResponseDTO updateCourse(Long id, CourseRequest courseRequest) {
    Course courseUpdate = courseRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Course","id",id));
    courseMapper.updateCourseFromRequest(courseRequest, courseUpdate);
    courseRepository.save(courseUpdate);
    return courseMapper.courseToCourseResponseDTO(courseUpdate);
}

    @Override
    public void deleteCourse(Long id) {

    }


}
