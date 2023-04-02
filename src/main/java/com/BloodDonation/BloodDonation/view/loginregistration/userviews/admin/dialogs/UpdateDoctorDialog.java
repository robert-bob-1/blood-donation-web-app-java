package com.BloodDonation.BloodDonation.view.loginregistration.userviews.admin.dialogs;

import com.BloodDonation.BloodDonation.entity.users.Admin;
import com.BloodDonation.BloodDonation.entity.users.Doctor;
import com.BloodDonation.BloodDonation.service.AdminService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class UpdateDoctorDialog extends Dialog {
    private final Admin admin;
    private final AdminService adminService;
    //Form
    VerticalLayout form = new VerticalLayout();
    TextField firstName = new TextField("First Name");
    TextField lastName = new TextField("Last Name");
    TextField email = new TextField("Email of doctor to update");
    TextField password = new TextField("Password");

    Button updateDoctorButton = new Button("Add doctor");

    Notification notification = new Notification("");

    public UpdateDoctorDialog(Admin admin, AdminService adminService) {
        this.admin = admin;
        this.adminService = adminService;

        updateDoctorButton.addClickListener(e -> {
            Doctor newDoctor = new Doctor(email.getValue(),
                    password.getValue(), firstName.getValue(), lastName.getValue(), "doctor");

            adminService.updateDoctor(email.getValue(), newDoctor);
            notification.setText("Doctor updated succesfully!");
            clearAll();

            notification.open();
//            notification.setDuration(2000);
        });

        form.add(email, password, firstName, lastName, updateDoctorButton);
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
