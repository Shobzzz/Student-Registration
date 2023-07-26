// CourseRepository.java
package com.example.StudentCrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.StudentCrud.domain.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    // Add any custom query methods if needed
}
