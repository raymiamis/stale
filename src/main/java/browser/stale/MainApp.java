package browser.stale;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    private TabPane tabPane;

    @Override
    public void start(Stage primaryStage) {
        tabPane = new TabPane();
        addNewTab("https://duckduckgo.com");

        Button newTabBtn = new Button("New Tab");
        newTabBtn.setOnAction(e -> addNewTab("https://duckduckgo.com"));

        ToolBar toolBar = new ToolBar(newTabBtn);

        BorderPane root = new BorderPane();
        root.setTop(toolBar);
        root.setCenter(tabPane);

        Scene scene = new Scene(root, 1000, 700);
        primaryStage.setTitle("Stale");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addNewTab(String url) {
        BrowserTab browserTab = new BrowserTab(url);
        tabPane.getTabs().add(browserTab);
        tabPane.getSelectionModel().select(browserTab);
    }

    public static void main(String[] args) {
        launch(args);
    }
}