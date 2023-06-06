package chinh.nguyen.controller;

import chinh.nguyen.dto.response.ResponseMessage;
import chinh.nguyen.model.Student;
import chinh.nguyen.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private IStudentService studentService;

    @GetMapping
    public ResponseEntity<?> showListStudent() {
        List<Student> studentList = studentService.findAll();
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody Student student){
        studentService.save(student);
        return new ResponseEntity<>(new ResponseMessage("create_success"), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@RequestBody Student student, @PathVariable Long id){
        if(!studentService.findById(id).isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
      student.setId(studentService.findById(id).get().getId());
        studentService.save(student);
        return new ResponseEntity<>(new ResponseMessage("update_success"), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id){
        if(!studentService.findById(id).isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        studentService.deleteById(id);
        return new ResponseEntity<>(new ResponseMessage("delete_success"), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> detailStudent(@PathVariable Long id){
        if(!studentService.findById(id).isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>( studentService.findById(id).get(), HttpStatus.OK);
    }
    @GetMapping("/page")
    public ResponseEntity<?> showPageStudent(@PageableDefault(sort = "name", size = 3, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Student> studentPage = studentService.findAll(pageable);
        return new ResponseEntity<>(studentPage, HttpStatus.OK);
    }
    @GetMapping("/search/{name}")
    public ResponseEntity<?> showListStudentSearch(@PathVariable String name) {
        List<Student> studentList = studentService.findByNameContaining(name);
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<?> showListStudentSearchFull(@RequestParam("name") String name) {
        List<Student> studentList = studentService.findByNameFull(name);
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }
}
