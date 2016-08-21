package org.kumoricon.site.report.till;

import com.vaadin.navigator.View;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import org.kumoricon.site.BaseView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringView(name = org.kumoricon.site.report.till.TillReportView.VIEW_NAME)
public class TillReportView extends BaseView implements View {
    public static final String VIEW_NAME = "tillReport";
    public static final String REQUIRED_RIGHT = "view_till_report";

    private Button refresh = new Button("Show Report");
    private Label data = new Label();

    @Autowired
    private TillReportPresenter handler;

    @PostConstruct
    public void init() {
        setSizeFull();

        addComponent(refresh);
        addComponent(data);
        data.setContentMode(ContentMode.PREFORMATTED);
        setExpandRatio(data, 1f);
        data.setSizeFull();

        refresh.addClickListener((Button.ClickListener) clickEvent -> { refresh.setCaption("Refresh"); handler.showAllTills(this); });
    }

    public void showData(String report) { data.setValue(report); }

    public String getRequiredRight() { return REQUIRED_RIGHT; }
}