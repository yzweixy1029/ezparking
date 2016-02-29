package com.jsnu.yls.graduation.web.handler;

import com.jsnu.yls.graduation.persistence.entities.Admin;
import com.jsnu.yls.graduation.service.Impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

/**
 * 管理员handler
 *
 * Created by chenwei on 2015/9/17.
 * modify chenwei on 2016/2/22
 */
@Controller
@SessionAttributes(value = {"admin"})
public class AdminHandler {

    @Autowired
    private AdminServiceImpl adminService;

    @ModelAttribute
    public void getUser(@RequestParam(value = "id", required = false) Integer id,
                        Map<String, Object> map) {
        if (id != null) {
            map.put("user", adminService.getAdminByID(id));
        }
    }

    /**
     * 转发至登录页面
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Map<String, Object> map, HttpServletRequest request) {
        Admin admin = new Admin();

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("userName")) {
                try {
                    admin.setUserName(URLDecoder.decode(cookie.getValue(), "utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }

        map.put("admin", admin);


        return "user/login";
    }


    /**
     * 用户登录
     *
     * @param admin
     * @param map
     * @param is_rmb
     * @return
     */
    @RequestMapping(value = "/sign_in", method = RequestMethod.POST)
    public String sign_in(Admin admin, Map<String, Object> map, @RequestParam(required = false) String is_rmb, HttpServletResponse response) {
        Admin temp = adminService.login(admin);

        if (is_rmb == null) {
            Cookie cookie = new Cookie("userName", null);
            response.addCookie(cookie);
        } else {
            Integer rmbAccount = Integer.valueOf(is_rmb);
            if (rmbAccount == 1) {
                Cookie cookie = null;
                try {
                    cookie = new Cookie("userName", URLEncoder.encode(admin.getUserName(), "utf-8"));
                    cookie.setMaxAge(3600 * 24 * 7);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }


        if (temp != null) {

            map.put("admin", temp);

            return "";
        }


        map.put("error_message", "用户名或密码不正确");
        return "";
    }

    /**
     * 用户注销
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("admin");
        request.getSession().invalidate();
        return "";
    }


    /**
     * 新建或更新账户信息
     *
     * @param admin
     * @param result
     * @param map
     * @param request
     * @return
     */
    @RequestMapping(value = "/save_user")
    public String saveOrUpdate(@Valid Admin admin, Errors result, Map<String, Object> map, HttpServletRequest request) {
        if (result.getErrorCount() > 0) {
            map.put("admin", admin);
            return "";
        }
        adminService.saveOrUpdateAccount(admin);

        Admin account = (Admin) request.getSession().getAttribute("admin");
        if (admin.getId() != null) {
            if (admin.getId().equals(account.getId())) {
                return "redirect:logout";
            }
        }
        return "";
    }

    /**
     * 跳转至账号管理页面
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/get_admins")
    public String manageCounts(Map<String, Object> map) {
        map.put("admins", adminService.getAdmins());
        return "";
    }

    /**
     * 跳转至更新账户页面
     *
     * @param id
     * @param map
     * @return
     */
    @RequestMapping(value = "/get_admin/{id}")
    public String getAdmin(@PathVariable String id, Map<String, Object> map) {
        Integer uid = Integer.valueOf(id);
        map.put("admin", adminService.getAdminByID(uid));
        return "";
    }

    /**
     * 删除账户
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "delete_admin/{id}")
    public String deleteAccount(@PathVariable String id) {
        Integer uid = Integer.valueOf(id);
        adminService.removeAccount(adminService.getAdminByID(uid));
        return "";
    }


    /**
     * 用户注册
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "sign_up")
    public String regist(Map<String, Object> map) {
        map.put("admin", new Admin());
        return "";
    }


//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//        binder.setDisallowedFields("password");
//    }


}
