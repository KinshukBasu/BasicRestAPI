package controller;

/**
 * Created by KinshukBasu on 04-Jul-17.
 */

import model.User;
import services.UserService;

import static spark.Spark.*;
import spark.Request;
import spark.Response;
import spark.Route;

public class UserController {

    public UserController(final UserService userService) throws Exception {

        get("/users", new Route() {
            //@Override
            public Object handle(Request request, Response response) {
                // process request
                return userService.getAllUsers();
            }
        });

        //more routes
    }

}
