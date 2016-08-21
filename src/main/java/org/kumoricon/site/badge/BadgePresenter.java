package org.kumoricon.site.badge;

import org.kumoricon.model.badge.Badge;
import org.kumoricon.model.badge.BadgeFactory;
import org.kumoricon.model.badge.BadgeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;


@Controller
public class BadgePresenter {
    @Autowired
    private BadgeRepository badgeRepository;

    private static final Logger log = LoggerFactory.getLogger(BadgePresenter.class);

    public BadgePresenter() {
    }

    public void badgeSelected(BadgeView view, Badge badge) {
        if (badge != null) {
            log.info("{} viewed badge {}", view.getCurrentUser(), badge);
            view.navigateTo(BadgeView.VIEW_NAME + "/" + badge.getId().toString());
            view.showBadge(badge);
        }
    }

    public void badgeSelected(BadgeView view, Integer id) {
        if (id != null) {
            org.kumoricon.model.badge.Badge badge = badgeRepository.findOne(id);
            badgeSelected(view, badge);
        }
    }

    public void addNewBadge(BadgeView view) {
        log.info("{} created new badge", view.getCurrentUser());
        view.navigateTo(BadgeView.VIEW_NAME);
        Badge newBadge = BadgeFactory.emptyBadgeFactory();
        view.showBadge(newBadge);
    }

    public void cancelBadge(BadgeView view) {
        view.clearSelection();
        view.navigateTo(BadgeView.VIEW_NAME);
        view.closeBadgeEditWindow();
    }

    public void saveBadge(BadgeView view, Badge badge) {
        log.info("{} saved badge {}", view.getCurrentUser(), badge);
        badgeRepository.save(badge);
        view.navigateTo(BadgeView.VIEW_NAME);
        showBadgeList(view);
    }

    public void showBadgeList(BadgeView view) {
        log.info("{} viewed badge list", view.getCurrentUser());
        List<Badge> badges = badgeRepository.findAll();
        view.afterSuccessfulFetch(badges);
    }

    public void navigateToBadge(BadgeView view, String parameters) {
        if (parameters != null) {
            Integer id = Integer.parseInt(parameters);
            Badge badge = badgeRepository.findOne(id);
            if (badge != null) {
                view.selectBadge(badge);
            } else {
                log.error("{} tried to view badge id {} but it was not found in the database",
                    view.getCurrentUser(), id);
            }
        }
    }

// Currently not used because badges may be set as not visible, but may not be deleted outright
//    public void deleteBadge(BadgeView view, Badge badge) {
//        if (badge.getId() != null) {
//            log.info("{} deleted badge {}", view.getCurrentUser(), badge);
//            badgeRepository.delete(badge);
//            view.navigateTo(view.VIEW_NAME);
//            showBadgeList(view);
//            view.notify(badge.getName() + " deleted");
//        }
//        view.closeBadgeEditWindow();
//    }
}
