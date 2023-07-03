package com.aademo.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TextController {

    @PostMapping("/text")
    public String receiveText(@RequestBody String text, String lastname) {
        // Process the received text here
        return "Received text: " + text;
    }

    @PostMapping("/text2")
    public String receiveText2(@RequestBody String text2) {
        // Process the received text here
        return "Received text: " + text2;
    }
}
