package software2project.View_Controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import software2project.Model.ConnectionManager;
import software2project.Model.SQLStatementFactory;
import software2project.Model.SessionInfo;
import software2project.Software2Project;

public class AppointmentTypeReportController implements Initializable {
    private Software2Project software2Project;
    private ConnectionManager connectionManager = ConnectionManager.getInstance();
    private SessionInfo session = SessionInfo.getUser();
    @FXML
    private TableView<TypeCountReport> WeekAppointmentTableView;
    @FXML
    private TableColumn<TypeCountReport, String> TypeColumn;
    @FXML
    private TableColumn<TypeCountReport, Integer> CountColumn;
    @FXML
    private Button CancelButton;
    
    public class TypeCountReport{
        public SimpleStringProperty type;
        public SimpleIntegerProperty count;
        
        TypeCountReport(){
            type = new SimpleStringProperty(null);
            count = new SimpleIntegerProperty(0);
        }
        
        public void setType(String t){
            this.type.set(t);
        }
        
        public void setCount(int i){
            this.count.set(i);
        }
        
        public String getType(){
            return type.get();
        }
        
        public Integer getCount(){
            return count.get();
        }
        
    }
    
    private ArrayList<TypeCountReport> list = new ArrayList();
    private ObservableList<TypeCountReport> report = FXCollections.observableArrayList(list);
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        CountColumn.setCellValueFactory(new PropertyValueFactory<>("count"));
    }    

    @FXML
    public void CancelAction(ActionEvent event) throws Exception{
        Stage stage;        
        stage=(Stage) CancelButton.getScene().getWindow();
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
        SQLStatementFactory sql = new SQLStatementFactory();
        try{
        Statement reportRun = connectionManager.getConnection().createStatement();
        ResultSet results = reportRun.executeQuery(sql.getAppointmentTypeSQLStatement());
        
        while (results.next()){
            TypeCountReport temp = new TypeCountReport();
            
            temp.setType(results.getString(1));
            temp.setCount(results.getInt(2));
            report.add(temp);
        }
        } catch (SQLException se){
            //error
        }
        WeekAppointmentTableView.setItems(report);
    }
}
