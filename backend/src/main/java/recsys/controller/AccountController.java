package recsys.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/account")
public class AccountController {

    @Get
    public String index() {
        return "See my accounts :D ";
    }
}

