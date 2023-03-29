package com.BloodDonation.BloodDonation.view.loginregistration.userviews.admin.doctorDialogs;

import com.BloodDonation.BloodDonation.entity.Admin;
import com.BloodDonation.BloodDonation.entity.Doctor;
import com.BloodDonation.BloodDonation.service.AdminService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;


public class RegisterDoctorDialog extends Dialog {
    private final Admin admin;
    private final AdminService adminService;
    //Form
    VerticalLayout form = new VerticalLayout();
    TextField firstName = new TextField("First Name");
    TextField lastName = new TextField("Last Name");
    TextField email = new TextField("Email");
    TextField password = new TextField("Password");

    Button addDoctorButton = new Button("Add doctor");

    Notification notification = new Notification("");

    public RegisterDoctorDialog(Admin admin, AdminService adminService) {
        this.admin = admin;
        this.adminService = adminService;

        addDoctorButton.addClickListener(e -> {
            Doctor newDoctor = new Doctor(email.getValue(),
                    password.getValue(), firstName.getValue(), lastName.getValue(), "doctor");

            if (adminService.registerDoctor(newDoctor) != null) {
                notification.setText("Doctor added succesfully!");
                clearAll();
            } else {
                notification.setText("Doctor couldn't be created!");
            }
            notification.open();
//            notification.setDuration(2000);
        });

        form.add(firstName, lastName, email, password, addDoctorButton);
        form.setWidth("600px");
        form.setAlignItems(FlexComponent.Alignment.STRETCH);
        form.setJustifyContentMode(FlexComponent.JustifyContentMode.AROUND);

        setHeaderTitle("Add doctor");
        add(form);

        Button closeDialog = new Button("Close", l -> this.close());

        getFooter().add(closeDialog);
    }

    public void clearAll(){
        firstName.clear();
        lastName.clear();
        email.clear();
        password.clear();
    }
}
