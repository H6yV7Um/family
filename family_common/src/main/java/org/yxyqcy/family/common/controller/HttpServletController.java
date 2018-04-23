package org.yxyqcy.family.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created with family.
 * User: cy
 * Date: 16/5/20
 * Time: 下午1:48
 * description: autowired servlet
 */
@Controller
public class HttpServletController {
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpSession session;
    @Autowired
    protected HttpServletResponse response;

    public HttpServletController() {
    }
}
