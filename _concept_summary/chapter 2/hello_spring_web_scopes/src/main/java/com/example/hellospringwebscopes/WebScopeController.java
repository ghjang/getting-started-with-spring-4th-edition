package com.example.hellospringwebscopes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class WebScopeController {

    @Autowired
    private ApplicationBean applicationBean;

    @Autowired
    private RequestBean requestBean;

    @Autowired
    private SessionBean sessionBean;

    @GetMapping("/application")
    public String getApplicationScopeMessage(Model model) {
        model.addAttribute("applicationMessage", applicationBean.getMessage());
        return "application";
    }

    @GetMapping("/request")
    public String getRequestScopeMessage(Model model) {
        model.addAttribute("requestMessage", requestBean.getMessage());
        return "application";
    }

    @GetMapping("/session")
    public String getSessionScopeMessage(Model model, HttpSession session) {
        model.addAttribute("sessionMessage", sessionBean.getMessage());
        return "application";
    }
}