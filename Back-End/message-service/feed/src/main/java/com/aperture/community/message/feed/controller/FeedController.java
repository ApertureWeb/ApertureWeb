package com.aperture.community.message.feed.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedController {


    @GetMapping("test")
    public void fluxTest() {

    }

}