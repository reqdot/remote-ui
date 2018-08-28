package com.repl.remoteui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UIController {

    @Autowired
    private OAuth2RestOperations restTemplate;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/token")
    public String getToken(HttpServletResponse res) {
        OAuth2AccessToken accessToken = restTemplate.getAccessToken();
        System.out.println("accessToken: " + accessToken);

//        String bearerToken = "Bearer " + accessToken;
        Cookie setCookie = new Cookie("accessToken", accessToken.toString());
        setCookie.setMaxAge(60*60*24);
        setCookie.setPath("/");
        res.addCookie(setCookie);

        return "token";
    }

    @GetMapping("/profile")
    public void profile() {

    }

}
