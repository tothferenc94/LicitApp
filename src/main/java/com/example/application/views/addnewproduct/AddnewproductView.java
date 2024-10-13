package com.example.application.views.addnewproduct;

import com.example.application.data.Product;
import com.example.application.layouts.InputLayout;
import com.example.application.services.ProductService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("Add new product")
@Route(value = "add-new-product", layout = MainLayout.class)
public class AddnewproductView extends Composite<VerticalLayout> {

    private InputLayout inputLayout = new InputLayout();
    private Button saveButton = new Button("Mentés");

    @Autowired
    ProductService productService;

    public AddnewproductView() {
        saveButton.addClickListener(e -> saveProduct());
        getContent().add(inputLayout, saveButton);
    }

    private void saveProduct() {
        Product product = new Product();
        if(inputLayout.getBinder().validate().isOk()) {
            inputLayout.getBinder().writeBeanIfValid(product);
            product.setActualPrice(product.getStartPrice());
            productService.save(product);
            Notification.show("Sikeres mentés").addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        } else
            Notification.show("Sikertelen mentés").addThemeVariants(NotificationVariant.LUMO_ERROR);
    }

}
