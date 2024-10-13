package com.example.application.layouts;

import com.example.application.data.Product;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class InputLayout extends VerticalLayout {

    private FormLayout formLayout = new FormLayout();
    Binder<Product> binder = new Binder<>(Product.class);
    TextField productNameField = new TextField("Terméknév");
    TextField startDateField = new TextField("Kezdő dátum (yyyy-mm-dd)");
    TextField deadlineDateField = new TextField("Határidő (yyyy-mm-dd)");
    TextField picUrlField = new TextField("Kép URL");
    NumberField startPriceField = new NumberField("Kezdőérték");
    NumberField bidField = new NumberField("Licitlépcső");

    public InputLayout() {
        createLayout();
    }

    private void createLayout() {
        H3 title = new H3("Új termék létrehozása");

        startPriceField.setPlaceholder("0.00");
        bidField.setPlaceholder("0.00");

        formLayout.add(title, productNameField, startDateField,
                deadlineDateField, picUrlField, startPriceField, bidField);

        bindFields();

        add(formLayout);
    }

    private void bindFields() {
        binder.bind(productNameField, Product::getProductName, Product::setProductName);
        binder.bind(startDateField, Product::getStartDate, Product::setStartDate);
        binder.bind(deadlineDateField, Product::getDeadlineDate, Product::setDeadlineDate);
        binder.bind(picUrlField, Product::getPicUrl, Product::setPicUrl);
        binder.bind(startPriceField, Product::getStartPrice, Product::setStartPrice);
        binder.bind(bidField, Product::getBid, Product::setBid);
    }

}
