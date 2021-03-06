package blog.controller;

import blog.form.RegisterNewUser;
import blog.model.User;
import blog.services.PostService;
import blog.services.UserServiceImp;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.nio.charset.StandardCharsets;

@Controller
public class UserController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserServiceImp userServiceImp;

    @RequestMapping("/users/login")
    private String loginPage(User user){

        return "users/login";
    }

    @RequestMapping(value = "/users/login", method = RequestMethod.POST)
    public String login(User user, Model model) {
        if (userServiceImp.authenticate(user.getUserName(), user.getPasswordHash())) {
            return "redirect:/posts";
        }

        return "redirect:/";
    }

    @RequestMapping("/users/logout")
    public String logout(User user, Model model) {

        return "redirect:/";
    }

    @RequestMapping("/users/register")
    public String register(RegisterNewUser registerNewUser) {
        return "users/register";
    }

    @RequestMapping(value = "/users/register", method = RequestMethod.POST)
    public String registerNewUser(RegisterNewUser registerNewUser) {
        User user = new User();
        user.setUserName(registerNewUser.getUserName());
        user.setFullName(registerNewUser.getFullName());
        String sha256hex;

        sha256hex = Hashing.sha256().hashString(registerNewUser.getPassword(), StandardCharsets.UTF_8).toString();
        user.setPasswordHash(sha256hex);
        userServiceImp.registerNewUser(user);
        return "redirect:/";
    }

}
