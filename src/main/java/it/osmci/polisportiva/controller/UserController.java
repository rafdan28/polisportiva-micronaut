package it.osmci.polisportiva.controller;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import it.osmci.polisportiva.model.User;
import it.osmci.polisportiva.service.UserService;
import io.micronaut.http.HttpResponse;
import jakarta.inject.Inject;

import javax.validation.Valid;

@Controller("/users")
public class UserController {
    @Inject
    private UserService userService;

    @Get()
    public HttpResponse<User> getUserById(Long userId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            return HttpResponse.ok(user);
        } else {
            return HttpResponse.notFound();
        }
    }

    @Post()
    public HttpResponse<User> registerUser(@Body @Valid User user) {
        User createdUser = userService.registerUser(user);
        return HttpResponse.created(createdUser);
    }
}
