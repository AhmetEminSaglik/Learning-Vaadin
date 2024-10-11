package org.vaadin.aes.view.home.abstracts;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;
import org.vaadin.aes.enums.EnumDTO;
import org.vaadin.aes.enums.EnumPageURL;
import org.vaadin.aes.model.dto.UserDataDto;
import org.vaadin.aes.view.auth.LoginView;
import org.vaadin.aes.view.home.core.drawer.LeftDrawer;
import org.vaadin.aes.view.home.core.header.CustomHeader;

import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

public abstract class AbstractLayoutView extends VerticalLayout implements BeforeEnterObserver {
    private static final Logger log = Logger.getLogger(AbstractLayoutView.class.getName());
//    private EnumPageURL enumPageURL;
    protected LeftDrawer leftDrawer = new LeftDrawer();
    protected CustomHeader customHeader;
    private final VerticalLayout body = new VerticalLayout();

    public AbstractLayoutView(EnumPageURL enumPageURL) {
        log.info(getClassName() + " userData should be found. Go on " + getClass().getSimpleName() + " page");
//        this.enumPageURL = enumPageURL;
        this.customHeader = new CustomHeader(enumPageURL);
        setSizeFull();
        body.setSizeFull();

        add(customHeader);
        HorizontalLayout unionDrawerWithBody = new HorizontalLayout();
        unionDrawerWithBody.setSizeFull();
        unionDrawerWithBody.add(leftDrawer);
        unionDrawerWithBody.add(body);
        add(unionDrawerWithBody);


    }

    public VerticalLayout getBody() {
        return body;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
//        if(!isUserLoggedIn()){
//            event.forwardTo(LoginView.class);
//            event.forwardTo(EnumPageURL.LOGIN_PAGE.getUrl());
//        }

    }
    private  boolean isUserLoggedIn(){
        UserDataDto userData = (UserDataDto) VaadinSession.getCurrent().getAttribute(EnumDTO.USER_DATA.getName());
        log.info("Found UserData: " + userData);
        if (userData == null) {
            return false;
        }
        return true;
    }
}
