package com.example.API_SpringBoot.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {
    @GetMapping("/")
    public String calculatorForm(Model model) {
        model.addAttribute("result", 0);
        return "calculator";
    }

    @PostMapping("/")
    public String calculatorSubmit(@RequestParam int num1, @RequestParam int num2, @RequestParam String operation, Model model) {
        int result = 0;

        if (operation.equals("+")) {
            result = num1 + num2;
        } else if (operation.equals("-")) {
            result = num1 - num2;
        } else if (operation.equals("*")) {
            result = num1 * num2;
        } else if (operation.equals("/")) {
            result = num1 / num2;
        }

        model.addAttribute("result", result);
        return "calculator";
    }
}

