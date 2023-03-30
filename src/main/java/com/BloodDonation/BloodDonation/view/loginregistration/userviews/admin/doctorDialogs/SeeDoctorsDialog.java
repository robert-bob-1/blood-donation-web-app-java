package com.BloodDonation.BloodDonation.view.loginregistration.userviews.admin.doctorDialogs;

import com.BloodDonation.BloodDonation.entity.Admin;
import com.BloodDonation.BloodDonation.entity.Doctor;
import com.BloodDonation.BloodDonation.service.AdminService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.util.List;

public class SeeDoctorsDialog  extends Dialog {
    private final Admin admin;
    private final AdminService adminService;
    //Form
    VerticalLayout form = new VerticalLayout();

    Grid<Doctor> grid = new Grid<>(Doctor.class, false);

    Button addDoctorButton = new Button("Add doctor");

    Notification notification = new Notification("");

    public SeeDoctorsDialog(Admin admin, AdminService adminService) {
        this.admin = admin;
        this.adminService = adminService;

        grid.addColumn(Doctor::getFirstName).setHeader("First name");
        grid.addColumn(Doctor::getLastName).setHeader("Last name");
        grid.addColumn(Doctor::getEmail).setHeader("Email");
        grid.addColumn(Doctor::getPassword).setHeader("Password");

        List<Doctor> doctors = adminService.getDoctors();
        grid.setItems(doctors);

        form.add(grid, addDoctorButton);
        form.setWidth("600px");
        form.setAlignItems(FlexComponent.Alignment.STRETCH);
        form.setJustifyContentMode(FlexComponent.JustifyContentMode.AROUND);

        setHeaderTitle("Add doctor");
        add(form);

        Button closeDialog = new Button("Close", l -> this.close());

        getFooter().add(closeDialog);
    }

}
