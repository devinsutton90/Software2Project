package software2project.View_Controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Timer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import software2project.Model.AppointmentNotification;
import software2project.Model.ConnectionManager;
import software2project.Model.SQLExecute;
import software2project.Model.SQLStatementFactory;
import software2project.Model.SessionInfo;
import software2project.Software2Project;

public class LoginScreenController implements Initializable {

    private Software2Project software2Project;
    private final SQLStatementFactory sql = new SQLStatementFactory();
    private final ConnectionManager connectionManager = ConnectionManager.getInstance();
    @FXML
    private TextField usernameTextBox;
    @FXML
    private PasswordField passwordTextBox;
    @FXML
    private Button loginButton;
    @FXML
    private Label LoginTitle;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label passLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String a = Locale.getDefault().getLanguage();
        switch (a) {
                case "fr":   
                    LoginTitle.setText("Journal de Rendez");
                    usernameLabel.setText("Nom d'utilisateur: ");
                    passLabel.setText("Mot de passe: ");
                    loginButton.setText("Soumettre");
                    break;
                case "en":    
                    LoginTitle.setText("Appointment Tracker");
                    usernameLabel.setText("Username: ");
                    passLabel.setText("Password: ");
                    loginButton.setText("Login");
                    break;
                case "es":
                    LoginTitle.setText("Seguimiento de Citas");
                    usernameLabel.setText("Nombre de usuario: ");
                    passLabel.setText("Contraseña: ");
                    loginButton.setText("Enviar");
        }
    }

    @FXML
    private void loginSubmit(ActionEvent event) throws Exception {
        try {

            Statement statement = connectionManager.getConnection().createStatement();

            String SQLLogin = sql.getLoginSQLString(usernameTextBox.getText(),
                    passwordTextBox.getText());

            ResultSet results = statement.executeQuery(SQLLogin);

            if (results.next()) {
                SessionInfo user = SessionInfo.getUser();
                user.setUsername(usernameTextBox.getText());
                String loginLog = ("Successful login by " + usernameTextBox.getText() + " at " + LocalTime.now().toString());
                File log = new File("userlogs.txt");
                log.createNewFile(); // 
                FileOutputStream stream = new FileOutputStream(log, true);
                try (BufferedWriter bufferedStream = new BufferedWriter(new OutputStreamWriter(stream))) {
                    bufferedStream.newLine();
                    bufferedStream.write(loginLog);
                }
                
                //Create appointment reminder threads
                SQLExecute sqlExecute = new SQLExecute();
                sqlExecute.getUserTodaySchedule();
                
                user.getAppointmentList().forEach((a) -> {
                    
                    ZonedDateTime alertTime = a.getStartZDT().minusMinutes(15);
                    ZonedDateTime nowTime = ZonedDateTime.now();
                    
                    long wait = nowTime.until(alertTime, ChronoUnit.MILLIS);
                    
                    System.out.println(wait);
                    
                    if(wait <= 0){
                        new AppointmentNotification(a).run();
                    } else {
                        Timer timer = new Timer();
                        Platform.runLater(() -> timer.schedule(new AppointmentNotification(a), wait));
                    }
                });
                

                Stage stage;
                stage = (Stage) loginButton.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(AppointmentsByWeekController.class.getResource("AppointmentsByWeek.fxml"));
                Scene scene = new Scene(loader.load());
                stage.setScene(scene);
                stage.setTitle("Appointment Tracking");
                stage.show();

                AppointmentsByWeekController controller = loader.getController();
                controller.setSoftware2Project(this.software2Project);
                

            } else {
                String loginLog = ("Failed login by " + usernameTextBox.getText() + " at " + LocalTime.now().toString());
                File log = new File("userlogs.txt");
                log.createNewFile(); // 
                FileOutputStream stream = new FileOutputStream(log, true);
                try (BufferedWriter bufferedStream = new BufferedWriter(new OutputStreamWriter(stream))) {
                    bufferedStream.newLine();
                    bufferedStream.write(loginLog);
                }
                
                String a = Locale.getDefault().getLanguage();
                if(a.matches("fr")){
                    Runnable errorMessage = () ->{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erreur");
                        alert.setHeaderText("Erreur");
                        alert.setContentText("Identifiant out Mot de Passe Sont Incorrects.");
                        alert.showAndWait();};
                    errorMessage.run();
                } else if (a.matches("en")){
                    Runnable errorMessage = () ->{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Invalid Username and Password");
                        alert.setHeaderText("Invalid Username and Password");
                        alert.setContentText("Incorrect Username or Password.");
                        alert.showAndWait();};
                    errorMessage.run();
                } else if (a.matches("es")){
                    Runnable errorMessage = () ->{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Falta");
                        alert.setHeaderText("Falta");
                        alert.setContentText("Nombre de usuario o contraseña incorrecta");
                        alert.showAndWait();};
                    errorMessage.run();
                }
            }

        } catch (SQLException se) {
        }
    }

    public void setSoftware2Project(Software2Project software2Project) {
        this.software2Project = software2Project;
    }
}
