package com.BloodDonation.BloodDonation.view.loginregistration;

import com.BloodDonation.BloodDonation.entity.User;
import com.BloodDonation.BloodDonation.entity.UserType;
import com.BloodDonation.BloodDonation.mapper.UserMapper;
import com.BloodDonation.BloodDonation.service.UserService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Optional;

@Route(value = "")
@PageTitle("Login")
public class LoginView extends VerticalLayout {
    private final LoginForm login = new LoginForm();
    private final Button registerButton = new Button("Register a new account");

    private final UserService userService;
    private final UserMapper userMapper = new UserMapper();

    public LoginView(UserService userServiceImpl){
        this.userService = userServiceImpl;
        getStyle().set("background-color", "#FCFFE7");

        addClassName("login-view");
        setSizeFull();
        setAlignItems(FlexComponent.Alignment.CENTER);
        setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        VerticalLayout header = new VerticalLayout();
        H1 title = new H1("Blood donation");
        title.getStyle().set("color", "#2B3467");
        header.add(title);
        header.setAlignItems(FlexComponent.Alignment.CENTER);

        login.addLoginListener(e -> {
            User user = userService.loginUser(e.getUsername(), e.getPassword());
            if(user == null)
                login.setError(true);
            else{
                switch (user.userType) {
                    case ("admin"):
                        System.out.println("Admin logged in");
                        UI.getCurrent().navigate(HomeView.class, new RouteParameters("userID", String.valueOf(id)));
                        break;
                    case ("doctor") -> System.out.println("Doctor logged in");
                    case ("donor") -> System.out.println("Donor logged in");

//                   UI.getCurrent().navigate(HomeView.class, new RouteParameters("userID", String.valueOf(id)));
                    default -> System.out.println("not right");
                }
            }
        });

        registerButton.addClickListener(e -> {
            UI.getCurrent().navigate(RegisterView.class);
        });

        registerButton.setWidth("19%");
        registerButton.getStyle().set("background-color","blue");
        registerButton.getStyle().set("color","white");

        this.add(header, login, registerButton);

        setAlignItems(FlexComponent.Alignment.CENTER);
        setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
    }
}
