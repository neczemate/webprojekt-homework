package hu.nye.webprojekt.HomeWork.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(path = "/authCheck")
public class AuthorizationCheckController {

    @RequestMapping(path = "/principal", method = RequestMethod.GET)
    public String checkAuthorizationWithPrincipal(Principal principal) {
        return String.format("Welcome, %s!", principal.getName());
    }

    /*
    @RequestMapping(path = "/authentication", method = RequestMethod.GET)
    public String checkAuthorizationWithAuthentication(Authentication authentication) {
        return String.format("Welcome, %s!", authentication.getName());
    }
     */

    @RequestMapping(path = "/request", method = RequestMethod.GET)
    public String checkAuthorizationWithRequest(HttpServletRequest httpServletRequest) {
        return String.format("Welcome, %s!", httpServletRequest.getAttribute("authorizedUsername"));
    }

}
