package org.vaadin.aes.view.home.concretes;

import com.vaadin.flow.router.Route;
import org.vaadin.aes.enums.EnumPageURL;
import org.vaadin.aes.view.home.abstracts.AbstractLayoutView;

@Route("profile")
public class ProfileView extends AbstractLayoutView {
    public ProfileView() {
        super(EnumPageURL.PROFILE);
    }
}
