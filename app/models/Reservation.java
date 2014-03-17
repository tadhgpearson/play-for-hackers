/**
 * 
 */
package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author tpearson
 *
 */
@Entity
public class Reservation {

    @Id
    final String recordLocator;
    
    final String travelerName;

    public Reservation(String travelerName) {
	this.recordLocator = RandomStringUtils.randomAlphanumeric(6).toUpperCase();
	this.travelerName = travelerName;
    }
    
    public String getRecordLocator() {
        return recordLocator;
    }
    
    public String getTravelerName() {
        return travelerName;
    }

}
