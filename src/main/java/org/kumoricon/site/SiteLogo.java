package org.kumoricon.site;

import com.vaadin.server.ThemeResource;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.VaadinSessionScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.kumoricon.model.user.User;
import org.springframework.context.annotation.Scope;

@SpringComponent
@Scope("session")
@VaadinSessionScope
public class SiteLogo extends VerticalLayout {
    private Label username = new Label("");

    public SiteLogo() {
        setWidth("100%");
        ThemeResource resource = new ThemeResource("img/logo.png");
        Image logo = new Image(null, resource);
        logo.setHeight("143px");

        addComponent(logo);
        setComponentAlignment(logo, Alignment.TOP_CENTER);
        setHeight(175, Unit.PIXELS);
        setMargin(false);
        setSpacing(true);

        username.setSizeUndefined();
        addComponent(username);
        setComponentAlignment(username, Alignment.BOTTOM_CENTER);
    }

    public void setUser(User user) {
        if (user != null) {
            this.username.setValue(user.getUsername());
        } else {
            this.username.setValue(null);
        }
    }
}
