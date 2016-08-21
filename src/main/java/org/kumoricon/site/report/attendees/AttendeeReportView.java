package org.kumoricon.site.report.attendees;

import com.vaadin.navigator.View;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import org.kumoricon.site.report.ReportView;
import org.kumoricon.site.BaseView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@ViewScope
@SpringView(name = AttendeeReportView.VIEW_NAME)
public class AttendeeReportView extends BaseView implements View, ReportView {
    public static final String VIEW_NAME = "attendeeReport";
    public static final String REQUIRED_RIGHT = "view_attendance_report";
    @Autowired
    private AttendeeReportPresenter handler;

    private Button refresh = new Button("Refresh");
    private Label data = new Label();

    @PostConstruct
    public void init() {
        addComponent(refresh);
        refresh.addClickListener((Button.ClickListener) clickEvent -> handler.fetchReportData(this));
        addComponent(data);
        data.setContentMode(ContentMode.HTML);
        handler.fetchReportData(this);
        setExpandRatio(data, 1f);
        data.setSizeFull();
        data.setWidthUndefined();
    }


    @Override
    public void afterSuccessfulFetch(String data) {
        this.data.setValue(data);
    }

    public String getRequiredRight() {
        return REQUIRED_RIGHT;
    }
}
