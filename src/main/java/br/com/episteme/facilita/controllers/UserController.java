package br.com.episteme.facilita.controllers;

import br.com.episteme.facilita.dto.RequisicaoNovoUser;
import br.com.episteme.facilita.models.Disciplina;
import br.com.episteme.facilita.models.TipoDeProva;
import br.com.episteme.facilita.models.User;
import br.com.episteme.facilita.repository.UserRepository;

import br.com.episteme.facilita.service.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServiceUser userService;

    @GetMapping("/home")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("tipos", TipoDeProva.values());
        return mv;
    }

    @GetMapping("/admin/home")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ModelAndView inicial() {
        ModelAndView mv = new ModelAndView("admin/home");
        mv.addObject("tipos", TipoDeProva.values());
        return mv;
    }

    @GetMapping("/usuarios")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ModelAndView lista() {
        List<User> usuarios = userRepository.findAll();
        ModelAndView mv = new ModelAndView("usuarios/lista");
        mv.addObject("usuarios", usuarios);
        return mv;
    }

    @GetMapping("/cadastro")
    public ModelAndView cadastrar(RequisicaoNovoUser requisicao) {
        ModelAndView mv = new ModelAndView("usuarios/cadastro");
        return mv;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView("usuarios/login");
        return mv;
    }

    @GetMapping("/saibaMais")
    public ModelAndView info() {
        ModelAndView mv = new ModelAndView("saibaMais");
        return mv;
    }

    @GetMapping("/perfil")
    public static ModelAndView perfil(@AuthenticationPrincipal User usuario){
        System.out.println(usuario);
        ModelAndView mv = new ModelAndView("usuarios/perfil");
        mv.addObject("usuario", usuario);
        return mv;
    }

    @GetMapping("/sugeridos")
    public ModelAndView sugerirSimulado(){
        ModelAndView mv = new ModelAndView("usuarios/sugeridos");
        return mv;
    }

    @GetMapping("/calendario")
    public ModelAndView mostrarCalendario(){
        ModelAndView mv = new ModelAndView("usuarios/calendario");
        return mv;
    }

    @PostMapping("/salvarUsuario")
    public ModelAndView create(@Valid RequisicaoNovoUser requisicao, BindingResult br) throws Exception {
        System.out.println(requisicao);
        ModelAndView mv = new ModelAndView("usuarios/cadastro");
        if (br.hasErrors()) {
            return mv;
        }

        if (userService.validarEmail(requisicao)) {
            userService.salvarUsuario(requisicao);
            return login();
        } else {
            mv.addObject("mensagem", "Já existe um usuário cadastrado com esse email");
            return mv;
        }
    }

    @GetMapping("/{id}/delete")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ModelAndView deletarUsuario(@PathVariable Long id){
        ModelAndView mv = new ModelAndView("redirect:/usuarios");
        this.userRepository.deleteById(id);
        return mv;
    }
}

