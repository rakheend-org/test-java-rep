package za.co.rakheem.demo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/password/")
@RestController
public class PasswordController {


    @GetMapping("{oldPassword}/change/{newPassword}")
    public String changePassword(@PathVariable final String oldPassword, @PathVariable final String newPassword) {
        System.out.println(oldPassword);
        return newPassword;
    }

}
