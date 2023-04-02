package com.BloodDonation.BloodDonation.view.loginregistration.userviews.doctor.dialogs;

import com.BloodDonation.BloodDonation.entity.Appointment;
import com.BloodDonation.BloodDonation.entity.Location;
import com.BloodDonation.BloodDonation.entity.users.Doctor;
import com.BloodDonation.BloodDonation.service.AppointmentService;
import com.BloodDonation.BloodDonation.service.LocationService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class LocationsAppointmentsDialog extends Dialog {
    private final Doctor doctor;
    private final LocationService locationService;
    private final AppointmentService appointmentService;

    //Form
    VerticalLayout form = new VerticalLayout();

    Grid<Location> locationGrid = new Grid<>(Location.class, false);
    Button seeAppointmentsButton = new Button("See appointments at selected location");

    List<Appointment> appointments = new ArrayList<>();
    Grid<Appointment> appointmentGrid = new Grid<>(Appointment.class, false);
    Button validateAppointmentButton = new Button("Validate appointment");


    Notification notification = new Notification("");

    public LocationsAppointmentsDialog(Doctor doctor, LocationService locationService, AppointmentService appointmentService) {
        this.doctor = doctor;
        this.locationService = locationService;
        this.appointmentService = appointmentService;

        locationGrid.addColumn(Location::getName).setHeader("Location name");

        List<Location> locations = locationService.getLocations();
        locationGrid.setItems(locations);


        seeAppointmentsButton.addClickListener( l -> {
            appointments = appointmentService.getAppointmentsByLocation(locationGrid.asSingleSelect().getValue());
            if (appointments != null) {
                appointmentGrid.setItems(appointments);
            }

            locationGrid.deselectAll();
        });

        appointmentGrid.addColumn(Appointment::getDatetime).setHeader("Appointment date");
        appointmentGrid.setItems(appointments);

        validateAppointmentButton.addClickListener( l -> {
            Appointment selectedAppointment = appointmentGrid.asSingleSelect().getValue();
            if (selectedAppointment.getDoctorId() == null)
                appointmentService.validateAppointment(selectedAppointment, doctor);
        });

        form.add(locationGrid, seeAppointmentsButton,
                appointmentGrid, validateAppointmentButton);
        form.setWidth("600px");
        form.setAlignItems(FlexComponent.Alignment.STRETCH);
        form.setJustifyContentMode(FlexComponent.JustifyContentMode.AROUND);

        setHeaderTitle("Appointments");
        add(form);

        Button closeDialog = new Button("Close", l -> this.close());

        getFooter().add(closeDialog);

    }
}
