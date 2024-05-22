package com.chris.courseplatform.app.controllers;

import com.chris.courseplatform.app.models.Dto.CourseRequest;
import com.chris.courseplatform.app.models.Dto.CourseResponseDTO;
import com.chris.courseplatform.app.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping("/course")
    public ResponseEntity<CourseResponseDTO> createCourse(@RequestBody CourseRequest courseRequest){
        CourseResponseDTO response = courseService.createCourse(courseRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/course/{id}")
    public ResponseEntity<CourseResponseDTO> updateCourse(@PathVariable Long id, @RequestBody CourseRequest courseRequest){
        CourseResponseDTO response = courseService.updateCourse(id,courseRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id){
        courseService.deleteCourse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }






}
