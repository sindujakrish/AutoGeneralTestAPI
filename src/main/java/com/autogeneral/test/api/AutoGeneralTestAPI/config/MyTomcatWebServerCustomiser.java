package com.autogeneral.test.api.AutoGeneralTestAPI.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

@Component
public class MyTomcatWebServerCustomiser
        implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

    @Override
    public void customize(TomcatServletWebServerFactory factory) {
    	factory.addConnectorCustomizers(connector -> connector.setAttribute("relaxedQueryChars", "[,]"));
    }
}
