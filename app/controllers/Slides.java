package controllers;

import java.util.List;

import models.Reservation;
import play.data.DynamicForm;
import play.mvc.Content;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.database;
import views.html.everythingElse;
import views.html.index;
import views.html.intro;
import views.html.routing;
import views.html.templating;
import views.html.webservices;

import com.avaje.ebean.Ebean;

public class Slides extends Controller {
  
    public static Result index() {
        String title = "Play Framework for Hackers";
	return ok(index.render(title));
    }
    
    public static Result intro(){
	return ok(intro.render());
    }
  
    public static Result routing(){
	return ok(routing.render());
    }
    
    public static Result templating(){
	Reservation r = new Reservation("BOOMBOX");
	Content out = templating.render(r);
	return ok(out);
    }
    
    public static Result database(){
	List<Reservation> reservations = Ebean.find(Reservation.class).findList();
	return ok(database.render(DynamicForm.form(Reservation.class) , reservations));
     }
    
    public static Result webservices(){
	return ok(webservices.render(""));
    }
    
    public static Result everythingElse(){
	String lang = request().getHeader(ACCEPT_LANGUAGE);
	String imgName = (lang != null && lang.toLowerCase().startsWith("en-us")) ? "merka" : "france_flag" ; 
	return ok(everythingElse.render(imgName));
     }

}
