package com.BloodDonation.BloodDonation.view.loginregistration.userviews.donor.dialogs;

import com.BloodDonation.BloodDonation.entity.Location;
import com.BloodDonation.BloodDonation.service.LocationService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.List;

public class SeeLocationsDialog extends Dialog {
    private final LocationService locationService;

    //Form
    VerticalLayout form = new VerticalLayout();

    Grid<Location> grid = new Grid<>(Location.class, false);

    Notification notification = new Notification("");

    public SeeLocationsDialog(LocationService locationService) {
        this.locationService = locationService;

        grid.addColumn(Location::getName).setHeader("Location name");

        List<Location> locations = locationService.getLocations();
        grid.setItems(locations);

        form.add(grid);
        form.setWidth("600px");
        form.setAlignItems(FlexComponent.Alignment.STRETCH);
        form.setJustifyContentMode(FlexComponent.JustifyContentMode.AROUND);

        setHeaderTitle("All locations");
        add(form);

        Button closeDialog = new Button("Close", l -> this.close());

        getFooter().add(closeDialog);
    }

}