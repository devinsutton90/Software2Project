package software2project.View_Controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import software2project.Model.Customer;
import software2project.Model.SQLExecute;
import software2project.Model.SessionInfo;
import software2project.Software2Project;

public class AddAppointmentCListController implements Initializable {

    private Software2Project software2Project;
    private SessionInfo session = SessionInfo.getUser();
    @FXML
    private TableView<Customer> CustomerListTableView;
    @FXML
    private TableColumn<Customer, Integer> CustomerIDColumn;
    @FXML
    private TableColumn<Customer, String> CustomerNameColumn;
    @FXML
    private TableColumn<Customer, String> PhoneColumn;
    @FXML
    private TableColumn<Customer, String> AddressColumn;
    @FXML
    private TableColumn<Customer, String> CityColumn;
    @FXML
    private TableColumn<Customer, String> PostalCodeColumn;
    @FXML
    private Button AddAppointmentForCustomerButton;
    @FXML
    private Button CancelAddAppointmentButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        CustomerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        AddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        CityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        PostalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        PhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
    }

    @FXML
    private void AddAppointmentForCustomerAction(ActionEvent event) throws Exception {
        if (CustomerListTableView.getSelectionModel().getSelectedIndex() != -1) {
            session.setSelectedCustomer(CustomerListTableView.getSelectionModel().getSelectedItem());
            Stage stage;
            stage = (Stage) CustomerListTableView.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AddAppointmentFormController.class.getResource("AddAppointmentForm.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.show();

            AddAppointmentFormController controller = loader.getController();
            controller.setSoftware2Project(this.software2Project);
        } else {
            Runnable errorMessage = () ->{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Customer Selected");
            alert.setHeaderText("No Customer Selected");
            alert.setContentText("No Customer Selected");
            alert.show();
            };
            
            errorMessage.run();
                    
        }
    }

    @FXML
    private void CancelAddAppointmentAction(ActionEvent event) throws Exception {
        Stage stage;
        stage = (Stage) CustomerListTableView.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AppointmentsByWeekController.class.getResource("AppointmentsByWeek.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("Appointment Tracking");
        stage.show();

        AppointmentsByWeekController controller = loader.getController();
        controller.setSoftware2Project(this.software2Project);
    }

    public void setSoftware2Project(Software2Project software2Project) throws Exception {
        this.software2Project = software2Project;
        session.clearListData();
        try {
            SQLExecute execute = new SQLExecute();
            execute.getCustomerListAll();
        } catch (SQLException se) {
            
        }
        CustomerListTableView.setItems(session.getCustomerList());
    }

}
