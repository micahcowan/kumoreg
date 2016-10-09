package org.kumoricon.site.utility.importusers;


import com.vaadin.navigator.View;
import com.vaadin.server.Sizeable;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import org.kumoricon.site.BaseView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@ViewScope
@SpringView(name = ImportUsersView.VIEW_NAME)
public class ImportUsersView extends BaseView implements View {
    public static final String VIEW_NAME = "importAttendees";
    public static final String REQUIRED_RIGHT = "import_user_data";

    @Autowired
    private ImportUsersPresenter handler;

    private Label instructions = new Label("Copy/paste a whitespace-separated list of staff names to generate usernames");
    private TextArea pasteArea = new TextArea();
    private Button submit = new Button("Import");

    @PostConstruct
    public void init() {
        handler.setView(this);
        setSizeFull();

        addComponent(instructions);

        submit.addClickListener(handler.getClickListener());
        addComponent(submit);

        pasteArea.setSizeFull();
        pasteArea.setWidth(600, Sizeable.Unit.PIXELS);
        addComponent(pasteArea);
    }

    public void setHandler(ImportUsersPresenter presenter) {
        this.handler = presenter;
    }

    public String getRequiredRight() { return REQUIRED_RIGHT; }
}