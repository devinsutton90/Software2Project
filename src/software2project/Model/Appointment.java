package software2project.Model;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Appointment {
    //Properties
    private Customer customer;
    private SimpleIntegerProperty appointmentId;
    private SimpleStringProperty title;
    private SimpleStringProperty description;
    private SimpleStringProperty location;
    private SimpleStringProperty contact;
    private SimpleStringProperty URL;
    private ZonedDateTime startTime;
    private ZonedDateTime endTime;
    
    //Constructor
    public Appointment(Customer c, int id, String title, String desc, String loc, 
            String contact, String URL, ZonedDateTime start, ZonedDateTime end){
        this.appointmentId = new SimpleIntegerProperty(id);
        this.customer = c;
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(desc);
        this.location = new SimpleStringProperty(loc);
        this.contact = new SimpleStringProperty(contact);
        this.URL = new SimpleStringProperty(URL);
        this.startTime = start.withZoneSameInstant(ZoneId.systemDefault());
        this.endTime = end.withZoneSameInstant(ZoneId.systemDefault());
    }
    
    public Appointment(){
        this.appointmentId = new SimpleIntegerProperty();
        this.customer = new Customer();
        this.title = new SimpleStringProperty();
        this.description = new SimpleStringProperty();
        this.location = new SimpleStringProperty();
        this.contact = new SimpleStringProperty();
        this.URL = new SimpleStringProperty();
        this.startTime = null;
        this.endTime = null;
    }
    
    //Set Methods
    public void setCustomer(Customer c){
        this.customer = c;
    }
    
    public void setAppointmentId(int id){
        this.appointmentId.set(id);
    }
    
    public void setTitle(String t){
        this.title.set(t);
    }
    
    public void setDescription(String d){
        this.description.set(d);
    }
    
    public void setLocation(String l){
        this.location.set(l);
    }
    
    public void setContact(String c){
        this.contact.set(c);
    }
    
    public void setURL(String url){
        this.URL.set(url);
    }
    
    public void setStartTime(ZonedDateTime start){
        this.startTime = start;
    }
    
    public void setEndTime(ZonedDateTime end){
        this.endTime = end;
    }

    //Get Methods
    public int getAppointmentId(){
        return this.appointmentId.get();
    }
    
    public String getTitle(){
        return this.title.get();
    }
    
    public String getDescription(){
        return this.description.get();
    }
    
    public String getLocation(){
        return this.location.get();
    }
    
    public String getContact(){
        return this.contact.get();
    }
    
    public String getURL(){
        return this.URL.get();
    }
    
    public String getDate(){
        return this.startTime.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
    }
    
    public String getAppointmentStartTime(){
        return this.startTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }
    
    public String getAppointmentEndTime(){
        return this.endTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }
    
    public final String getStartTimeUTC(){
        ZonedDateTime utcTime = startTime.withZoneSameInstant(ZoneId.of("UTC"));
        String startTimeString = utcTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        
        return startTimeString;
    }
    
    public final String getEndTimeUTC(){
        ZonedDateTime utcTime = endTime.withZoneSameInstant(ZoneId.of("UTC"));
        String endTimeString = utcTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        
        return endTimeString;
    }
    
    public final String getStartTimeCurrentZone(){
        ZonedDateTime localTime = startTime.withZoneSameInstant(ZoneOffset.systemDefault());
        String endTimeString = localTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        
        return endTimeString;
    }
    
    public final String getEndTimeCurrentZone(){
        ZonedDateTime localTime = endTime.withZoneSameInstant(ZoneOffset.systemDefault());
        String endTimeString = localTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        
        return endTimeString;
    }
    
    public final ZonedDateTime getStartZDT(){
        return this.startTime;
    }
    
    //Get Customer Information Methods
    public int getCustomerId(){
        return this.customer.getCustomerId();
    }
    
    public String getCustomerName(){
        return this.customer.getCustomerName();
    }
    
    public int getActive(){
        return this.customer.getActive();
    }
    
    public int getAddressId(){
        return this.customer.getAddressId();
    }
    
    public String getAddress(){
        return this.customer.getAddress();
    }
    
    public String getAddress2(){
        return this.customer.getAddress2();
    }
    
    public String getPostalCode(){
        return this.customer.getPostalCode();
    }
    
    public String getPhone(){
        return this.customer.getPhone();
    }
    
    public int getCityId(){
        return this.customer.getCityId();
    }
    
    public String getCity(){
        return this.customer.getCity();
    }
    
    public int getCountryId(){
        return this.customer.getCountryId();
    }
    
    public String getCountry(){
        return this.customer.getCountry();
    }
    
}
