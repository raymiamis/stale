module browser.stale {
    requires javafx.controls;
    requires javafx.fxml;


    opens browser.stale to javafx.fxml;
    exports browser.stale;
}