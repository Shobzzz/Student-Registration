package com.example.StudentCrud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.StudentCrud.domain.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	 List<Student> findByDeletedFalse(); // Fetch active students (where 'deleted' is false)
	    List<Student> findByDeletedTrue();  // Fetch inactive students (where 'deleted' is true)
	
}
