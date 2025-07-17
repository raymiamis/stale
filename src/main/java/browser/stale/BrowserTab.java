package browser.stale;

import javafx.concurrent.Worker;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.scene.layout.HBox;

public class BrowserTab extends Tab {
    public BrowserTab(String initialUrl) {
        setText("Tab");

        WebView webView = new WebView();
        WebEngine engine = webView.getEngine();

        TextField addressBar = new TextField(initialUrl);
        Button goButton = new Button("Go");
        goButton.setOnAction(e -> engine.load(addressBar.getText()));

        // Dark Mode Script vorbereiten
        String darkModeScript = """
            (function() {
                const originalMatchMedia = window.matchMedia;
                window.matchMedia = function(query) {
                    if (query === "(prefers-color-scheme: dark)") {
                        return {
                            matches: true,
                            media: query,
                            onchange: null,
                            addEventListener: function() {},
                            removeEventListener: function() {},
                            addListener: function() {},
                            removeListener: function() {}
                        };
                    }
                    return originalMatchMedia(query);
                };
            })();
        """;

        // Listener VOR dem Laden registrieren
        engine.getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                engine.executeScript(darkModeScript);
            }
        });

        engine.load(initialUrl);

        BorderPane layout = new BorderPane();
        layout.setTop(new HBox(addressBar, goButton));
        layout.setCenter(webView);

        setContent(layout);

        setOnClosed(e -> engine.load(null));
    }
}
