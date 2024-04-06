package eif.viko.lt.gposkaite.Java3;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Nav;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.RouterLink;

@CssImport("./styles/styles.css")
public class MainLayout extends AppLayout {
    public MainLayout() {
        createHeader();
    }

    private void createHeader() {
        H1 logo = new H1("Nekilnojamojo turto skelbimai");
        logo.addClassNames("text-l", "m-m");



        RouterLink propertiesLink = new RouterLink("Pagrindinis", PropertyListView.class);
        propertiesLink.addClassNames("menu-link");

        RouterLink residentialLink = new RouterLink("Gyvenamosios paskirties ", ResidentialView.class);
        residentialLink.addClassNames("menu-link");

        RouterLink commercialLink = new RouterLink("Komerciniai objektai", CommercialView.class);
        commercialLink.addClassNames("menu-link");

        RouterLink landLink = new RouterLink("Žemės sklypai", LandView.class);
        landLink.addClassNames("menu-link");

        Nav nav = new Nav();
        nav.addClassNames("header-nav");
        nav.add(propertiesLink, residentialLink, commercialLink, landLink);

        HorizontalLayout header = new HorizontalLayout(logo, nav);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidth("100%");
        header.getStyle().set("background-color", "#800080");
        header.addClassNames("py-m", "px-l");

        addToNavbar(header);
    }
}