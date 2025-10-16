package cn.tineaine.receptionistservice.controller;


import cn.tineaine.receptionistservice.entity.EraHandlerGroup;
import cn.tineaine.receptionistservice.entity.Response;
import cn.tineaine.receptionistservice.service.EraHandlerGroupService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * 表控制层
 *
 * @author zhaosw
 * @since 2025-01-11 20:49:42
 */
@RestController
@RequestMapping("eraExtensionGroup")
public class EraHandlerGroupController {

    @Resource
    private EraHandlerGroupService eraHandlerGroupService;

    /**
     * 分页查询所有数据
     *
     * @param page            分页对象
     * @param eraHandlerGroup 查询实体
     * @return 所有数据
     */
    @PostMapping("page")
    public Response<IPage<EraHandlerGroup>> selectAll(Page<EraHandlerGroup> page, EraHandlerGroup eraHandlerGroup) {
        return Response.ok(this.eraHandlerGroupService.page(page, new QueryWrapper<>(eraHandlerGroup)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping
    public Response<EraHandlerGroup> selectOne(@RequestParam String id) {
        return Response.ok(this.eraHandlerGroupService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param eraHandlerGroup 实体对象
     * @return 新增结果
     */
    @PostMapping("add")
    public Response<Boolean> insert(@RequestBody EraHandlerGroup eraHandlerGroup) {
        return Response.ok(this.eraHandlerGroupService.save(eraHandlerGroup));
    }

    /**
     * 修改数据
     *
     * @param eraHandlerGroup 实体对象
     * @return 修改结果
     */
    @PostMapping("edit")
    public Response<Boolean> update(@RequestBody EraHandlerGroup eraHandlerGroup) {
        return Response.ok(this.eraHandlerGroupService.updateById(eraHandlerGroup));
    }

    /**
     * 删除数据
     *
     * @param id 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public Response<Boolean> delete(@RequestParam("id") Long id) {
        return Response.ok(this.eraHandlerGroupService.removeById(id));
    }
}

