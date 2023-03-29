package com.BloodDonation.BloodDonation.view.loginregistration.userviews.admin;


import com.BloodDonation.BloodDonation.entity.Admin;
import com.BloodDonation.BloodDonation.service.AdminService;
import com.BloodDonation.BloodDonation.view.loginregistration.userviews.admin.doctorDialogs.DeleteDoctorDialog;
import com.BloodDonation.BloodDonation.view.loginregistration.userviews.admin.doctorDialogs.RegisterDoctorDialog;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.UUID;

@Route("user/:userID/")
@PageTitle("AdminMainView")
public class AdminMainView extends VerticalLayout implements BeforeEnterObserver {
    //functionality
    private UUID userID;
    private Admin admin;
    private final AdminService adminService;

    //UI
    private final Button registerDoctorButton = new Button("Register doctor");
    private RegisterDoctorDialog registerDoctorDialog;
    private final Button deleteDoctorButton = new Button("Delete doctor");
    private DeleteDoctorDialog deleteDoctorDialog;
    private final Button updateDoctorButton = new Button("Update doctor");
    private final Button seeDoctorsButton = new Button("See doctors");

    //

    public AdminMainView(AdminService adminService) {
        this.adminService = adminService;

        getStyle().set("background-color", "#FCFFE7");

        registerDoctorButton.addClickListener( e -> {
            registerDoctorDialog = new RegisterDoctorDialog(admin, adminService);
            registerDoctorDialog.open();
            registerDoctorDialog.clearAll();
        });

        deleteDoctorButton.addClickListener( e -> {
            deleteDoctorDialog = new DeleteDoctorDialog(admin, adminService);
            deleteDoctorDialog.open();
            deleteDoctorDialog.clearAll();
        });

        add(registerDoctorButton, deleteDoctorButton, updateDoctorButton);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        userID = UUID.fromString(beforeEnterEvent.getRouteParameters().get("userID").orElse("nu s-a primit id"));
        admin = adminService.getAdmin(userID);
    }
}
