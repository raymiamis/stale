package browser.stale;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Objects;

public class MainApp extends Application {

    private TabPane tabPane;
    private Tab plusTab;

    @Override
    public void start(Stage primaryStage) {
        tabPane = new TabPane();

        plusTab = createPlusTab();
        tabPane.getTabs().add(plusTab);

        Tab firstTab = addNewTab();
        tabPane.getSelectionModel().select(firstTab);

        tabPane.getSelectionModel().selectedItemProperty().addListener((obs, oldTab, newTab) -> {
            if (newTab == plusTab) {
                Tab newBrowserTab = addNewTab();

                tabPane.getTabs().remove(plusTab);
                tabPane.getTabs().add(plusTab);

                tabPane.getSelectionModel().select(newBrowserTab);

                updateLastVisibleTabStyle();
            }
        });

        BorderPane root = new BorderPane();
        root.setCenter(tabPane);

        Scene scene = new Scene(root, 1000, 700);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());

        primaryStage.setTitle("Stale");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateLastVisibleTabStyle() {
        for (Tab tab : tabPane.getTabs()) {
            tab.getStyleClass().remove("last-visible");
        }

        for (int i = tabPane.getTabs().size() - 1; i >= 0; i--) {
            Tab tab = tabPane.getTabs().get(i);
            if (tab != plusTab) {
                tab.getStyleClass().add("last-visible");
                break;
            }
        }
    }

    private Tab addNewTab() {
        BrowserTab browserTab = new BrowserTab("https://duckduckgo.com");
        tabPane.getTabs().add(tabPane.getTabs().size() - 1, browserTab);
        updateLastVisibleTabStyle();
        return browserTab;
    }

    private Tab createPlusTab() {
        Tab tab = new Tab("+");
        tab.setClosable(false);
        return tab;
    }

    public static void main(String[] args) {
        launch(args);
    }
}