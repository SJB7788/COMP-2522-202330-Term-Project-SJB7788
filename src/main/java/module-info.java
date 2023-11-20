module com.example.comp2522202330termprojectsjb7788 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.comp2522202330termprojectsjb7788 to javafx.fxml;
    exports com.example.comp2522202330termprojectsjb7788;
    exports com.example.comp2522202330termprojectsjb7788.interfaces;
    opens com.example.comp2522202330termprojectsjb7788.interfaces to javafx.fxml;
    exports com.example.comp2522202330termprojectsjb7788.enums;
    opens com.example.comp2522202330termprojectsjb7788.enums to javafx.fxml;
    exports com.example.comp2522202330termprojectsjb7788.Characters;
    opens com.example.comp2522202330termprojectsjb7788.Characters to javafx.fxml;
}