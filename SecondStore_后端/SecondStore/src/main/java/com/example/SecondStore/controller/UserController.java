package com.example.SecondStore.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.SecondStore.entity.User;
import com.example.SecondStore.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Map;

/**
 * 普通用户(User)表控制层
 *
 * @author bwmgd
 * @since 2021-04-25 09:50:11
 */
@Slf4j
@RestController
@RequestMapping("user")
public class UserController extends ApiController {
    /**
     * 服务对象
     */
    @Autowired
    private UserService userService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(@RequestParam("page") long page, @RequestParam(name = "status", required = false) Serializable status) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        if (status != null) wrapper.eq(User::getStatus, status);
        return success(this.userService.page(new Page<>(page, 20), wrapper));
    }

    @PostMapping()
    public R selectList(@RequestBody Map<String, String> map) {
        return success(this.userService.list(new QueryWrapper<User>().allEq(map)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.userService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param user 实体对象
     * @return 新增结果
     */
    @PostMapping("/register")
    public R insert(@RequestBody User user) {
        if (userService.register(user.getName()) != null)  //用户名重复
            return failed("该用户名已被注册");
        return success(this.userService.save(user));
    }

    /**
     * 修改数据
     *
     * @param user 实体对象
     * @return 修改结果
     */
    @PostMapping("update")
    public R update(@RequestBody User user) {
        return success(this.userService.updateById(user));
    }

    @PostMapping("delete")
    public R delete(@RequestParam User user) {
        return success(this.userService.removeById(user.getId()));
    }

    @PostMapping("login")
    public R login(@RequestBody User user) {
        if ((user = userService.login(user.getName(), user.getPassword())) != null) {
            if(user.getStatus() == 1) return success(user);
            else if(user.getStatus() == 0) return failed("审核中,请耐心等待！");
            else return failed("该账号审核失败，请注册新账号！");
        }
        return failed("用户名或密码错误");
    }
}