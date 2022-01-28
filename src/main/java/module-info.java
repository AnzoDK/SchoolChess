module dk.anzodk.skakproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens dk.anzodk.skakproject to javafx.fxml;
    exports dk.anzodk.skakproject;
}
