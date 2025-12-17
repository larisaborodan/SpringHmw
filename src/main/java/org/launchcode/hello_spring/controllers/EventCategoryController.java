package org.launchcode.hello_spring.controllers;



import jakarta.validation.Valid;
import org.launchcode.hello_spring.data.EventCategoryRepository;
import org.launchcode.hello_spring.models.EventCategory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("categories")
public class EventCategoryController {

    private final EventCategoryRepository eventCategoryRepository;

    public EventCategoryController(EventCategoryRepository eventCategoryRepository) {
        this.eventCategoryRepository = eventCategoryRepository;
    }

    // GET /categories
    @GetMapping
    public String displayAllCategories(Model model) {
        model.addAttribute("title", "All Categories");
        model.addAttribute("categories", eventCategoryRepository.findAll());
        return "categories/index";
    }

    // GET /categories/create
    @GetMapping("create")
    public String renderCreateCategoryForm(Model model) {
        model.addAttribute("title", "Create Category");
        model.addAttribute("eventCategory", new EventCategory());
        return "categories/create";
    }

    // POST /categories/create
    @PostMapping("create")
    public String processCreateCategoryForm(
            @ModelAttribute @Valid EventCategory eventCategory,
            Errors errors,
            Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Category");
            return "categories/create";
        }

        eventCategoryRepository.save(eventCategory);
        return "redirect:/categories";
    }
}

