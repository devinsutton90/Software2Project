package software2project.View_Controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import software2project.Model.Appointment;
import software2project.Model.SQLExecute;
import software2project.Model.SessionInfo;
import software2project.Software2Project;

public class AllAppointmentsController implements Initializable {
    private Software2Project software2Project;
    private SessionInfo user = SessionInfo.getUser();
    @FXML
    private MenuItem CloseMenuItem;
    @FXML
    private MenuItem MonthViewMenuItem;
    @FXML
    private MenuItem WeekViewMenuItem;
    @FXML
    private MenuItem ViewAllCustomerMenuItem;
    @FXML
    private MenuItem ViewAllAppointmentsMenuItem;
    @FXML
    private MenuItem AppointmentTypeReportMenuItem;
    @FXML
    private MenuItem ScheduleByConsultantReportMenuItem;
    @FXML
    private MenuItem AllUpcomingAppointmentsMenuItem;
    @FXML
    private MenuItem AllHistoricAppointmentsMenuItem;
    @FXML
    private TableView<Appointment> AppointmentsTableview;
    @FXML
    private TableColumn<Appointment, String> ConsultantColumn;
    @FXML
    private TableColumn<Appointment, String> CustomerNameColumn;
    @FXML
    private TableColumn<Appointment, Integer> AppointmentIDColumn;
    @FXML
    private TableColumn<Appointment, String> Datecolumn;
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
    private Button DeleteAppointmentButton;
    @FXML
    private Button ModifyAppointmentButton;
    @FXML
    private Button AddAppointmentButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CustomerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        ConsultantColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
        AppointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        Datecolumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        StartTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTimeCurrentZone"));
        EndTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endTimeCurrentZone"));
        LocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        DescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        URLColumn.setCellValueFactory(new PropertyValueFactory<>("URL"));
    }    

    @FXML
    private void CloseApplication(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void MonthViewMenuItemSelect(ActionEvent event) throws Exception{
        Stage stage;        
        stage=(Stage) AppointmentsTableview.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AppointmentsByMonthController.class.getResource("AppointmentsByMonth.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("Appointment Tracking");
        stage.show();   

        AppointmentsByMonthController controller = loader.getController();
        controller.setSoftware2Project(this.software2Project);
    }

    @FXML
    private void WeekViewMenuItemSelect(ActionEvent event) throws Exception{
        Stage stage;        
        stage=(Stage) AppointmentsTableview.getScene().getWindow();
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
    private void ViewAllCustomerMenuItemSelect(ActionEvent event) throws Exception{
        Stage stage;        
        stage=(Stage) AppointmentsTableview.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AllCustomersController.class.getResource("AllCustomers.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("Appointment Tracking");
        stage.show();   

        AllCustomersController controller = loader.getController();
        controller.setSoftware2Project(this.software2Project);
    }

    @FXML
    private void ViewAllAppointmentsMenuItemAction(ActionEvent event) throws Exception{
        Stage stage;        
        stage=(Stage) AppointmentsTableview.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AllAppointmentsController.class.getResource("AppointmentsAll.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("Appointment Tracking");
        stage.show();   

        AllAppointmentsController controller = loader.getController();
        controller.setSoftware2Project(this.software2Project);
    }

    @FXML
    private void AppointmentTypeReportMenuItemSelect(ActionEvent event) throws Exception{
        Stage stage;        
        stage=(Stage) AppointmentsTableview.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AppointmentTypeReportController.class.getResource("AppointmentTypeReport.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("Appointment Tracking");
        stage.show();   

        AppointmentTypeReportController controller = loader.getController();
        controller.setSoftware2Project(this.software2Project);
    }

    @FXML
    private void ScheduleByConsultantReportMenuItemSelect(ActionEvent event) throws Exception{
        Stage stage;        
        stage=(Stage) AppointmentsTableview.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ConsultantSchedulesReportController.class.getResource("ConsultantSchedulesReport.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("Appointment Tracking");
        stage.show();   

        ConsultantSchedulesReportController controller = loader.getController();
        controller.setSoftware2Project(this.software2Project);
    }

    @FXML
    private void AllUpcomingAppointmentsMenuItemSelect(ActionEvent event) throws Exception{
        Stage stage;        
        stage=(Stage) AppointmentsTableview.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AllUpcomingAppointmentsReportController.class.getResource("AllUpcomingAppointmentsReport.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("Appointment Tracking");
        stage.show();   

        AllUpcomingAppointmentsReportController controller = loader.getController();
        controller.setSoftware2Project(this.software2Project);
    }

    @FXML
    private void AllHistoricAppointmentsMenuItemSelect(ActionEvent event) throws Exception{
        Stage stage;        
        stage=(Stage) AppointmentsTableview.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AllHistoricalAppointmentsReportController.class.getResource("AllHistoricalAppointmentsReport.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("Appointment Tracking");
        stage.show();   

        AllHistoricalAppointmentsReportController controller = loader.getController();
        controller.setSoftware2Project(this.software2Project);
    }

    @FXML
    private void DeleteAppointmentAction(ActionEvent event) {
        if(AppointmentsTableview.getSelectionModel().getSelectedIndex() != -1){
            Runnable prompt = () -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirm Appointment Deletion");
                alert.setHeaderText("Confirm Appointment Deletion");
                alert.setContentText("Proceed with deleting selected Appointment?");
                Optional<ButtonType> result = alert.showAndWait();
            
            if (result.get() == ButtonType.OK){
                user.setSelectedAppointment(AppointmentsTableview.getSelectionModel().getSelectedItem());
            
                try{
                    SQLExecute execute = new SQLExecute();
                    execute.deleteAppointment(user.getSelectedAppointment());
                    
                    Stage stage; 
  
                    stage=(Stage) AppointmentsTableview.getScene().getWindow();

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(AllAppointmentsController.class.getResource("AllAppointments.fxml"));

                    Scene scene = new Scene(loader.load());
                    stage.setScene(scene);
                    stage.show();   

                    AllAppointmentsController controller = loader.getController();
                    controller.setSoftware2Project(this.software2Project);
                } catch (Exception se){
                }
            }};
            prompt.run();
            
             
        } else {
            Runnable errorMessage = () -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("No Appointment Selected");
                alert.setHeaderText("No Appointment Selected");
                alert.setContentText("No Appointment Selected");
                alert.show();};
            errorMessage.run();
        }
    }

    @FXML
    private void ModifyAppointmentAction(ActionEvent event) throws Exception{
        if (AppointmentsTableview.getSelectionModel().getSelectedIndex() != -1) {
            user.setSelectedAppointment(AppointmentsTableview.getSelectionModel().getSelectedItem());
            Stage stage;

            stage = (Stage) AppointmentsTableview.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ModifyAppointmentFormController.class.getResource("ModifyAppointmentForm.fxml"));

            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.show();

            ModifyAppointmentFormController controller = loader.getController();
            controller.setSoftware2Project(this.software2Project);
        } else {
            Runnable errorMessage = () -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("No Appointment Selected");
                alert.setHeaderText("No Appointment Selected");
                alert.setContentText("No Appointment Selected");
                alert.show();};
            errorMessage.run();
        }
    }

    @FXML
    private void AddAppointmentAction(ActionEvent event) throws Exception{
        Stage stage;        
        stage=(Stage) AppointmentsTableview.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AddAppointmentCListController.class.getResource("AddAppointmentCList.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();   

        AddAppointmentCListController controller = loader.getController();
        controller.setSoftware2Project(this.software2Project);
    }
    
    public void setSoftware2Project(Software2Project software2Project) throws Exception{
        this.software2Project = software2Project;
        try{
            SQLExecute execute = new SQLExecute();
            execute.getAppointmentListAll();
        } catch (SQLException se){
            //error
        }
        AppointmentsTableview.setItems(user.getAppointmentList());
    }
}
