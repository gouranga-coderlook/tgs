/**
 * 
 */
package com.coderlook.tgs.controller;

import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.coderlook.tgs.model.UserLoginModel;

/**
 * @author Gouranga
 *
 */
@Controller

public class UserAuthenticationController implements WebMvcConfigurer {

	public static final String LOGIN_TEMPLATE = "public-pages/login";
	public static final String LOGIN_REDIRECT_URL = "redirect:login";
	public static final String DASHBAORD_REDIRECT_URL = "redirect:dashboard";
	public static final String FORGET_PASSWORD_TEMPLATE = "public-pages/forget-password";
	public static final String FORGET_PASSWORD_REDIRECT_URL = "redirect:forget-password";
	public static final String LOGOUT_SUCCESS_TEMPLATE = "public-pages/logout";
	
	// use for login
	@GetMapping("/login")
	public String loginForm(Model model, HttpSession httpSession) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			return DASHBAORD_REDIRECT_URL;
		}else {
			if(httpSession.getAttribute("message")!=null && httpSession.getAttribute("message-displayed")==null) {
				httpSession.setAttribute("message-displayed", true);
			}else if(httpSession.getAttribute("message")!=null && httpSession.getAttribute("message-displayed")!=null){
				httpSession.removeAttribute("message-displayed");
				httpSession.removeAttribute("message");
			}
		}
		return LOGIN_TEMPLATE;
	}


	// use for forget password
	@GetMapping("/forget-password")
	public String forgetPasswordForm(Model model) {
		model.addAttribute("userLogin", new UserLoginModel());
		return FORGET_PASSWORD_TEMPLATE;
	}

	// use for forget password
	@PostMapping("/forget-password")
	public String forgetPasswordSubmit(@ModelAttribute UserLoginModel userLogin, Model model) {

		
		System.out.println("Test case: forget password");
		System.out.println("forgot password: "+userLogin.toString());
		
		return FORGET_PASSWORD_REDIRECT_URL;
	}
	
	@GetMapping("public/logout")
	public String logoutSuccess(Model model) {
		//model.addAttribute("userLogin", new UserLoginModel());
		return LOGOUT_SUCCESS_TEMPLATE;
	}

	@RequestMapping(value = "/public/authFailed", method = RequestMethod.GET)
	public ModelAndView authFailed() {
		ModelAndView retVal = new ModelAndView();
		retVal.setViewName("authFailed");
		return retVal;
	}

	@RequestMapping(value = "/public/accessDenied", method = RequestMethod.GET)
	public ModelAndView accessDenied() {
		ModelAndView retVal = new ModelAndView();
		retVal.setViewName("accessDenied");
		return retVal;
	}
}
