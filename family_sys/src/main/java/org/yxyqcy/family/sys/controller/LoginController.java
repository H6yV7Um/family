package org.yxyqcy.family.sys.controller;

import com.google.common.collect.Maps;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.yxyqcy.family.common.config.Global;
import org.yxyqcy.family.sys.cache.CacheUtil;
import org.yxyqcy.family.sys.user.entity.User;
import org.yxyqcy.family.sys.util.UserUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created with family. author: cy Date: 16/6/1 Time: 下午2:34 description: 登录
 * controller
 */
@Controller
@RequestMapping("/")
public class LoginController {

	/**
	 * 管理登录
	 */
	@RequestMapping(value = "adminLogin", method = RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response) {
		User user = UserUtils.getUserByState();
		// 如果已经登录，则跳转到管理首页
		if (user.getBusinessId() != null) {
			return "redirect:/view" + Global.getConfig("index_page");
		}
		return Global.getConfig("login_page");
	}

	/**
	 * 登录失败，真正登录的POST请求由Filter完成
	 */
	@RequestMapping(value = "adminLogin", method = RequestMethod.POST)
	public String login(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String username,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUserByState();
		// 如果已经登录，则跳转到管理首页
		if (user.getBusinessId() != null) {
			request.getSession().removeAttribute("isValidateCodeLogin");
			return "redirect:/view" + Global.getConfig("index_page");
		}
		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, username);
		// 登陆失败+1 3次以上需要验证码
		boolean loginResult = isValidateCodeLogin(username, true, false);
		model.addAttribute("isValidateCodeLogin",loginResult);
		request.getSession().setAttribute("isValidateCodeLogin",loginResult);
		return Global.getConfig("login_page");
	}

	/**
	 * 是否是验证码登录
	 *
	 * @param useruame
	 *            用户名
	 * @param isFail
	 *            计数加1
	 * @param clean
	 *            计数清零
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean isValidateCodeLogin(String useruame, boolean isFail, boolean clean) {
		Map<String, Integer> loginFailMap = (Map<String, Integer>) CacheUtil.get("loginFailMap");
		if (loginFailMap == null) {
			loginFailMap = Maps.newHashMap();
			CacheUtil.put("loginFailMap", loginFailMap);
		}
		Integer loginFailNum = loginFailMap.get(useruame);
		if (loginFailNum == null) {
			loginFailNum = 0;
		}
		if (isFail) {
			loginFailNum++;
			loginFailMap.put(useruame, loginFailNum);
		}
		if (clean) {
			loginFailMap.remove(useruame);
		}
		return loginFailNum >= 3;
	}

	/**
	 * 管理登录
	 */
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		SecurityUtils.getSubject().logout();
		//session.invalidate();
		return Global.getConfig("login_page");
	}
}
