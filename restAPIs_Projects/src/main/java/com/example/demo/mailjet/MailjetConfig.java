package com.example.demo.mailjet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;

@Configuration
public class MailjetConfig {

    @Bean
    public MailjetClient mailjetClient() {
        return new MailjetClient("YOUR_API_KEY", "YOUR_API_SECRET", new ClientOptions("v3.1"));
    }
}
