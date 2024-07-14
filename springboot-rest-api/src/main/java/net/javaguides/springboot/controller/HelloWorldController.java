package net.javaguides.springboot.controller;


//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
We use @Controller annotation to make a Java Class as a Spring MVC Controller.
We use @ResponseBody annotation tells a Controller that the object returned is
automatically serialized into JSON and passed back into HttpResponse object.
We use @RestController which is a combination of @Controller & @ResponseBody
We use @GetMapping annotation to map HTTPGET request onto specific handler method
 */
//@Controller
//@ResponseBody
@RestController
public class HelloWorldController {

    //HTTP GET Request
    //http://localhost:8080/hello-world

    @GetMapping("/hello-world")
    public  String HelloWord(){
        return "Welcome to Hello World..!!";
    }
}
