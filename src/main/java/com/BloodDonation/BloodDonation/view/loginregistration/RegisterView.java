package com.BloodDonation.BloodDonation.view.loginregistration;

import com.BloodDonation.BloodDonation.service.DonorService;
import com.BloodDonation.BloodDonation.view.loginregistration.components.RegistrationForm;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("RegisterDonor")
@PageTitle("Register")
public class RegisterView extends VerticalLayout {
    private final LoginForm login = new LoginForm();
    private final DonorService donorService;
    public RegisterView(DonorService donorService){
        this.donorService = donorService;

        RegistrationForm registrationForm = new RegistrationForm(donorService);
        add(registrationForm);
        setHeightFull();
        setWidthFull();
        getStyle().set("background-color", "#FCFFE7");
        registrationForm.getElement().getThemeList().add("dark");

        setHorizontalComponentAlignment(Alignment.CENTER, registrationForm);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }
}
