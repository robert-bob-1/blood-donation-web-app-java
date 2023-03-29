package com.BloodDonation.BloodDonation.view.loginregistration.components;

import com.BloodDonation.BloodDonation.entity.Donor;
import com.BloodDonation.BloodDonation.service.DonorService;
import com.BloodDonation.BloodDonation.service.UserService;
import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import java.util.stream.Stream;

public class RegistrationForm extends VerticalLayout {

    private H3 title;

    private TextField firstName;
    private TextField lastName;
    private TextField email;

    private PasswordField password;
    private PasswordField passwordConfirm;

    private TextField bloodType;

    private TextArea description;
    private TextField image;

    private Span errorMessageField;

    private Button submitButton;
    Notification notificationBox = new Notification();
    Dialog dialogBox = new Dialog();
    
//    private final UserService userService;
    private final DonorService donorService;

    public RegistrationForm(DonorService donorService) {
        this.donorService = donorService;
        getStyle().set("background-color", "#2B3467");
        title = new H3("Sign-up form");
        title.getStyle().set("padding", "5px 160px");
        firstName = new TextField("First name");
        firstName.getStyle().set("padding", "5px 130px");
        lastName = new TextField("Last name");
        lastName.getStyle().set("padding", "5px 130px");
        email = new TextField("Email to login with");
        email.getStyle().set("padding", "5px 130px");
        password = new PasswordField("Password");
        password.getStyle().set("padding", "5px 130px");
        passwordConfirm = new PasswordField("Confirm password");
        passwordConfirm.getStyle().set("padding", "5px 130px");
        bloodType = new TextField("Add your blood type");
        bloodType.getStyle().set("padding", "5px 130px");
//        description = new TextArea("Insert a description (optional)");
//        description.setWidth("220px");
//        description.setMaxHeight("200px");
//        description.getStyle().set("padding", "5px 130px");
//        image = new TextField("Insert link to profile picture (optional)");
//        image.getStyle().set("padding", "5px 130px");
        setRequiredIndicatorVisible(firstName, lastName, email, password, passwordConfirm);

        errorMessageField = new Span();

        notificationBox.setPosition(Notification.Position.TOP_CENTER);
        notificationBox.setDuration(2000);

        dialogBox.setHeaderTitle("Registration successful!");

        submitButton = new Button("Join the community");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        submitButton.getStyle().set("padding", "20px 161px");
        submitButton.addClickListener(e -> {
            if (firstName.getValue().equals("") || lastName.getValue().equals("")
                    || email.getValue().equals("")
                    || password.getValue().equals("")
                    || passwordConfirm.getValue().equals("")) {
                notificationBox.setText("Please complete all fields!");
                notificationBox.open();

            } else if ( !password.getValue().equals(passwordConfirm.getValue())){
                notificationBox.setText("Passwords don't match!");
                notificationBox.open();
            }
            else {
                Donor newDonor = new Donor(email.getValue(), password.getValue(), firstName.getValue(), lastName.getValue(), "donor", bloodType.getValue());
                newDonor = this.donorService.registerDonor(newDonor);
                if (newDonor != null){
                    notificationBox.setText("Welcome, " + firstName.getValue() + " " + lastName.getValue());
                    notificationBox.open();
                }
                else{
                    System.out.println("couldn't register user");
                }
            }
        });
        add(title, firstName, lastName, email, password, passwordConfirm, bloodType,
//                description, image,
                errorMessageField, submitButton);

        // Max width of the Form
        setMaxWidth("500px");
    }

    public PasswordField getPasswordField() {
        return password;
    }

    public PasswordField getPasswordConfirmField() {
        return passwordConfirm;
    }

    public Span getErrorMessageField() {
        return errorMessageField;
    }

    public Button getSubmitButton() {
        return submitButton;
    }

    private void setRequiredIndicatorVisible(HasValueAndElement<?, ?>... components) {
        Stream.of(components).forEach(comp -> comp.setRequiredIndicatorVisible(true));
    }
}