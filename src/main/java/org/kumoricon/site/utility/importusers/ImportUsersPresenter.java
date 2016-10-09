package org.kumoricon.site.utility.importusers;

import com.vaadin.ui.Button;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("requiest")
public class ImportUsersPresenter {
    private static final Logger log = LoggerFactory.getLogger(ImportUsersPresenter.class);

    private ImportUsersView view;

    public Button.ClickListener getClickListener() { return new ImportUsersClickListener(); }

    private class ImportUsersClickListener implements Button.ClickListener {
        public ImportUsersClickListener() {

        }

        public void buttonClick(Button.ClickEvent event) {

        }
    }

    public ImportUsersPresenter() {}
    public ImportUsersView getView() { return view; }
    public void setView(ImportUsersView view) { this.view = view; }
}
