package de.fh.albsig0.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CovidController {

    @RequestMapping("/")
    public String sayHello() {
        return "<h1>Hallo</h1>";
    }
}
