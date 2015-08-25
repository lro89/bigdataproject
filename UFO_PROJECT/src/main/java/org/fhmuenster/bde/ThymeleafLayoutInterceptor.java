package org.fhmuenster.bde;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Sorgt dafür den Content in default Layout dynamisch zu befüllen.
 */
public class ThymeleafLayoutInterceptor extends HandlerInterceptorAdapter {

	private static final String DEFAULT_LAYOUT = "layout/default";
	private static final String DEFAULT_VIEW_ATTRIBUTE_NAME = "view";

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (modelAndView == null || !modelAndView.hasView()) {
			return;
		}
		String originalViewName = modelAndView.getViewName();
		modelAndView.setViewName(DEFAULT_LAYOUT);
		modelAndView.addObject(DEFAULT_VIEW_ATTRIBUTE_NAME, originalViewName);
	}
}