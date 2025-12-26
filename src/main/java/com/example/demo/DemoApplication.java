package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.example.demo.entity.AuditTrailRecord;
import com.example.demo.service.AuditTrailService;
import com.example.demo.service.UserService;
import com.example.demo.servlet.SimpleStatusServlet;
import com.example.demo.entity.User;


import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    // =======================
    // EXISTING SERVLET BEAN
    // =======================
    @Bean
    public ServletRegistrationBean<SimpleStatusServlet> statusServlet() {
        ServletRegistrationBean<SimpleStatusServlet> bean =
                new ServletRegistrationBean<>(new SimpleStatusServlet(), "/status");
        bean.setLoadOnStartup(1);
        return bean;
    }

    // =======================
    // AUDIT TRAIL SERVICE BEAN
    // =======================
    @Bean
    public AuditTrailService auditTrailService() {
        return new AuditTrailService() {

            private final List<AuditTrailRecord> store = new ArrayList<>();

            @Override
            public AuditTrailRecord logEvent(AuditTrailRecord record) {
                store.add(record);
                return record;
            }

            @Override
            public List<AuditTrailRecord> getLogsByCredential(Long credentialId) {
                return store.stream()
                        .filter(r -> r.getCredentialId() != null
                                && r.getCredentialId().equals(credentialId))
                        .toList();
            }

            @Override
            public List<AuditTrailRecord> getAllLogs() {
                return store;
            }
        };
    }

    // =======================
@Bean
public UserService userService() {
    return new UserService() {

        @Override
        public User registerUser(User user) {
            // dummy implementation
            return user;
        }

        @Override
        public User findByEmail(String email) {
            // dummy implementation
            return null;
        }
    };
}


}
