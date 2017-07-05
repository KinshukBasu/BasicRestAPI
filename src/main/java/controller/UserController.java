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

import static utility.jsonUtil.*;
import utility.ResponseError;

public class UserController {

    public UserController(final UserService userService) throws Exception {

        get("/users", (req, res) -> userService.getAllUsers(), json());

        //more routes
        get("/users/:id", (req,res) ->{
            String id = req.params(":id");
            User user = userService.getUser(id);
            if(user != null){
                return user;
            }
            res.status(400);
            return new ResponseError("No user with id \'%s\' found", id);
        }, json());

        post("/users", (req,res) -> userService.createUser(
             req.params(":id"),
             req.queryParams("name"),
             req.queryParams("email")
        ), json());


        exception(IllegalArgumentException.class, (e,req,res)->{
            res.status(400);
            res.body(toJson(new ResponseError(e)));
        });
    }

}
