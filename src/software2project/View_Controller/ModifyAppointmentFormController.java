package software2project.View_Controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import software2project.Model.Appointment;
import software2project.Model.SQLExecute;
import software2project.Model.SessionInfo;
import software2project.Software2Project;

public class ModifyAppointmentFormController implements Initializable {
    private Software2Project software2Project;
    private SessionInfo session = SessionInfo.getUser();
    @FXML
    private TextField ContactTextBox;
    @FXML
    private TextField LocationTextBox;
    @FXML
    private TextField DescriptionTextBox;
    @FXML
    private TextField TitleTextBox;
    @FXML
    private TextField URLTextBox;
    @FXML
    private Button CancelButton;
    @FXML
    private Button SaveButton;
    @FXML
    private DatePicker DatePicker;
    @FXML
    private TextField StartHour;
    @FXML
    private TextField EndHour;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void CancelAction(ActionEvent event) throws Exception{
        Stage stage;
            stage = (Stage) CancelButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AppointmentsByWeekController.class.getResource("AppointmentsByWeek.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.setTitle("Appointment Tracking");
            stage.show();
            
            AppointmentsByWeekController controller = loader.getController();
            controller.setSoftware2Project(this.software2Project);
    }

    @FXML
    private void SaveAction(ActionEvent event) throws Exception{
        Appointment temp = new Appointment();
        temp.setCustomer(session.getSelectedCustomer());
        temp.setContact(session.getUsername());
        temp.setLocation(LocationTextBox.getText());
        temp.setDescription(DescriptionTextBox.getText());
        temp.setTitle(TitleTextBox.getText());
        temp.setURL(URLTextBox.getText());
        LocalTime startTime = LocalTime.of(Integer.parseInt(StartHour.getText(0, 2)), Integer.parseInt((StartHour.getText(3, 5))));
        ZonedDateTime start = ZonedDateTime.of(DatePicker.getValue().atTime(startTime), ZoneId.systemDefault());
        temp.setStartTime(start);
        LocalTime endTime = LocalTime.of(Integer.parseInt(EndHour.getText(0, 2)), Integer.parseInt((EndHour.getText(3, 5))));
        ZonedDateTime end = ZonedDateTime.of(DatePicker.getValue().atTime(endTime), ZoneId.systemDefault());
        temp.setEndTime(end);
        
        if (start.isBefore(ZonedDateTime.of(DatePicker.getValue().atTime(startTime).withHour(8).withMinute(0), ZoneId.systemDefault())) || 
                end.isAfter(ZonedDateTime.of(DatePicker.getValue().atTime(startTime).withHour(17).withMinute(0), ZoneId.systemDefault()))){
            Runnable errorMessage = () -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Time");
                alert.setHeaderText("Invalid Time");
                alert.setContentText("Scheduled appointment falls outside of normal business hours (8AM - 5PM).");
                alert.show();};
            errorMessage.run();
        } else{
               
        SQLExecute execute = new SQLExecute();
        if (execute.checkModifyAppointmentConflict(temp)){
            execute.modifyAppointment(temp);

            Stage stage;
            stage = (Stage) CancelButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AppointmentsByWeekController.class.getResource("AppointmentsByWeek.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.setTitle("Appointment Tracking");
            stage.show();

            AppointmentsByWeekController controller = loader.getController();
            controller.setSoftware2Project(this.software2Project);
        } else {
            Runnable errorMessage = () -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Schedule Conflict");
                alert.setHeaderText("Schedule Conflict");
                alert.setContentText("Schedule Conflict");
                alert.show();
            };
            errorMessage.run();
        }
        }
    }
    
    public void setSoftware2Project(Software2Project software2Project)throws Exception{
        this.software2Project = software2Project;
        ContactTextBox.setText(session.getSelectedAppointment().getContact());
        LocationTextBox.setText(session.getSelectedAppointment().getLocation());
        DescriptionTextBox.setText(session.getSelectedAppointment().getDescription());
        TitleTextBox.setText(session.getSelectedAppointment().getTitle());
        URLTextBox.setText(session.getSelectedAppointment().getURL());
        DatePicker.setValue(LocalDate.parse(session.getSelectedAppointment().getStartTimeUTC(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        StartHour.setText(session.getSelectedAppointment().getAppointmentStartTime());
        EndHour.setText(session.getSelectedAppointment().getAppointmentEndTime());
    }
}
