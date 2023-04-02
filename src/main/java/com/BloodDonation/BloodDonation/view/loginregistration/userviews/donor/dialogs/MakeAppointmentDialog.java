package com.BloodDonation.BloodDonation.view.loginregistration.userviews.donor.dialogs;

import com.BloodDonation.BloodDonation.entity.users.Donor;
import com.BloodDonation.BloodDonation.entity.Location;
import com.BloodDonation.BloodDonation.service.AppointmentService;
import com.BloodDonation.BloodDonation.service.LocationService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.util.List;


public class MakeAppointmentDialog extends Dialog {
    private final Donor donor;
    private final LocationService locationService;
    private final AppointmentService appointmentService;

    //Form
    VerticalLayout form = new VerticalLayout();

    TextField datetime = new TextField("Insert date and time in format 'yyyy-MM-dd HH:mm:ss'");

    Grid<Location> grid = new Grid<>(Location.class, false);

    Button addAppointmentButton = new Button("Add appointment");

    Notification notification = new Notification("");


    public MakeAppointmentDialog(Donor donor, LocationService locationService, AppointmentService appointmentService) {
        this.donor = donor;
        this.locationService = locationService;
        this.appointmentService = appointmentService;

        grid.addColumn(Location::getName).setHeader("Location name");

        List<Location> locations = locationService.getLocations();
        grid.setItems(locations);

        addAppointmentButton.addClickListener( l -> {
            appointmentService.createAppointment(donor, grid.asSingleSelect().getValue(), datetime.getValue());
            datetime.clear();
            grid.deselectAll();
        });

        form.add(datetime, grid, addAppointmentButton);
        form.setWidth("600px");
        form.setAlignItems(FlexComponent.Alignment.STRETCH);
        form.setJustifyContentMode(FlexComponent.JustifyContentMode.AROUND);

        setHeaderTitle("All locations");
        add(form);

        Button closeDialog = new Button("Close", l -> this.close());

        getFooter().add(closeDialog);

    }

}
