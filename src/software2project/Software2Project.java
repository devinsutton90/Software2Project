package software2project;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import software2project.View_Controller.LoginScreenController;

public class Software2Project extends Application {
    private Stage primaryStage;
    
    @Override
    public void stop(){
        System.exit(0);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Login");
        
         try {
            // Load login screen
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Software2Project.class.getResource("View_Controller/LoginScreen.fxml"));
            
            // Show the scene containing the login screen.
            Scene scene = new Scene(loader.load());
            primaryStage.setScene(scene);
            primaryStage.show();
            
            // Load controller for login screen
            LoginScreenController controller = loader.getController();
            controller.setSoftware2Project(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
