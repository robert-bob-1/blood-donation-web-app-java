package com.BloodDonation.BloodDonation.view.loginregistration.userviews.donor;


import com.BloodDonation.BloodDonation.entity.users.Donor;
import com.BloodDonation.BloodDonation.service.AppointmentService;
import com.BloodDonation.BloodDonation.service.DonorService;
import com.BloodDonation.BloodDonation.service.LocationService;
import com.BloodDonation.BloodDonation.view.loginregistration.LoginView;
import com.BloodDonation.BloodDonation.view.loginregistration.userviews.donor.dialogs.EditAccountDialog;
import com.BloodDonation.BloodDonation.view.loginregistration.userviews.donor.dialogs.MakeAppointmentDialog;
import com.BloodDonation.BloodDonation.view.loginregistration.userviews.donor.dialogs.SeeLocationsDialog;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;

import java.util.UUID;

@Route("donor/:userID/")
@PageTitle("DonorMainView")
public class DonorMainView extends VerticalLayout implements BeforeEnterObserver {
    //functionality
    private UUID userID;
    private Donor donor;
    private final DonorService donorService;
    private final LocationService locationService;
    private final AppointmentService appointmentService;

    //UI
    private final Button editAccountButton = new Button("Edit account");
    private EditAccountDialog editAccountDialog;

    private final Button deleteAccountButton = new Button("Delete account");

    private final Button seeLocationsButton = new Button("See locations");
    private SeeLocationsDialog seeLocationsDialog;

    private final Button makeAppointmentButton = new Button("Make an appointment");
    private MakeAppointmentDialog makeAppointmentDialog;

    public DonorMainView(DonorService donorService, LocationService locationService, AppointmentService appointmentService) {
        this.donorService = donorService;
        this.locationService = locationService;
        this.appointmentService = appointmentService;

        getStyle().set("background-color", "#FCFFE7");

        editAccountButton.addClickListener( e -> {
            editAccountDialog = new EditAccountDialog(donor, donorService);
            editAccountDialog.open();
            editAccountDialog.clearAll();
        });

        deleteAccountButton.addClickListener( l -> {
            donorService.deleteDonor(donor.uuid);
            UI.getCurrent().navigate(LoginView.class);
        });

        seeLocationsButton.addClickListener( l -> {
            seeLocationsDialog = new SeeLocationsDialog(locationService);
            seeLocationsDialog.open();
        });

        makeAppointmentButton.addClickListener( l -> {
            makeAppointmentDialog = new MakeAppointmentDialog(donor, locationService, appointmentService);
            makeAppointmentDialog.open();
        });

        add(editAccountButton, deleteAccountButton, seeLocationsButton, makeAppointmentButton);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        userID = UUID.fromString(beforeEnterEvent.getRouteParameters().get("userID").orElse("nu s-a primit id"));
        donor = donorService.getDonor(userID);
    }
}
