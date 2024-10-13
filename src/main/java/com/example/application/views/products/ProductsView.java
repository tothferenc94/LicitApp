package com.example.application.views.products;

import com.example.application.data.Product;
import com.example.application.layouts.MyDialog;
import com.example.application.services.ProductService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import com.vaadin.flow.theme.lumo.LumoUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

@PageTitle("Products")
@Route(value = "", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@Uses(Icon.class)
public class ProductsView extends Div {

    private Grid<Product> grid;

    @Autowired
    ProductService productService;

    private Dialog dialog;

    public ProductsView() {
        setSizeFull();
        addClassNames("products-view");

        VerticalLayout layout = new VerticalLayout(createGrid());
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);

        grid.addItemClickListener(e -> {
            dialog = new MyDialog(e.getItem(), this.productService);
            dialog.open();
            dialog.addDialogCloseActionListener(f -> {
                refreshGrid();
            });
        });

        add(layout);
    }

    private Component createGrid() {
        grid = new Grid<>(Product.class, false);
        grid.addColumn("id").setAutoWidth(true);
        grid.addColumn("productName").setAutoWidth(true);
        grid.addColumn("startDate").setAutoWidth(true);
        grid.addColumn("deadlineDate").setAutoWidth(true);
        grid.addColumn("startPrice").setAutoWidth(true);
        grid.addColumn("actualPrice").setAutoWidth(true);
        grid.addColumn("bid").setAutoWidth(true);

        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.addClassNames(LumoUtility.Border.TOP, LumoUtility.BorderColor.CONTRAST_10);
        refreshGrid();
        return grid;
    }

    private void refreshGrid() {
        grid.setItems(query -> productService.list(PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query))).stream());
    }

}
