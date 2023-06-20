package it.osmci.polisportiva.controller;

import io.micronaut.http.annotation.*;
import it.osmci.polisportiva.model.User;
import it.osmci.polisportiva.service.UserService;
import io.micronaut.http.HttpResponse;
import jakarta.inject.Inject;

import javax.validation.Valid;
import java.util.List;

@Controller("/users")
public class UserController {
    @Inject
    private UserService userService;

    @Post()
    public HttpResponse<User> registerUser(@Body @Valid User user) {
        User createdUser = userService.registerUser(user);
        return HttpResponse.created(createdUser);
    }

    @Get
    public HttpResponse<List<User>> findAll() {
        List<User> userList = userService.findAll();
        if(userList.size() != 0){
            return HttpResponse.ok(userList);
        }
        return HttpResponse.notFound();
    }

    @Get("/{userId}")
    public HttpResponse<User> getUserById(Long userId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            return HttpResponse.ok(user);
        }
        return HttpResponse.notFound();
    }

    @Delete("/{userId}")
    public void deleteUserById(@PathVariable Long userId) {
        userService.deleteUserById(userId);
    }
}
