package com.alex.blog.controller;

import com.alex.blog.domain.User;
import com.alex.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    /**
     * 查询所有用户
     * @param model
     * @return
     */
    @GetMapping
    public ModelAndView list(Model model){
        model.addAttribute("userList", repository.findAll());
        model.addAttribute("title", "用户管理");
        return new ModelAndView("users/list", "userModel", model);
    }

    /**
     * 查询某个用户
     * @param model
     * @return
     */
    @GetMapping("{id}")
    public ModelAndView view(@PathVariable("id") Long id, Model model){
        Optional<User> optionalUser = repository.findById(id);
        User user = optionalUser.get();
        model.addAttribute("user", user);
        model.addAttribute("title", "查看用户");
        return new ModelAndView("users/view", "userModel", model);
    }

    /**
     * 创建用户
     * @param model
     * @return
     */
    @GetMapping("/form")
    public ModelAndView createForm(Model model){
        model.addAttribute("user", new User(null, null, null));
        model.addAttribute("title", "创建用户");
        return new ModelAndView("users/form", "userModel", model);
    }

    @PostMapping()
    public ModelAndView saveOrUpdateUser(User user){
        user = repository.save(user);
        return new ModelAndView("redirect:/users");
    }

    /**
     * 删除用户
     */
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id,Model model){
        repository.deleteById(id);
        return new ModelAndView("redirect:/users");
    }

    /**
     * 修改用户
     */
    @GetMapping("/modify/{id}")
    public ModelAndView modify(@PathVariable("id") Long id, Model model){
        Optional<User> optionalUser = repository.findById(id);
        User user = optionalUser.get();
        model.addAttribute("user", user);
        return new ModelAndView("/users/form", "model",model);
    }
}
