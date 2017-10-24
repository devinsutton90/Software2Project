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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import software2project.Model.Customer;
import software2project.Model.SQLExecute;
import software2project.Software2Project;

public class AddCustomerController implements Initializable {

    private Software2Project software2Project;
    @FXML
    private TextField CustNameTextBox;
    @FXML
    private TextField AddressTextBox;
    @FXML
    private TextField Address2TextBox;
    @FXML
    private TextField CityNameTextBox;
    @FXML
    private TextField PostalCodeTextBox;
    @FXML
    private TextField CountryTextBox;
    @FXML
    private TextField PhoneNumberTextBox;
    @FXML
    private Button Cancel;
    @FXML
    private Button SaveBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void Cancel(ActionEvent event) throws Exception {
        Stage stage;

        //get reference to the button's stage         
        stage = (Stage) CustNameTextBox.getScene().getWindow();

        //load up OTHER FXML document
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AllCustomersController.class.getResource("AllCustomers.fxml"));

        //create a new scene with root and set the stage
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();

        AllCustomersController controller = loader.getController();
        controller.setSoftware2Project(this.software2Project);
    }

    @FXML
    private void AddSave(ActionEvent event) throws Exception {
              
        if (CustNameTextBox.getText().trim().isEmpty() || AddressTextBox.getText().trim().isEmpty()
                || PostalCodeTextBox.getText().trim().isEmpty() || PhoneNumberTextBox.getText().trim().isEmpty()
                || CityNameTextBox.getText().trim().isEmpty() || CountryTextBox.getText().trim().isEmpty()) {
            Runnable errorMessage = () -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Missing Required Fields");
                alert.setHeaderText("Missing Required Fields");
                alert.setContentText("All fields are requried except Address 2.");
                alert.show();
            };
            errorMessage.run();
            
        } else {
            try {
                SQLExecute execute = new SQLExecute();
                execute.addCustomer(createCustomer());
                Stage stage;

                //get reference to the button's stage         
                stage = (Stage) CustNameTextBox.getScene().getWindow();

                //load up OTHER FXML document
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(AllCustomersController.class.getResource("AllCustomers.fxml"));

                //create a new scene with root and set the stage
                Scene scene = new Scene(loader.load());
                stage.setScene(scene);
                stage.show();

                AllCustomersController controller = loader.getController();
                controller.setSoftware2Project(this.software2Project);
            } catch (SQLException se) {
                Runnable errorMessage = () -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("SQL ERROR");
                    alert.setHeaderText("SQL ERROR");
                    alert.setContentText("SQL ERROR");
                    alert.show();
                };
                errorMessage.run();
            }
        }
    }

    public Customer createCustomer() {
        Customer customer = new Customer();
        customer.setCustomerName(CustNameTextBox.getText());
        customer.setAddress(AddressTextBox.getText());
        customer.setAddress2(Address2TextBox.getText());
        customer.setPostalCode(PostalCodeTextBox.getText());
        customer.setPhone(PhoneNumberTextBox.getText());
        customer.setCity(CityNameTextBox.getText());
        customer.setCountry(CountryTextBox.getText());
        return customer;
    }

    public void setSoftware2Project(Software2Project software2Project) {
        this.software2Project = software2Project;
    }
}
