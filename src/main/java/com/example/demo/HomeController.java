package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    ToDoRepository toDoRepository;

    @RequestMapping("/")
    public String listToDo(Model model){
        model.addAttribute("toDos", toDoRepository.findAll());
        return "tasks";
    }

    @GetMapping("/add")
    public String toDoForm(Model model){
        model.addAttribute("toDo", new ToDo());
        return "toDoform";
    }

    @PostMapping("/process")
    public String processForm(@Valid ToDo toDo, BindingResult result){
        if (result.hasErrors()){
            return "toDoform";
        }
        toDoRepository.save(toDo);
        return "redirect:/";
    }
}
