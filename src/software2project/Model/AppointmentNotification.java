package software2project.Model;

import java.util.TimerTask;
import javafx.application.Platform;
import javafx.scene.control.Alert;

public class AppointmentNotification extends TimerTask{
    private Appointment a = null;
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
    public AppointmentNotification(Appointment a){
        this.a = a;
        Platform.runLater(() -> {
        alert.setTitle("Appointment Reminder");
        alert.setHeaderText("Appointment Reminder");
        alert.setContentText("You have an upcoming appointment with " + a.getCustomerName()
        + " at " + a.getAppointmentStartTime());
        });
    }
    
    @Override
    public void run(){
        Platform.runLater(() -> {
            alert.showAndWait();});
        
        }   
    }

