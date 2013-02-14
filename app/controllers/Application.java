package controllers;

import play.*;
import play.libs.*;
import play.libs.F.*;
import play.mvc.*;
import play.data.*;

import views.html.*;

import models.*;
import java.util.*;

import logic.*;

public class Application extends Controller {
  
    public static Result index() {
    	List<Task> tasks = Task.all();
        return ok(index.render("Your new application is ready, and you have " + tasks.size() + " tasks!"));
    }

    public static Result addTask() {
    	Task newTask = new Task();
    	newTask.label = "My task!!";
    	Task.create(newTask);
    	return ok(index.render("Task saved!"));
    }

    public static play.mvc.WebSocket<String> sockets() {
    	return new logic.WebSocket();
    }
  
}
