package it.osmci.polisportiva.controller;

import io.micronaut.http.annotation.*;
import it.osmci.polisportiva.model.User;
import it.osmci.polisportiva.service.UserService;
import io.micronaut.http.HttpResponse;
import jakarta.inject.Inject;

import javax.validation.Valid;

@Controller("/users")
public class UserController {
    @Inject
    private UserService userService;

    @Post
    public HttpResponse<Object> registerUser(@Body @Valid User user) {
        try {
            return HttpResponse.created(userService.registerUser(user));
        }
        catch (Exception e){
            return HttpResponse.notFound(e.getMessage());
        }
    }

    @Get
    public HttpResponse<Object> findAll() {
        try {
            return HttpResponse.ok(userService.findAll());
        }
        catch (Exception e){
            return HttpResponse.notFound(e.getMessage());
        }
    }

    @Get("/{userId}")
    public HttpResponse<Object> getUserById(Long userId) {
        try {
            return HttpResponse.ok(userService.getUserById(userId));
        }
        catch (Exception e){
            return HttpResponse.notFound(e.getMessage());
        }
    }

    @Delete("/{userId}")
    public HttpResponse<Object> deleteUserById(@PathVariable Long userId) {
        try{
            return HttpResponse.ok(userService.deleteUserById(userId));
        }
        catch (Exception e){
            return HttpResponse.notFound(e.getMessage());
        }
    }
}
