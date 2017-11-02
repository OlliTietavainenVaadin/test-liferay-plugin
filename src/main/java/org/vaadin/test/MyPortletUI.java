package org.vaadin.test;

import javax.portlet.PortletContext;
import javax.portlet.PortletSession;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.WrappedPortletSession;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;

@Theme("mytheme")
@SuppressWarnings("serial")
@Widgetset("org.vaadin.test.AppWidgetSet")
public class MyPortletUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        final String portletContextName = getPortletContextName(request);
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);

        final Button button = new Button("Click Me");
        button.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                layout.addComponent(new Label(
                        "Hello, World!<br>This is portlet "
                                + portletContextName,
                        ContentMode.HTML));

            }
        });
        ComboBox cb = new ComboBox();
        cb.addItem("foo");
        cb.addItem("bar");
        cb.addValueChangeListener(v -> {
        	Notification.show("Selected:" + v.getProperty().getValue());
        });
        layout.addComponents(button, cb);
    }

    private String getPortletContextName(VaadinRequest request) {
        WrappedPortletSession wrappedPortletSession = (WrappedPortletSession) request
                .getWrappedSession();
        PortletSession portletSession = wrappedPortletSession
                .getPortletSession();

        final PortletContext context = portletSession.getPortletContext();
        final String portletContextName = context.getPortletContextName();
        return portletContextName;
    }


}
