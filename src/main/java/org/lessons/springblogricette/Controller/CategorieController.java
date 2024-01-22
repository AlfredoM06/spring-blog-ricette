package org.lessons.springblogricette.Controller;

import jakarta.validation.Valid;
import org.lessons.springblogricette.Model.Categoria;
import org.lessons.springblogricette.Repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/categorie")
public class CategorieController {

    @Autowired
    private CategorieRepository categorieRepository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("listaCategorie", categorieRepository.findAll());
        return "categorie/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("formCategoria", new Categoria());
        return "categorie/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("formCategoria") Categoria formCategoria, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "categorie/create";
        }
        categorieRepository.save(formCategoria);
        return "redirect:/categorie";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Optional<Categoria> result = categorieRepository.findById(id);
        if (result.isPresent()) {
            model.addAttribute("formCategoria", result.get());
            return "categorie/create";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "la categoria con id " + id + " non è stata trovata");
        }
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("categoria") Categoria formCategoria, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "categorie/create";
        }
        Categoria updatedCategoria = categorieRepository.save(formCategoria);
        return "redirect:/categorie";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Optional<Categoria> result = categorieRepository.findById(id);
        if (result.isPresent()) {
            categorieRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "La categoria " + result.get().getName() + " è stata eliminata");
            return "redirect:/categorie";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "la categoria con id " + id + " non è stata trovata");
        }
    }

}
