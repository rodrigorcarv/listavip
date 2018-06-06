package br.com.alura.listavip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.listavip.repository.ConvidadoRepository;

@Controller
public class ConvidadoController {

    @Autowired
    ConvidadoRepository convidadoRepository; 

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("listaconvidados")
    public String listaConvidados(Model model) {

        model.addAttribute("convidados", convidadoRepository.findAll());
        return "listaconvidados";
    }

}
