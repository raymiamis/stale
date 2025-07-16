package browser.stale;

import javafx.scene.control.Tab;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;

public class BrowserTab extends Tab {
    public BrowserTab(String initialUrl) {
        setText("Tab");

        WebView webView = new WebView();
        WebEngine engine = webView.getEngine();

        TextField addressBar = new TextField(initialUrl);
        Button goButton = new Button("Go");
        goButton.setOnAction(e -> engine.load(addressBar.getText()));

        engine.load(initialUrl);

        BorderPane layout = new BorderPane();
        layout.setTop(new javafx.scene.layout.HBox(addressBar, goButton));
        layout.setCenter(webView);

        setContent(layout);

        setOnClosed(e -> engine.load(null));
    }
}