package com.example.demo.mailjet;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.resource.Emailv31;

public class Mailjet {

    @Autowired
    private MailjetClient mailjetClient;

    public void sendEmail() throws MailjetException, MailjetSocketTimeoutException {
        MailjetRequest request = new MailjetRequest(Emailv31.resource)
                .property(Emailv31.MESSAGES, new JSONArray()
                        .put(new JSONObject()
                                .put("From", new JSONObject()
                                        .put("Email", "your-email@example.com")
                                        .put("Name", "Your Name"))
                                .put("To", new JSONArray()
                                        .put(new JSONObject()
                                                .put("Email", "recipient@example.com")))
                                .put("Subject", "Test Email")
                                .put("TextPart", "Hello, this is a test email from Mailjet.")));

        MailjetResponse response = mailjetClient.post(request);
        System.out.println(response.getStatus());
        System.out.println(response.getData());
    }
}
