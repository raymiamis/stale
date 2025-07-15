module browser.stale {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;


    opens browser.stale to javafx.fxml;
    exports browser.stale;
}