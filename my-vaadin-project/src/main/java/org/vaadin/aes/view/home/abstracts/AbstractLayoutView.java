package org.vaadin.aes.view.home.abstracts;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.server.VaadinSession;
import org.vaadin.aes.enums.EnumDTO;
import org.vaadin.aes.enums.EnumPageURL;
import org.vaadin.aes.model.dto.UserDataDto;
import org.vaadin.aes.view.core.drawer.LeftDrawer;
import org.vaadin.aes.view.core.header.CustomHeader;

import java.util.logging.Logger;

public abstract class AbstractLayoutView extends VerticalLayout /*implements BeforeEnterObserver */{
    private static final Logger log = Logger.getLogger(AbstractLayoutView.class.getName());
    //    private EnumPageURL enumPageURL;
    protected LeftDrawer leftDrawer = new LeftDrawer();
    protected CustomHeader customHeader;
    private final VerticalLayout body = new VerticalLayout();

    public AbstractLayoutView(EnumPageURL enumPageURL) {
        this.customHeader = new CustomHeader(enumPageURL);
        if (!isUserLoggedIn()) {
            navigateToLogin();
            log.info("User not found. will be directed to Login page");
            return;
        }


        log.info(getClassName() + " userData should be found. Go on " + getClass().getSimpleName() + " page");
//        this.enumPageURL = enumPageURL;
        setSizeFull();
        body.setSizeFull();

        add(customHeader);
        HorizontalLayout unionDrawerWithBody = new HorizontalLayout();
        unionDrawerWithBody.setSizeFull();
        unionDrawerWithBody.add(leftDrawer);
        unionDrawerWithBody.add(body);
        add(unionDrawerWithBody);
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        body.setJustifyContentMode(JustifyContentMode.CENTER);
        body.setAlignItems(Alignment.CENTER);
        body.setSpacing(false);

    }

    public VerticalLayout getBody() {
        return body;
    }

    // 14.10.2024 toplantida konusuldu
/*    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (!isUserLoggedIn()) {
//            event.forwardTo(LoginView.class);
            event.forwardTo(EnumPageURL.LOGIN_PAGE.getUrl());
        }

    }*/

    private boolean isUserLoggedIn() {
        UserDataDto userData = (UserDataDto) VaadinSession.getCurrent().getAttribute(EnumDTO.USER_DATA.getName());
        log.info("Found UserData: " + userData);
        if (userData == null) {
            return false;
        }
        return true;
    }

    private void navigateToLogin() {
        UI.getCurrent().navigate(EnumPageURL.LOGIN_PAGE.getUrl());
    }
}
