package org.lessons.springblogricette.Controller;

import org.lessons.springblogricette.Model.Ricetta;
import org.lessons.springblogricette.Repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ricette")
public class RicettaController {

    @Autowired
    private BlogRepository blogRepository;


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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza with id " + id + " not found");

        }
    }

}
