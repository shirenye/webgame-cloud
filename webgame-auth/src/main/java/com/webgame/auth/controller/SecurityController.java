package com.webgame.auth.controller;

import com.webgame.auth.manager.UserManager;
import com.webgame.auth.service.ValidateCodeService;
import com.webgame.common.core.constant.StringConstant;
import com.webgame.common.core.entity.WebGameResponse;
import com.webgame.common.core.exception.ValidateCodeException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

/**
 * @author MrBird
 */
@Controller
@RequiredArgsConstructor
public class SecurityController {

    private final ValidateCodeService validateCodeService;
    private final UserManager userManager;
    private final ConsumerTokenServices consumerTokenServices;

    @ResponseBody
    @GetMapping("user")
    public Principal currentUser(Principal principal) {
        return principal;
    }

    @ResponseBody
    @GetMapping("captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException, ValidateCodeException {
        validateCodeService.create(request, response);
    }

    @RequestMapping("login")
    public String login() {
        return "login";
    }

    @ResponseBody
    @DeleteMapping("signout")
    public WebGameResponse signout(HttpServletRequest request, @RequestHeader("Authorization") String token) {
        token = StringUtils.replace(token, "bearer ", StringConstant.EMPTY);
        consumerTokenServices.revokeToken(token);
        return new WebGameResponse().message("signout");
    }
}
