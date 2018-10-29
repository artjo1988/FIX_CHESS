package ru.fix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import ru.fix.servlets.MovesServlet;

import javax.servlet.http.HttpServlet;

@SpringBootApplication
@ComponentScan
public class Application {

    @Bean
    public ServletRegistrationBean countServlet() {
        ServletRegistrationBean<HttpServlet> registrationBean = new ServletRegistrationBean<>();
        registrationBean.setServlet(new MovesServlet());
        registrationBean.addUrlMappings("/hourse/servlet/count");
        registrationBean.setLoadOnStartup(1);
        return registrationBean;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
