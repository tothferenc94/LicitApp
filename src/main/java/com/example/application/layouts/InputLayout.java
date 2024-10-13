package com.example.application.layouts;

import com.example.application.data.Product;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.StringLengthValidator;
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

        formLayout.add(productNameField, startDateField,
                deadlineDateField, picUrlField, startPriceField, bidField);

        bindFields();

        add(title, formLayout);
    }

    private void bindFields() {
        binder.forField(productNameField)
                .withValidator(new StringLengthValidator("Túl rövid a terméknév!", 1, 100))
                .bind(Product::getProductName, Product::setProductName);
        binder.forField(startDateField)
                .withValidator(value -> value.matches("\\d{4}\\.(0[1-9]|1[0-2])\\.(0[1-9]|[12][0-9]|3[01])"), "A dátumformátum nem megfelelő! (éééé.hh.nn)")
                .bind(Product::getStartDate, Product::setStartDate);
        binder.forField(deadlineDateField)
                .withValidator(value -> value.matches("\\d{4}\\.(0[1-9]|1[0-2])\\.(0[1-9]|[12][0-9]|3[01])"), "A dátumformátum nem megfelelő! (éééé.hh.nn)")
                .bind(Product::getDeadlineDate, Product::setDeadlineDate);
        binder.bind(picUrlField, Product::getPicUrl, Product::setPicUrl);
        binder.forField(startPriceField)
                .withValidator(value -> value != null && value > 0.0, "A kezdőérték nem lehet 0!")
                .bind(Product::getStartPrice, Product::setStartPrice);
        binder.forField(bidField)
                .withValidator(value -> value != null && value > 0.0, "A licitlépcső nem lehet 0!")
                .bind(Product::getBid, Product::setBid);
    }

}
