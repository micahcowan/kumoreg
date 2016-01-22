package org.kumoricon.view.order;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import org.kumoricon.model.attendee.Attendee;
import org.kumoricon.presenter.order.OrderPresenter;
import org.kumoricon.view.attendee.AttendeeCheckinDetailForm;
import org.kumoricon.view.attendee.AttendeeDetailForm;

public class AttendeeWindow extends Window {

    AttendeeDetailForm attendeeDetailForm = new AttendeeCheckinDetailForm();
    Button save = new Button("Save");
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete");

    private OrderPresenter handler;

    public AttendeeWindow(OrderPresenter orderPresenter) {
        super("Attendee");
        this.handler = orderPresenter;
        setIcon(FontAwesome.USER);
        center();

        setWidth(1100, Unit.PIXELS);

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setMargin(true);
        verticalLayout.setSpacing(true);

        verticalLayout.addComponent(attendeeDetailForm);

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSpacing(true);
        horizontalLayout.addComponent(save);
        horizontalLayout.addComponent(cancel);
        horizontalLayout.addComponent(delete);

        save.setTabIndex(20);
        cancel.setTabIndex(21);

        save.addClickListener((Button.ClickListener) clickEvent -> {
            // handler.attendeeIsValid(attendee);
            handler.addAttendeeToOrder(attendeeDetailForm.getAttendee());
            close();
        });

        cancel.addClickListener((Button.ClickListener) clickEvent -> {
            close();
        });

        delete.addClickListener((Button.ClickListener) clickEvent -> {
            handler.removeAttendeeFromOrder(attendeeDetailForm.getAttendee());
            close();
        });

        verticalLayout.addComponent(horizontalLayout);
        setContent(verticalLayout);

        attendeeDetailForm.selectFirstName();
    }

    public Attendee getAttendee() {
        return attendeeDetailForm.getAttendee();
    }


    public OrderPresenter getHandler() { return handler; }
    public void setHandler(OrderPresenter handler) { this.handler = handler; }

    public AttendeeDetailForm getDetailForm() {
        return attendeeDetailForm;
    }
}