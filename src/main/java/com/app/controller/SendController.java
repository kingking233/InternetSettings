package com.app.controller;

import com.app.service.Sender;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/send")
public class SendController {

    private final Sender sender;

    String iosMessage = "Open Settings > Mobile Data. Select Primary AT number. Go to Mobile Data Network. Under Mobile Data, set APN: internet. Go back to save and restart.";
    String androidMessage = "Go to Settings > Connections > Mobile Networks > APN. If no APN for AT, tap Add, enter Name: AT, APN: Internet. Save and select it to enable mobile data";
    String otherMessage  = "Go to Menu > Settings > Connectivity. Choose Access Points. Add or edit profile. Set Account Name: AT Internet and APN: internet. Save and exit.";


    public SendController(Sender sender) {
        this.sender = sender;
    }

    @GetMapping("/android")
    public ResponseEntity<String> sendSmsAndroid(@RequestParam String msisdn ) {
        boolean success = sender.sendSms(msisdn, androidMessage);

        if (success) {
            return ResponseEntity.ok("SMS sent successfully.");
        } else {
            return ResponseEntity.badRequest().body("Failed to send SMS.");
        }
    }

    @GetMapping("/IOS")
    public ResponseEntity<String> sendSmsIos(@RequestParam String msisdn) {
        boolean success = sender.sendSms(msisdn, iosMessage);

        if (success) {
            return ResponseEntity.ok("SMS sent successfully.");
        } else {
            return ResponseEntity.badRequest().body("Failed to send SMS.");
        }
    }


    @GetMapping("/other")
    public ResponseEntity<String> sendSms(@RequestParam String msisdn) {
        boolean success = sender.sendSms(msisdn, otherMessage);

        if (success) {
            return ResponseEntity.ok("SMS sent successfully.");
        } else {
            return ResponseEntity.badRequest().body("Failed to send SMS.");
        }
    }
}
