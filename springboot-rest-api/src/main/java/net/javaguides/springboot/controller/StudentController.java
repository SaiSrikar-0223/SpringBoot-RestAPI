package net.javaguides.springboot.controller;

import net.javaguides.springboot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    //localhost:8080/student
    @GetMapping
    public Student getStudent(){
        Student student=new Student(1,"Kalyan","Gudala");
        return  student;
    }
    //localhost:8080/students
    @GetMapping("/students")
    public List<Student> students(){
        List<Student> students=new ArrayList<>();
        students.add(new Student(1,"Kalyan","Gudala"));
        students.add(new Student(2,"Sai","Srikar"));
        students.add(new Student(3,"Saritha","Gudala"));
        return students;
    }

    //Spring Boot Rest API with @PathVariable :
    // {id} => URI template Variable
    //@PathVariable annotation used on a method argument to bind it to the value of URI template variable
    //localhost:8080/students/1
    @GetMapping("/students/{id}")
    public Student studentPathVariable(@PathVariable("id") int studentId){
        return new Student(studentId,"Kalyan","Gudala");
    }
    //localhost:8080/students/1/Kalyan/Gudala
    @GetMapping("/students/{id}/{first-name}/{last-name}")
    public Student studentPathVariable(@PathVariable("id") int studentId,
                                       @PathVariable("first-name") String firstName,
                                       @PathVariable("last-name") String lastName){
        return new Student(studentId,firstName,lastName);
    }

    //Spring Boot Rest API with @RequestParam
    //localhost:8080/students/query?id=1
    @GetMapping("/students/query")
    public Student studentRequestVariable(@RequestParam int id){
        return new Student(id,"Kalyan","Gudala");
    }

    //Spring Boot Rest API with @RequestParam
    //localhost:8080/students/querys?id=1&firstName=Kalyan&lastName=Gudala
    @GetMapping("/students/querys")
    public Student studentRequestParamVariables(@RequestParam int id,
                                          @RequestParam String firstName,
                                          @RequestParam String lastName){
        return new Student(id,firstName,lastName);
    }

    //Spring Boot Rest API that handles HTTP POST Request :creates the new resource
    //@PostMapping and @RequestBody
    //@RequestBody annotation is responsible for retrieving the HTTP request body &
    // automatically converting into Java Object
    //localhost:8080/students/create
    @PostMapping("/students/create")
    @ResponseStatus(HttpStatus.CREATED)
    public  Student createStudent(@RequestBody Student student){
        System.out.println(student.getStudentId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }
   //Spring Boot Rest API that handles HTTP PUT request : updates the existing resource
   //localhost:8080/students/3/update
   @PutMapping("/students/{id}/update")
   public Student updateStudent(@RequestBody Student student,@PathVariable("id") int studentId){
       System.out.println(student.getFirstName());
       System.out.println(student.getLastName());
       return  student;
   }
   //Spring Boot Rest API that handles HTTP DELETE request: deletes the existing resource
    //localhost:8080/students/3/delete
    @DeleteMapping("/students/{id}/delete")
    public String deleteStudent(@PathVariable("id") int studentId){
        System.out.println(studentId);
        return "Student deleted successfully..!!";
    }

    //localhost:8080/studentrespentity
    @GetMapping("/studentrespentity")
    public ResponseEntity<Student> getStudentEntity(){
        Student student=new Student(1,"Kalyan","Gudala");
        //return  new ResponseEntity<>(student,HttpStatus.OK);
        //return ResponseEntity.ok(student);
        return ResponseEntity.ok().header("custome-header",
                "kalyan").body(student);
    }

    //localhost:8080/studentsrespentity
    @GetMapping("/studentsrespentity")
    public ResponseEntity<List<Student>> getStudentsEntity(){
        List<Student> students=new ArrayList<>();
        students.add(new Student(1,"Kalyan","Gudala"));
        students.add(new Student(2,"Sai","Srikar"));
        students.add(new Student(3,"Saritha","Gudala"));
        return ResponseEntity.ok(students);
    }

    //localhost:8080/studentsrespentity/1/Kalyan/Gudala
    @GetMapping("/studentsrespentity/{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentRespEntityPathVariable(@PathVariable("id") int studentId,
                                       @PathVariable("first-name") String firstName,
                                       @PathVariable("last-name") String lastName){
        Student student=new Student(studentId,firstName,lastName);
        return ResponseEntity.ok(student);
    }

    //Spring Boot Rest API with @RequestParam
    //localhost:8080/studentsrespentity/query?id=1
    @GetMapping("/studentsrespentity/query")
    public ResponseEntity<Student> studentRespEntityRequestVariable(@RequestParam int id){
       Student student=new Student(id,"Kalyan","Gudala");
       return ResponseEntity.ok(student);
    }

    //Spring Boot Rest API with @RequestParam
    //localhost:8080/studentsrespentity/querys?id=1&firstName=Kalyan&lastName=Gudala
    @GetMapping("/studentsrespentity/querys")
    public ResponseEntity<Student> studentRespEntityRequestParamVariables(@RequestParam int id,
                                                @RequestParam String firstName,
                                                @RequestParam String lastName){
       Student student=new  Student(id,firstName,lastName);
       return ResponseEntity.ok(student);
    }

    //localhost:8080/studentsrespentity/create
    @PostMapping("/studentsrespentity/create")
    //@ResponseStatus(HttpStatus.CREATED)
    public  ResponseEntity<Student> createRespEntityStudent(@RequestBody Student student){
        System.out.println(student.getStudentId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student,HttpStatus.CREATED);
    }

    //localhost:8080/studentsrespentity/3/update
    @PutMapping("/studentsrespentity/{id}/update")
    public ResponseEntity<Student> updateRespEntityStudent(@RequestBody Student student,@PathVariable("id") int studentId){
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return ResponseEntity.ok(student);
    }

    //Spring Boot Rest API that handles HTTP DELETE request: deletes the existing resource
    //localhost:8080/studentsrespentity/3/delete
    @DeleteMapping("/studentsrespentity/{id}/delete")
    public ResponseEntity<String> deleteRespEntityStudent(@PathVariable("id") int studentId){
        System.out.println(studentId);
        return ResponseEntity.ok("Student deleted successfully..!!");
    }
}
