module edu.espol.proyectohote {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens edu.espol.proyectohote to javafx.fxml;
    exports edu.espol.proyectohote;
}