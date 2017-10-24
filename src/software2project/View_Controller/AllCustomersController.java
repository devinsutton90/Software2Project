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
import software2project.Model.Customer;
import software2project.Model.SQLExecute;
import software2project.Model.SessionInfo;
import software2project.Software2Project;

public class AllCustomersController implements Initializable {

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
    private TableView<Customer> CustomersTableview;
    @FXML
    private TableColumn<Customer, Integer> CustomerIDColumn;
    @FXML
    private TableColumn<Customer, String> CustomerNameColumn;
    @FXML
    private TableColumn<Customer, String> AddressColumn;
    @FXML
    private TableColumn<Customer, String> Address2Column;
    @FXML
    private TableColumn<Customer, String> CityColumn;
    @FXML
    private TableColumn<Customer, String> PostalCodeColumn;
    @FXML
    private TableColumn<Customer, String> CountryColumn;
    @FXML
    private TableColumn<Customer, String> PhoneColumn;
    @FXML
    private Button DeleteCustomerButton;
    @FXML
    private Button ModifyCustomerButton;
    @FXML
    private Button AddCustomerButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        CustomerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        AddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        Address2Column.setCellValueFactory(new PropertyValueFactory<>("address2"));
        CityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        PostalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        CountryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        PhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
    }

    @FXML
    private void CloseApplication(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void MonthViewMenuItemSelect(ActionEvent event) throws Exception {
        Stage stage;
        stage = (Stage) CustomersTableview.getScene().getWindow();
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
    private void WeekViewMenuItemSelect(ActionEvent event) throws Exception {
        Stage stage;
        stage = (Stage) CustomersTableview.getScene().getWindow();
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
    private void ViewAllCustomerMenuItemSelect(ActionEvent event) throws Exception {
        Stage stage;
        stage = (Stage) CustomersTableview.getScene().getWindow();
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
    private void ViewAllAppointmentsMenuItemAction(ActionEvent event) throws Exception {
        Stage stage;
        stage = (Stage) CustomersTableview.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AllAppointmentsController.class.getResource("AllAppointments.fxml"));
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
        stage=(Stage) CustomersTableview.getScene().getWindow();
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
    private void ScheduleByConsultantReportMenuItemSelect(ActionEvent event) throws Exception {
        Stage stage;
        stage = (Stage) CustomersTableview.getScene().getWindow();
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
    private void AllUpcomingAppointmentsMenuItemSelect(ActionEvent event) throws Exception {
        Stage stage;
        stage = (Stage) CustomersTableview.getScene().getWindow();
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
    private void AllHistoricAppointmentsMenuItemSelect(ActionEvent event) throws Exception {
        Stage stage;
        stage = (Stage) CustomersTableview.getScene().getWindow();
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
    private void DeleteCustomerAction(ActionEvent event) {
        if (CustomersTableview.getSelectionModel().getSelectedIndex() != -1) {
                Runnable prompt = () ->{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirm Customer Deletion");
                alert.setHeaderText("Confirm Customer Deletion");
                alert.setContentText("Proceed with deleting selected Customer?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    user.setSelectedCustomer(CustomersTableview.getSelectionModel().getSelectedItem());

                    try {
                        SQLExecute execute = new SQLExecute();
                        execute.deleteCustomer(user.getSelectedCustomer());

                        Stage stage;

                        stage = (Stage) CustomersTableview.getScene().getWindow();

                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(AllCustomersController.class.getResource("allCustomers.fxml"));

                        Scene scene = new Scene(loader.load());
                        stage.setScene(scene);
                        stage.show();

                        AllCustomersController controller = loader.getController();
                        controller.setSoftware2Project(this.software2Project);
                    } catch (Exception se) {
                        Alert alert1 = new Alert(Alert.AlertType.ERROR);
                        alert1.setTitle("SQL ERROR");
                        alert1.setHeaderText("SQL ERROR");
                        alert1.setContentText("SQL ERROR");
                        alert1.show();
                    }
                
            }};
                prompt.run();
        } else {
            Runnable errorMessage = () -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("No Customer Selected");
                alert.setHeaderText("No Customer Selected");
                alert.setContentText("No Customer Selected");
                alert.show();};
            errorMessage.run();
        }
    }

    @FXML
    private void ModifyCustomerAction(ActionEvent event) throws Exception {
        if (CustomersTableview.getSelectionModel().getSelectedIndex() != -1) {
            user.setSelectedCustomer(CustomersTableview.getSelectionModel().getSelectedItem());
            Stage stage;

            stage = (Stage) CustomersTableview.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ModifyCustomerController.class.getResource("ModifyCustomer.fxml"));

            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.show();

            ModifyCustomerController controller = loader.getController();
            controller.setSoftware2Project(this.software2Project);
        } else {
            Runnable errorMessage = () -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("No Customer Selected");
                alert.setHeaderText("No Customer Selected");
                alert.setContentText("No Customer Selected");
                alert.show();};
            errorMessage.run();
        }
    }

    @FXML
    private void AddCustomerAction(ActionEvent event) throws Exception {
        Stage stage;

        //get reference to the button's stage         
        stage = (Stage) CustomersTableview.getScene().getWindow();

        //load up OTHER FXML document
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AddCustomerController.class.getResource("AddCustomer.fxml"));

        //create a new scene with root and set the stage
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();

        AddCustomerController controller = loader.getController();
        controller.setSoftware2Project(this.software2Project);
    }

    public void setSoftware2Project(Software2Project software2Project) throws Exception {
        user.clearListData();
        try {
            SQLExecute execute = new SQLExecute();
            execute.getCustomerListAll();
        } catch (SQLException se) {
            Runnable errorMessage = () -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("SQL ERROR");
                alert.setHeaderText("SQL ERROR");
                alert.setContentText("SQL ERROR");
                alert.show();};
            errorMessage.run();
        }
        CustomersTableview.setItems(user.getCustomerList());
    }
}
