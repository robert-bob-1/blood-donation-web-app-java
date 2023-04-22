package com.BloodDonation.BloodDonation.view.loginregistration.userviews.donor.dialogs;

import com.BloodDonation.BloodDonation.entity.users.Donor;
import com.BloodDonation.BloodDonation.service.DonorService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;


public class EditAccountDialog extends Dialog {
    private final Donor donor;
    private final DonorService donorService;

    VerticalLayout form = new VerticalLayout();
    TextField firstName = new TextField("First Name");
    TextField lastName = new TextField("Last Name");
    TextField email = new TextField("Email");
    TextField password = new TextField("Password");
    TextField bloodType = new TextField("Blood Type");

    Button updateDonorButton = new Button("Update details");

    Notification notification = new Notification("");

    public EditAccountDialog(Donor donor, DonorService donorService) {
        this.donor = donor;
        this.donorService = donorService;

        firstName.setValue(donor.firstName);
        lastName.setValue(donor.lastName);
        email.setValue(donor.email);
        password.setValue(donor.password);

        updateDonorButton.addClickListener(e -> {
            Donor newDonor = new Donor(email.getValue(),
                    password.getValue(), firstName.getValue(), lastName.getValue(),
                    bloodType.getValue(), "donor");
//            newDonor.uuid = donor.uuid;
            donorService.updateDonor(newDonor);
            notification.setText("Donor updated succesfully!");
            clearAll();

            notification.open();
        });

        form.add(email, password, firstName, lastName, bloodType, updateDonorButton);
        form.setWidth("600px");
        form.setAlignItems(FlexComponent.Alignment.STRETCH);
        form.setJustifyContentMode(FlexComponent.JustifyContentMode.AROUND);

        setHeaderTitle("Update donor");
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
