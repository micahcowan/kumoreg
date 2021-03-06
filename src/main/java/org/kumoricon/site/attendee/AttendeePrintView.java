package org.kumoricon.site.attendee;

import org.kumoricon.model.attendee.Attendee;

import java.util.List;

public interface AttendeePrintView {
    void showPrintBadgeWindow(List<Attendee> attendeeList);
    void notify(String message);
    void notifyError(String message);
    String getCurrentUsername();
    String getCurrentClientIPAddress();
}
