import java.util.Locale;

import org.joda.time.DateTimeZone;

import play.Application;
import play.GlobalSettings;

/**
 * 
 */

/**
 * @author tpearson
 *	Class for interations with the Play Server
 */
public class Global extends GlobalSettings{

    @Override
    public void onStart(Application app){
	Locale.setDefault(Locale.US);
	DateTimeZone.setDefault(DateTimeZone.UTC);
    }
}
