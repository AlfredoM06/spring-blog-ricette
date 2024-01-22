package org.lessons.springblogricette.Controller;

import jakarta.validation.Valid;
import org.lessons.springblogricette.Model.Ricetta;
import org.lessons.springblogricette.Repository.BlogRepository;
import org.lessons.springblogricette.Repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ricette")
public class RicettaController {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private CategorieRepository categorieRepository;


    @GetMapping
    public String index(Model model) {
        List<Ricetta> listaRicette = blogRepository.findAll();
        model.addAttribute("listaRicette", listaRicette);
        return "ricette/list";
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Optional<Ricetta> result = blogRepository.findById(id);
        if (result.isPresent()) {
            Ricetta ricetta = result.get();
            model.addAttribute("ricetta", ricetta);
            return "ricette/show";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ricetta with id " + id + " not found");

        }
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("ricetta", new Ricetta());
        model.addAttribute("listaCategorie", categorieRepository.findAll());
        return "ricette/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("ricetta") Ricetta formRicetta, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("listaCategorie", categorieRepository.findAll());
            return "ricette/create";
        }
        Ricetta saveRicetta = blogRepository.save(formRicetta);
        return "redirect:/ricette/show/" + saveRicetta.getId();
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Optional<Ricetta> result = blogRepository.findById(id);
        if (result.isPresent()) {
            model.addAttribute("ricetta", result.get());
            model.addAttribute("listaCategorie", categorieRepository.findAll());
            return "ricette/edit";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ricetta with id " + id + " not found");
        }
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("ricetta") Ricetta formRicetta, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("listaCategorie", categorieRepository.findAll());
            return "ricette/edit";
        }
        Ricetta saveRicetta = blogRepository.save(formRicetta);
        return "redirect:/ricette/show/" + id;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Optional<Ricetta> result = blogRepository.findById(id);
        if (result.isPresent()) {
            blogRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Ricetta " + result.get().getTitle() + " deleted!");
            return "redirect:/ricette";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ricetta with id " + id + " not found");
        }

    }
}
