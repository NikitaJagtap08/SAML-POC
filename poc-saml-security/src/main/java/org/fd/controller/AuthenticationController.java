package org.fd.controller;

import java.net.URI;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * User authentication controller.
 */
@EnableWebMvc
@Controller
public class AuthenticationController {

	/**
	 * Displays the home page on successful login.
	 */
	@RequestMapping("/login/continue")
	public String login() {
		return "home";
	}

	/**
	 * Displays the home page on successful logout.
	 */
	@RequestMapping("/logout/continue")
	public String logout() {
		return "home";
	}

	/**
	 * Displays the login page.
	 */
	@RequestMapping("/login")
	public String show() {
		return "login";
	}

	@RequestMapping("/main")
	public ModelAndView showMain() {

		ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequestUri();
		builder.scheme("https");
		
		URI newUri = builder.build().toUri();
		
		String s=newUri.toString();
		//System.out.println(s.substring(0,41));
		
		s=s.substring(0,41);
		s=s+"login";
		s=s.replaceAll("https", "http");
		
		ModelAndView model =new ModelAndView();
		model.addObject("loginuri",s);
		return model;
	}

	@Bean
	public ViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setViewClass(JstlView.class);
		bean.setPrefix("/WEB-INF/jsp/");
		bean.setSuffix(".jsp");
		return bean;
	}
}
