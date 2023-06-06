package chinh.nguyen.service;

import chinh.nguyen.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IStudentService {
    Page<Student> findAll(Pageable pageable);
    List<Student> findAll();
    Optional<Student> findById(Long id);
    void save(Student student);
    void deleteById(Long id);
    List<Student> findByNameContaining(String name);
    List<Student> findByNameFull(String name);
}
