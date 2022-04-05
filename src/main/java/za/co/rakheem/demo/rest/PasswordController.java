package za.co.rakheem.demo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;

@RestController
@RequestMapping("/api/password/")
public class PasswordController {


    @GetMapping("{oldPassword}/change/{newPassword}")
    public String changePassword(@PathVariable final String oldPassword, @PathVariable final String newPassword) {
        System.out.println(oldPassword);
        return newPassword;
    }

    @GetMapping("/load/custom-config")
    public void yarm(String content) {
        var configuration = new Yaml();
        configuration.load(content);
    }

    @GetMapping("/codeinject")
    public String codeInject(String filepath) throws IOException {

        String[] cmdList = new String[]{"sh", "-c", "ls -la " + filepath};
        ProcessBuilder builder = new ProcessBuilder(cmdList);
        builder.redirectErrorStream(true);
        Process process = builder.start();
        return process.toString();
    }
}
