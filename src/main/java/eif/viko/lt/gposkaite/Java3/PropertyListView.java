package eif.viko.lt.gposkaite.Java3;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import eif.viko.lt.gposkaite.Java3.model.Property;
import eif.viko.lt.gposkaite.Java3.service.PropertyService;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Route(value = "properties", layout = MainLayout.class)
@PageTitle("Pagrindinis | Nekilnojamojo turto skelbimai")
public class PropertyListView extends VerticalLayout {
    private final PropertyService propertyService;
    private Grid<Property> grid = new Grid<>(Property.class);
    private TextField locationFilter = new TextField();
    private ComboBox<String> typeFilter = new ComboBox<>("Paskirtis");

    public PropertyListView(PropertyService propertyService) {
        this.propertyService = propertyService;
        setSizeFull();
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        configureFilters();
        addFiltersToLayout();
        updateList();
        configureGrid();
    }

    private void updateList() {
        List<Property> properties = propertyService.loadProperties();
        Stream<Property> filteredProperties = properties.stream();

        if (!locationFilter.isEmpty()) {
            filteredProperties = filteredProperties.filter(property ->
                    property.getLocation().toLowerCase().contains(locationFilter.getValue().toLowerCase()));
        }

        if (typeFilter.getValue() != null) {
            filteredProperties = filteredProperties.filter(property ->
                    property.getType().equals(typeFilter.getValue()));
        }

        grid.setItems(filteredProperties.collect(Collectors.toList()));
    }

    private void configureFilters() {
        locationFilter.setPlaceholder("Pagal miestą...");
        locationFilter.setClearButtonVisible(true);
        locationFilter.setWidth("50%");
        locationFilter.addValueChangeListener(e -> updateList());

        typeFilter.setItems("Gyvenamoji", "Komercinė", "Sklypas");
        typeFilter.setPlaceholder("Pagal paskirtį...");
        typeFilter.setClearButtonVisible(true);
        typeFilter.setWidth("50%");
        typeFilter.addValueChangeListener(e -> updateList());
    }

    private void addFiltersToLayout() {
        Button applyFilterButton = new Button("Filtruoti", e -> updateList());
        add(locationFilter, typeFilter, applyFilterButton);
    }

    private void configureGrid() {
        grid.addClassNames("property-grid");
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
        List<Property> allProperties = propertyService.loadProperties();
        grid.setItems(allProperties);
        add(grid);
    }
}
