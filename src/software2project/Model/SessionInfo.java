package software2project.Model;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SessionInfo {
    private String username = null;
    private SessionInfo() {}
    private ArrayList<Customer> customerData = new ArrayList<>();
    private ArrayList<Appointment> appointmentData = new ArrayList<>();
    private ObservableList<Customer> customerList = FXCollections.observableArrayList(customerData);
    private ObservableList<Appointment> appointmentList = FXCollections.observableArrayList(appointmentData);
    private Customer selectedCustomer = new Customer();
    private Appointment selectedAppointment = new Appointment();
    
    private static final SessionInfo user = new SessionInfo();
    
    public static SessionInfo getUser(){
        return user;
    }
    
    public void setUsername(String u){
        username = u;
    }
    
    public String getUsername(){
        return this.username;
    }
    
    public ObservableList<Appointment> getAppointmentList(){
        return this.appointmentList;
    }
    
    public ObservableList<Customer> getCustomerList(){
        return this.customerList;
    }
    
    public void clearListData(){
        customerList.clear();
        appointmentList.clear();
    }
    
    public Customer getSelectedCustomer(){
        return this.selectedCustomer;
    }
    
    public void setSelectedCustomer(Customer c){
        this.selectedCustomer = c;
    }
    
    public Appointment getSelectedAppointment(){
        return this.selectedAppointment;
    }
    
    public void setSelectedAppointment(Appointment a){
        this.selectedAppointment = a;
    }
}
