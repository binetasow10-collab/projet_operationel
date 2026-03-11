package com.bina.bsprojet.controller;

import com.bina.bsprojet.model.Task;
import com.bina.bsprojet.repository.TaskRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("tasks", taskRepository.findAll());

        return "index";
    }

    @GetMapping("/add")
    public String add() {

        return "add";
    }

    @PostMapping("/save")
    public String save(Task task) {

        taskRepository.save(task);

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        taskRepository.deleteById(id);

        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {

        Task task = taskRepository.findById(id).orElse(null);

        model.addAttribute("task", task);

        return "edit";
    }

    @PostMapping("/update")
    public String update(Task task) {

        taskRepository.save(task);

        return "redirect:/";
    }
}