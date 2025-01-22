package com.umesh.RoleBasedAuthorization.repository;

import com.umesh.RoleBasedAuthorization.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student,Integer> {
}
