package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.entities.User;
import web.service.UserService;

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public String create(@ModelAttribute("create_user") User user){
        if(user != null) {
            userService.save(user);
        }
        return "redirect:/users";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("newUser") User user) {
        return "new_user";
    }

    @GetMapping()
    public String read(Model model) {
        model.addAttribute("get_user", userService.read());
        return "user_show";
    }

    @GetMapping("/user/delete/YES")
    public String deleteUser(@RequestParam("id") long id, Model model) {
        model.addAttribute("del_user", userService.delete(id));
        return "redirect:/users";
    }
    @GetMapping("/user/delete")
    public String pageDelete(@RequestParam("id") long id, Model model) {
        model.addAttribute("que", userService.userPage(id));
        return "delete_user";
    }

    @GetMapping("/user/update")
    public String pageUpdate(@RequestParam("id") long id, Model model) {
        model.addAttribute("up_user", userService.upPage(id));
        return "update_user";
    }
    @PostMapping("/user/edit")
    public String update(@RequestParam("id") long id, @RequestParam("name") String name,
                         @RequestParam("lastName") String lastname, Model model) {
        model.addAttribute("update", userService.update(id, name, lastname));
        return "redirect:/users";
    }


}
