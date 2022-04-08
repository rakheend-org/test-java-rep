package za.co.rakheem.demo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

    @GetMapping("/injectCode")
    public String codeInject(String filepath) throws IOException {

        String[] cmdList = new String[]{"sh", "-c", "ls -la " + filepath};
        ProcessBuilder builder = new ProcessBuilder(cmdList);
        builder.redirectErrorStream(true);
        Process process = builder.start();
        return process.toString();
    }

    @RequestMapping("/passwords/")
    public String doSqlStuff(@RequestParam("username") String username) {

        StringBuilder result = new StringBuilder();

        try {
            Class.forName("org.mysql.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql//locahost:3306", "admin", "adminPass123456");

            if (!con.isClosed())
                System.out.println("Connect to database successfully.");

            Statement statement = con.createStatement();
            String sql = "select * from users where username = '" + username + "'";

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                String res_name = rs.getString("username");
                String res_pwd = rs.getString("password");
                String info = String.format("%s: %s\n", res_name, res_pwd);
                result.append(info);

            }
            rs.close();
            con.close();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
