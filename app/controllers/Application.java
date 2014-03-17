package controllers;

import java.util.List;

import models.Reservation;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import play.Logger;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.F.Promise;
import play.libs.WS;
import play.libs.WS.WSRequestHolder;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.WebSocket;
import views.html.database;
import views.html.webservices;

import com.avaje.ebean.Ebean;

import flickr.FlickrHandler;

public class Application extends Controller {
    
    
    public static Result newReservation() {
        DynamicForm input = DynamicForm.form().bindFromRequest(request());
        String name = input.get("traveler_name");
	Reservation r = new Reservation(name);
        Ebean.save(r);
        
        List<Reservation> allReservations = Ebean.find(Reservation.class).findList();
        Form<Reservation> reservationForm = new Form<Reservation>(Reservation.class);
	return ok(database.render(reservationForm, allReservations));    
    }
    
    public static Result searchImage(String query){
        final FlickrHandler flickr = new FlickrHandler();
	WSRequestHolder searchRQ = flickr.buildSearchRequest(query);
	Logger.info("Ready to search Flickr for " +searchRQ.toString());
	Promise<WS.Response> searchRS = searchRQ.get();
	
	Logger.info("Promised flickr result returned status code " +searchRS.get().getStatus());
	String imageURL = flickr.handleResponse(searchRS.get().asXml());
	
	return ok(webservices.render(imageURL));
    }

    public static WebSocket<String> time(){
	return new WebSocket<String>(){
	    
        	@Override
        	public void onReady(WebSocket.In<String> in, WebSocket.Out<String> out) {
        		DateTime now = new DateTime();
			DateTimeFormatter format = DateTimeFormat.longDateTime();
			String time = now.toString(format);
			out.write(time);
        	}
	};
    }
} 
