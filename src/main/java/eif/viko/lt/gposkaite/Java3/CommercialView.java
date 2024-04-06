package eif.viko.lt.gposkaite.Java3;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import eif.viko.lt.gposkaite.Java3.service.PropertyService;
import eif.viko.lt.gposkaite.Java3.model.Property;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.router.Route;

import java.util.List;
import java.util.stream.Collectors;

    @Route(value = "commercial", layout = MainLayout.class)
    @PageTitle("Komerciniai objektai | Nekilnojamojo turto skelbimai")
    public class CommercialView extends VerticalLayout {
        private final PropertyService propertyService;

        private Grid<Property> grid = new Grid<>(Property.class);


        public CommercialView(PropertyService propertyService) {
            this.propertyService = propertyService;
            setSizeFull();
            setDefaultHorizontalComponentAlignment(Alignment.CENTER);
            configureGrid();
        }

        private void configureGrid() {
            add(new H2("Komerciniai objektai"));
            grid.setSizeFull();
            grid.setColumns("type", "location", "price", "size");
            grid.getColumnByKey("type").setHeader("Paskirtis");
            grid.getColumnByKey("location").setHeader("Miestas");
            grid.getColumnByKey("price").setHeader("Kaina");
            grid.getColumnByKey("size").setHeader("Plotas");
            grid.addColumn(new ComponentRenderer<>(property -> {
                Image image = new Image(property.getImageUrl(), "Property image");
                image.setHeight("100px");
                return image;
            })).setHeader("Nuotrauka");
            grid.getColumns().forEach(col -> col.setAutoWidth(true));
            List<Property> commercialProperties = propertyService.loadProperties().stream()
                    .filter(p -> p.getType().equals("Komercinė"))
                    .collect(Collectors.toList());
            grid.setItems(commercialProperties);
            add(grid);
        }
    }