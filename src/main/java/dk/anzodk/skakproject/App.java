package dk.anzodk.skakproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.stage.WindowEvent;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static Stage _stage;

    @Override
    public void start(Stage stage) throws IOException {
        //scene = new Scene(loadFXML("primary"), 823, 556);
        scene = new Scene(loadFXML("ChessLobby"));
        App._stage = stage;
        stage.setScene(scene);
        stage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, eh -> {
        //ConnectionManager.INSTANCE.socketReadThread.interrupt();
        if(ConnectionManager.INSTANCE != null)
        {
            ConnectionManager.INSTANCE.Send("CLOSE");
            ConnectionManager.INSTANCE.KillClient();
            ConnectionManager.INSTANCE.KillConnection(); 
        }
        stage.close();
        });
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
        _stage.sizeToScene();
        
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}