package br.com.alura.listavip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.alura.enviadorEmail.enviadorEmail.service.EmailService;
import br.com.alura.listavip.model.Convidado;
import br.com.alura.listavip.repository.ConvidadoRepository;

@Controller
public class ConvidadoController {

    @Autowired
    ConvidadoRepository convidadoRepository; 

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping(value= "listaconvidados", method = RequestMethod.GET)
    public String listaConvidados(Model model) {

        model.addAttribute("convidados", convidadoRepository.findAll());
        return "listaconvidados";
    }

    @RequestMapping(value= "salvar", method = RequestMethod.POST)
    public String salvar(
            @RequestParam("nome") String nome, 
            @RequestParam("email") String email, 
            @RequestParam("telefone") String telefone, Model model) {

        Convidado convidado = new Convidado(nome, email, telefone);
        convidadoRepository.save(convidado);

        new EmailService().enviar(nome, email);

        Iterable<Convidado> convidados = convidadoRepository.findAll();
        model.addAttribute("convidados", convidados);

        return "listaconvidados";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long idConvidado, Model model) {
        Convidado convidado = convidadoRepository.findOne(idConvidado);
        convidadoRepository.delete(convidado);

        Iterable<Convidado> convidados = convidadoRepository.findAll();
        model.addAttribute("convidados", convidados);
        return "redirect:/listaconvidados";
    }

}
