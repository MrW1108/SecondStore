package com.example.SecondStore.controller;


import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.example.SecondStore.entity.Kind;
import com.example.SecondStore.service.KindService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 类别(Kind)表控制层
 *
 * @author bwmgd
 * @since 2021-04-25 09:50:11
 */
@Slf4j
@RestController
@RequestMapping("kind")
public class KindController extends ApiController {
    /**
     * 服务对象
     */
    @Autowired
    private KindService kindService;

    /**
     * 分页查询所有数据
     *
     * @return 所有数据
     */
    @GetMapping
    public R selectAll() {
        return success(this.kindService.list());
    }

    /**
     * 新增数据
     *
     * @param kind 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody Kind kind) {
        return success(this.kindService.save(kind));
    }
}