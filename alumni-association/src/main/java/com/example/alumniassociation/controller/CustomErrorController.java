<<<<<<< HEAD
package com.example.alumniassociation.controller;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @GetMapping("/error")
    public String handleError() {
        return "error"; // Create an error.html in templates directory
    }
    public String getErrorPath() {
        return "/error";
    }
}

=======
package com.example.alumniassociation.controller;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @GetMapping("/error")
    public String handleError() {
        return "error"; // Create an error.html in templates directory
    }
    public String getErrorPath() {
        return "/error";
    }
}

>>>>>>> f41e2e3 (Add project files)
