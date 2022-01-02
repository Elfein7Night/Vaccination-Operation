package project.view.worker;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import project.model.entities.Appointment;
import project.model.entities.Citizen;
import project.model.util.UtilMethods;
import project.view.MainView;
import project.view.PrettyButton;
import project.view.SimpleMainMenu;
import project.view.ViewUtils;

import java.util.Arrays;
import java.util.Collection;

public class WorkerView extends BorderPane {

    private final SimpleMainMenu mainMenu;

    public WorkerView() {
        PrettyButton homeButton = new PrettyButton("project/images/home.png");
        homeButton.setSize(50);
        homeButton.setAlignment(Pos.TOP_LEFT);
        homeButton.setOnMouseClicked(click -> showMainMenu());
        setTop(new HBox(homeButton));

        mainMenu = new SimpleMainMenu();
        setBackground(MainView.DEFAULT_BLANK_BG);
        showMainMenu();
    }

    public void appointmentsSetOnClick(EventHandler<MouseEvent> eventHandler) {
        mainMenu.appointmentsSetOnClick(eventHandler);
    }

    public void vaccinationsSetOnClick(EventHandler<MouseEvent> eventHandler) {
        mainMenu.vaccinationsSetOnClick(eventHandler);
    }

    public void showMainMenu() {
        setCenter(mainMenu);
    }

    @SuppressWarnings("unchecked")
    public void showAppointmentsPage(Collection<Appointment> appointments) {
        TableView<Appointment> tableView = new TableView<>();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Set Columns
        TableColumn<Appointment, String> ID = new TableColumn<>(MainView.TableColumns.ID.toString());
        TableColumn<Appointment, String> dateTime = new TableColumn<>(MainView.TableColumns.DATETIME.toString());
        TableColumn<Appointment, String> citizen = new TableColumn<>(MainView.TableColumns.CITIZEN.toString());
        TableColumn<Appointment, String> citizenID = new TableColumn<>(MainView.TableColumns.ID.toString());
        TableColumn<Appointment, String> citizenName = new TableColumn<>(MainView.TableColumns.NAME.toString());
        TableColumn<Appointment, String> citizenPhone = new TableColumn<>(MainView.TableColumns.PHONE_NUMBER.toString());


        citizen.getColumns().addAll(citizenID, citizenName, citizenPhone);
        tableView.getColumns().addAll(ID, dateTime, citizen);

        Arrays.asList(
                ID,
                citizen,
                citizenID,
                citizenName,
                citizenPhone,
                dateTime
        ).forEach(ViewUtils::centerColumn);

        // generate column values
        ID.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        citizenID.setCellValueFactory(new PropertyValueFactory<>("citizenId"));
        citizenName.setCellValueFactory(param -> {
            Citizen c = param.getValue().getCitizenByCitizenId();
            return new SimpleStringProperty(c.getFirstName() + " " + c.getLastName());
        });
        citizenPhone.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getCitizenByCitizenId().getPhoneNum()));
        dateTime.setCellValueFactory(param -> new SimpleStringProperty(UtilMethods.getDateString(param.getValue().getDate())));

        HBox.setMargin(tableView, MainView.TABLE_INSETS);
        tableView.setMinWidth(500);
        ViewUtils.unHighlightTable(tableView);

        tableView.getItems().addAll(appointments);

        VBox layout = new VBox(tableView);
        layout.setFillWidth(false);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(15));
        setCenter(layout);
    }
}
