package org.ict.spring.config;

import org.ict.spring.filter.RequestIdFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import java.util.Collections;
import java.util.List;

/**
 * @author sniper
 * @date 19 Jul 2023
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean requestIdFilter() {
        RequestIdFilter requestIdFilter = new RequestIdFilter();
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(requestIdFilter);
        List<String> urlPatterns = Collections.singletonList("/*");
        registrationBean.setUrlPatterns(urlPatterns);
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE + 1);
        return registrationBean;
    }

}
