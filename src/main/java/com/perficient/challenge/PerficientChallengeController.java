package com.perficient.challenge;

import io.micronaut.http.annotation.*;

@Controller("/perficient-challenge")
public class PerficientChallengeController {

    @Get(uri = "/", produces = "text/plain")
    public String index() {
        return "Example Response";
    }
}