module ca.ucalgary.projectcpsc233demo3gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.junit.jupiter.api;
    requires org.testng;


    opens ca.ucalgary.projectcpsc233demo3gui to javafx.fxml;
    exports ca.ucalgary.projectcpsc233demo3gui;
}