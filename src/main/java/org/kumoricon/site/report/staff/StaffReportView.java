package org.kumoricon.site.report.staff;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import org.kumoricon.model.user.User;
import org.kumoricon.site.BaseView;
import org.kumoricon.site.fieldconverter.RoleToStringConverter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

@ViewScope
@SpringView(name = StaffReportView.VIEW_NAME)
public class StaffReportView extends BaseView implements View {
    public static final String VIEW_NAME = "staffReport";
    public static final String REQUIRED_RIGHT = "view_staff_report";

    @Autowired
    private StaffReportPresenter handler;

    private Button refresh = new Button("Refresh");
    private Grid dataGrid = new Grid("User List");

    @PostConstruct
    public void init() {
        addComponent(refresh);
        refresh.addClickListener((Button.ClickListener) clickEvent -> handler.showUserList(this));

        addComponent(dataGrid);
        handler.showUserList(this);
        dataGrid.setColumns("lastName", "firstName", "username", "phone", "role");
        dataGrid.getColumn("role").setConverter(new RoleToStringConverter());
        dataGrid.setEditorEnabled(false);
        dataGrid.setHeightMode(HeightMode.ROW);
        dataGrid.setSelectionMode(Grid.SelectionMode.NONE);
        dataGrid.setWidth(600, Unit.PIXELS);
        dataGrid.addStyleName("kumoHeaderOnlyHandPointer");
        setExpandRatio(dataGrid, .9f);
    }

    public void afterSuccessfulFetch(List<User> users) {
        dataGrid.setContainerDataSource(new BeanItemContainer<>(User.class, users));
        if (users.size() > 0) { dataGrid.setHeightByRows(users.size()); }
    }

    public String getRequiredRight() {
        return REQUIRED_RIGHT;
    }
}
