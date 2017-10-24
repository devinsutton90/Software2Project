package software2project.View_Controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import software2project.Model.Customer;
import software2project.Model.SQLExecute;
import software2project.Model.SessionInfo;
import software2project.Software2Project;

public class ModifyCustomerController implements Initializable {
    private Software2Project software2Project;
    private SessionInfo user = SessionInfo.getUser();
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
    private CheckBox ActiveCheckbox;
    @FXML
    private Button Cancel;
    @FXML
    private Button SaveBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void Cancel(ActionEvent event) throws Exception{
        Stage stage; 
        
        //get reference to the button's stage         
        stage=(Stage) Cancel.getScene().getWindow();
        
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
    private void ModifySave(ActionEvent event) throws Exception{
        try{
            Customer c = user.getSelectedCustomer();
            c.setCustomerName(CustNameTextBox.getText());
            c.setAddress(AddressTextBox.getText());
            c.setAddress2(Address2TextBox.getText());
            c.setPostalCode(PostalCodeTextBox.getText());
            c.setPhone(PhoneNumberTextBox.getText());
            c.setCity(CityNameTextBox.getText());
            c.setCountry(CountryTextBox.getText());
            user.setSelectedCustomer(c);
            SQLExecute execute = new SQLExecute();         
            execute.modifyCustomer(c);
            Stage stage; 
        
            //get reference to the button's stage         
            stage=(Stage) CustNameTextBox.getScene().getWindow();

            //load up OTHER FXML document
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AllCustomersController.class.getResource("AllCustomers.fxml"));

            //create a new scene with root and set the stage
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.show();   

            AllCustomersController controller = loader.getController();
            controller.setSoftware2Project(this.software2Project);
        } catch (SQLException se){
        }
    }
    
    public void setSoftware2Project(Software2Project software2Project)throws Exception{
        this.software2Project = software2Project;
        CustNameTextBox.setText(user.getSelectedCustomer().getCustomerName());
        AddressTextBox.setText(user.getSelectedCustomer().getAddress());
        Address2TextBox.setText(user.getSelectedCustomer().getAddress2());
        CityNameTextBox.setText(user.getSelectedCustomer().getCity());
        PostalCodeTextBox.setText(user.getSelectedCustomer().getPostalCode());
        CountryTextBox.setText(user.getSelectedCustomer().getCountry());
        PhoneNumberTextBox.setText(user.getSelectedCustomer().getPhone());
        ActiveCheckbox.setSelected(true);
    }
    
    public void updateCustomer(){
        Customer c = user.getSelectedCustomer();
        c.setCustomerName(CustNameTextBox.getText());
        c.setAddress(AddressTextBox.getText());
        c.setAddress2(Address2TextBox.getText());
        c.setPostalCode(PostalCodeTextBox.getText());
        c.setPhone(PhoneNumberTextBox.getText());
        c.setCity(CityNameTextBox.getText());
        c.setCountry(CountryTextBox.getText());
        user.setSelectedCustomer(c);
    }
}
