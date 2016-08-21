package org.kumoricon.site.report.attendees;

import org.kumoricon.model.attendee.AttendeeRepository;
import org.kumoricon.site.report.ReportPresenter;
import org.kumoricon.site.report.ReportView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;


@Controller
public class AttendeeReportPresenter implements ReportPresenter {
    @Autowired
    private AttendeeRepository attendeeRepository;

    private static final Logger log = LoggerFactory.getLogger(AttendeeReportPresenter.class);


    public AttendeeReportPresenter() {
    }

    private String getTotalsByBadgeType() {
        StringBuilder sb = new StringBuilder();
        sb.append("<h2>Check Ins by Badge Type</h2>");
        sb.append("<table border=\"1\"><tr><td>Badge Type</td>");
        sb.append("<td>At-Con Checked In</td><td>At-Con Not Checked In</td>");
        sb.append("<td>Pre Reg Checked In</td><td>Pre Reg Not Checked In</td></tr>");
        List<Object[]> results = attendeeRepository.findBadgeCounts();
        for (Object[] line : results) {
            sb.append("<tr>");
            sb.append(String.format("<td>%s</td>", line[0].toString()));
            sb.append(String.format("<td align=\"right\">%s</td>", line[1].toString()));
            sb.append(String.format("<td align=\"right\">%s</td>", line[2].toString()));
            sb.append(String.format("<td align=\"right\">%s</td>", line[3].toString()));
            sb.append(String.format("<td align=\"right\">%s</td>", line[4].toString()));
            sb.append("</tr>");
        }
        sb.append("</table>");
        return sb.toString();
    }

    private static String buildTable(String title, List<Object[]> data) {
        StringBuilder sb = new StringBuilder();
        sb.append("<h2>" + title + "</h2>");
        sb.append("<table border=\"1\"><tr><td>Date</td><td> Checked In</td></tr>");
        for (Object[] line : data) {
            sb.append("<tr>");
            sb.append("<td>" + line[0].toString() + "</td>");
            sb.append("<td align=\"right\">" + line[1].toString() + "</td>");
            sb.append("</tr>");
        }
        sb.append("</table>");
        return sb.toString();
    }

    private static String buildTableWithRevenue(String title, List<Object[]> data) {
        StringBuilder sb = new StringBuilder();
        sb.append("<h2>" + title + "</h2>");
        sb.append("<table border=\"1\"><tr><td>Date</td><td> Checked In</td><td>Dollar Amount</td></tr>");
        for (Object[] line : data) {
            sb.append("<tr>");
            sb.append("<td>" + line[0].toString() + "</td>");
            sb.append("<td align=\"right\">" + line[1].toString() + "</td>");
            sb.append("<td align=\"right\">$" + line[2].toString() + "</td>");
            sb.append("</tr>");
        }
        sb.append("</table>");
        return sb.toString();
    }


    private static String buildAttendanceCounts( Integer totalAttendance, Integer warmBodyCount) {
        StringBuilder sb = new StringBuilder();
        sb.append("<h2>Total Attendance Counts</h2>");
        sb.append("<table border=\"1\">");
        sb.append("<tr>");
        sb.append("<td>Total Attendance</td>");
        sb.append(String.format("<td align=\"right\">%d</td>", totalAttendance));
        sb.append("</tr>");
        sb.append("<td>Warm Body Count</td>");
        sb.append(String.format("<td align=\"right\">%d</td>", warmBodyCount));
        sb.append("</tr>");
        sb.append("</table>");
        sb.append("<b>Total Attendance:</b> <i>Unique, paid</i> attendees who checked in. This means that weekend " +
                "badges are counted only once, and the count is a number of unique individual attendees who " +
                "registered in a given year. Attendance figures count paid membership purchases at standard or VIP " +
                "rates (staff, exhibitors, artists, guests, industry, press, and complimentary badges are not " +
                "counted).<br>");
        sb.append("<b>Warm Body Count:</b> All attendees (paid and gratis) who checked in<br>");
        sb.append("Attendees with multiple single day badges are counted as a single attendee based on " +
                "first name, last name, birthdate, and zip code<br>");
        return sb.toString();
    }

    @Override
    public void fetchReportData(ReportView view) {
        StringBuilder sb = new StringBuilder();
        sb.append(getTotalsByBadgeType());
        if (view.currentUserHasRight("view_attendance_report_revenue")) {
            sb.append(buildTableWithRevenue("At Con Check Ins By Day", attendeeRepository.findAtConCheckInCountsByDate()));
            sb.append(buildTableWithRevenue("Pre Reg Check Ins By Day", attendeeRepository.findPreRegCheckInCountsByDate()));
        } else {
            sb.append(buildTable("At Con Check Ins By Day", attendeeRepository.findAtConCheckInCountsByDate()));
            sb.append(buildTable("Pre Reg Check Ins By Day", attendeeRepository.findPreRegCheckInCountsByDate()));
        }
        sb.append(buildAttendanceCounts(
                attendeeRepository.findTotalAttendeeCount(), attendeeRepository.findWarmBodyCount()));
        log.info("{} viewed Attendee Report", view.getCurrentUser());
        view.afterSuccessfulFetch(sb.toString());
    }

    public AttendeeRepository getAttendeeRepository() { return attendeeRepository; }
    public void setAttendeeRepository(AttendeeRepository attendeeRepository) {
        this.attendeeRepository = attendeeRepository;
    }
}
