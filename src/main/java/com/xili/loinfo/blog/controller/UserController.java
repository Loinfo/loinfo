package com.xili.loinfo.blog.controller;

import com.xili.loinfo.blog.domain.Authority;
import com.xili.loinfo.blog.domain.User;
import com.xili.loinfo.blog.repository.UserRepository;
import com.xili.loinfo.blog.service.AuthorityService;
import com.xili.loinfo.blog.service.UserService;
import com.xili.loinfo.blog.util.ConstraintViolationExceptionHandler;
import com.xili.loinfo.blog.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Auther: liangxin
 * @Date: 2019/8/23
 * @Description:
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private static AtomicLong counter = new AtomicLong();

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityService authorityService;

    /**
     * 查询所有用户
     * @param async
     * @param pageIndex
     * @param pageSize
     * @param name
     * @param model
     * @return
     */
    @GetMapping
    public ModelAndView list(@RequestParam(value = "async", required = false) boolean async,
                             @RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex,
                             @RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize,
                             @RequestParam(value = "name", required = false, defaultValue = "") String name,
                                         Model model){
//        model.addAttribute("userList", userRepository.findAll());
//        model.addAttribute("title", "用户管理");
//        return new ModelAndView("users/list", "userModel", model);
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<User> page = userService.listUsersByNameLike(name, pageable);
        List<User> list = page.getContent();	// 当前所在页面数据列表
        model.addAttribute("title", "用户管理");
        model.addAttribute("page", page);
        model.addAttribute("userList", userRepository.findAll());
        return new ModelAndView(async==true?"users/list :: #mainContainerRepleace":"users/list", "userModel", model);
    }

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ModelAndView view(@PathVariable("id") Long id, Model model){
        Optional<User> byId = userRepository.findById(id);
        model.addAttribute("user", byId.get());
        model.addAttribute("title", "查看用户");

        return new ModelAndView("users/view","userModel", model);
    }

    /**
     * 获取 form 表单页面
     * @param model
     * @return
     */
    @GetMapping("/add")
    public ModelAndView createForm(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("title", "创建用户");
        return new ModelAndView("users/add","userModel", model);
    }

    /**
     * 新建用户
     * @param user
     * @return
     */
    @PostMapping
    public ResponseEntity<Response> create(User user, Long authorityId) {
//        userRepository.save(user);
//        return new ModelAndView("redirect:/users");
        List<Authority> authorities = new ArrayList<>();
        authorities.add(authorityService.getAuthorityById(authorityId));
        user.setAuthorities(authorities);
        if (user.getId() == null) {
            user.setEncodePassword(user.getPassword());
        } else {
            //判断密码是否变更
            User originalUser = userService.getUserById(user.getId());
            @NotEmpty(message = "密码不能为空") @Size(max = 100) String password = originalUser.getPassword();
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encodePasswd = encoder.encode(user.getPassword());
            boolean matches = encoder.matches(password, encodePasswd);
            if (!matches) {
                user.setEncodePassword(user.getPassword());
            } else {
                user.setPassword(user.getPassword());
            }
        }

        try {
            userService.saveOrUpdateUser(user);
        } catch (ConstraintViolationException e) {
            return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
        }
        return ResponseEntity.ok().body(new Response(true, "处理成功", user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable("id") Long id, Model model){
        try {
            userService.removeUser(id);
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, e.getMessage()));
        }
        return ResponseEntity.ok().body(new Response(true, "处理成功"));
//        userRepository.deleteById(id);
//        return new ModelAndView("redirect:/users");   //重定向到list.html页面
    }

    /**
     * 获取修改用户到界面
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "edit/{id}")
    public ModelAndView modify(@PathVariable("id") Long id, Model model){
        User user = userService.getUserById(id);
//        User user = userRepository.findById(id).get();
        model.addAttribute("user", user);
        model.addAttribute("title", "修改用户");
        return new ModelAndView("users/edit","userModel", model);
    }

}
