package com.BloodDonation.BloodDonation.view.loginregistration.userviews.admin.dialogs;

import com.BloodDonation.BloodDonation.entity.users.Admin;
import com.BloodDonation.BloodDonation.service.AdminService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class DeleteDoctorDialog extends Dialog {
    private final Admin admin;
    private final AdminService adminService;

    //Form
    VerticalLayout form = new VerticalLayout();
    TextField email = new TextField("Email");

    Button deleteDoctorButton = new Button("delete doctor");

    Notification notification = new Notification("");


    public DeleteDoctorDialog(Admin admin, AdminService adminService) {
        this.admin = admin;
        this.adminService = adminService;

        deleteDoctorButton.addClickListener(e -> {
            if (adminService.deleteDoctorByEmail(email.getValue()) != null) {
                notification.setText("Doctor deleted succesfully!");
                clearAll();
            } else {
                notification.setText("Doctor couldn't be created!");
            }
            notification.open();
            notification.setDuration(2000);
        });

        form.add(email, deleteDoctorButton);
        form.setWidth("600px");
        form.setAlignItems(FlexComponent.Alignment.STRETCH);
        form.setJustifyContentMode(FlexComponent.JustifyContentMode.AROUND);

        setHeaderTitle("Delete doctor");
        add(form);

        Button closeDialog = new Button("Close", l -> this.close());

        getFooter().add(closeDialog);
    }

    public void clearAll(){
        email.clear();
    }
}
