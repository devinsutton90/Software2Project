package software2project.View_Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import software2project.Model.Appointment;
import software2project.Model.SQLExecute;
import software2project.Model.SessionInfo;
import software2project.Software2Project;

public class ConsultantSchedulesReportController implements Initializable {
    private Software2Project software2Project;
    private SessionInfo session = SessionInfo.getUser();
    @FXML
    private TableView<Appointment> AppointmentsTableview;
    @FXML
    private TableColumn<Appointment, String> ConsultantColumn;
    @FXML
    private TableColumn<Appointment, String> CustomerNameColumn;
    @FXML
    private TableColumn<Appointment, Integer> AppointmentIDColumn;
    @FXML
    private TableColumn<Appointment, String> DateColumn;
    @FXML
    private TableColumn<Appointment, String> StartTimeColumn;
    @FXML
    private TableColumn<Appointment, String> EndTimeColumn;
    @FXML
    private TableColumn<Appointment, String> LocationColumn;
    @FXML
    private TableColumn<Appointment, String> DescriptionColumn;
    @FXML
    private TableColumn<Appointment, String> URLColumn;
    @FXML
    private Button cancelButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CustomerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        ConsultantColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
        AppointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        StartTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTimeCurrentZone"));
        EndTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endTimeCurrentZone"));
        LocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        DescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        URLColumn.setCellValueFactory(new PropertyValueFactory<>("URL"));
    }    

    @FXML
    private void cancel(ActionEvent event) throws Exception{
        Stage stage;        
        stage=(Stage) cancelButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AppointmentsByWeekController.class.getResource("AppointmentsByWeek.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("Appointment Tracking");
        stage.show();   

        AppointmentsByWeekController controller = loader.getController();
        controller.setSoftware2Project(this.software2Project);
    }
    
    public void setSoftware2Project(Software2Project software2Project) throws Exception{
        this.software2Project = software2Project;
        
        SQLExecute sql = new SQLExecute();
        sql.getConsultantSchedules();
        
        AppointmentsTableview.setItems(session.getAppointmentList());
    }
}
