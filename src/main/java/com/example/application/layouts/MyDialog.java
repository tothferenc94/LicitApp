package com.example.application.layouts;

import com.example.application.data.Product;
import com.example.application.services.ProductService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class MyDialog extends Dialog {

    private Product product;
    private Span idSpan;
    private Span startPriceSpan;
    private Span actualPriceSpan;
    private Span bidSpan;
    private Span startDateSpan;
    private Span deadlineDateSpan;
    private VerticalLayout verticalLayout;
    private Button closeButton = new Button("Mégse");
    private ProductService productService;
    private Button bidButton = new Button("Licit");

    public MyDialog(Product product, ProductService productService) {
        this.setCloseOnOutsideClick(false);
        this.productService = productService;
        this.product = product;
        this.idSpan = new Span(product.getId().toString());
        this.startDateSpan = new Span(product.getStartDate());
        this.deadlineDateSpan = new Span(product.getDeadlineDate());
        this.bidSpan = new Span(product.getBid().toString());
        this.actualPriceSpan = new Span(product.getActualPrice().toString());
        this.startPriceSpan = new Span(product.getStartPrice().toString());

        refresh();
        add(createHeader());
        createLayout();
    }

    public void createLayout() {
        this.verticalLayout = new VerticalLayout(
                idSpan,
                startDateSpan,
                deadlineDateSpan,
                startPriceSpan,
                actualPriceSpan,
                bidSpan,
                bidButton
        );
        addActions();
        add(verticalLayout);
    }

    public HorizontalLayout createHeader() {
        HorizontalLayout header = new HorizontalLayout();
        header.setWidthFull();
        header.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        H1 title = new H1(product.getProductName());
        title.getStyle().set("font-weight", "bold");
        closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        header.add(title, closeButton);
        return header;
    }

    public void addActions() {
        this.bidButton.addClickListener(e -> {
            bid();
            productService.save(product);
        });

        closeButton.addClickListener(e -> {
            this.close();
        });
    }

    public void bid() {
        product.setActualPrice(product.getActualPrice() + product.getBid());
        refresh();
    }

    public void refresh() {
        this.actualPriceSpan.setText(product.getActualPrice().toString());
    }

}
