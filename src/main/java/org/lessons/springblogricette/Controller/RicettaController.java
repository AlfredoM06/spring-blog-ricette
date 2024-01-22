package org.lessons.springblogricette.Controller;

import org.lessons.springblogricette.Model.Ricetta;
import org.lessons.springblogricette.Repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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


}
