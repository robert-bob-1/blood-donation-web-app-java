package com.BloodDonation.BloodDonation.view.loginregistration.userviews.doctor;

import com.BloodDonation.BloodDonation.entity.users.Doctor;
import com.BloodDonation.BloodDonation.service.AppointmentService;
import com.BloodDonation.BloodDonation.service.DoctorService;
import com.BloodDonation.BloodDonation.service.LocationService;
import com.BloodDonation.BloodDonation.view.loginregistration.LoginView;
import com.BloodDonation.BloodDonation.view.loginregistration.userviews.doctor.dialogs.LocationsAppointmentsDialog;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.UUID;

@Route("doctor/:userID/")
@PageTitle("DoctorMainView")
public class DoctorMainView extends VerticalLayout implements BeforeEnterObserver {
    //functionality
    private UUID userID;
    private Doctor doctor;
    private final DoctorService doctorService;
    private final LocationService locationService;
    private final AppointmentService appointmentService;

    //UI
    private final Button locationsAppointmentsButton = new Button("See locations and appointments");
    private LocationsAppointmentsDialog locationsAppointmentsDialog;

    public DoctorMainView(DoctorService doctorService, LocationService locationService, AppointmentService appointmentService) {
        this.doctorService = doctorService;
        this.locationService = locationService;
        this.appointmentService = appointmentService;

        getStyle().set("background-color", "#FCFFE7");

        locationsAppointmentsButton.addClickListener( l -> {
            locationsAppointmentsDialog = new LocationsAppointmentsDialog(doctor, locationService, appointmentService);
            locationsAppointmentsDialog.open();
        });



        add(locationsAppointmentsButton);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        userID = UUID.fromString(beforeEnterEvent.getRouteParameters().get("userID").orElse("nu s-a primit id"));
        doctor = doctorService.getDoctorById(userID);
    }
}
