package cn.tineaine.receptionistservice.controller;


import cn.tineaine.receptionistservice.entity.EraHandlerParam;
import cn.tineaine.receptionistservice.entity.Response;
import cn.tineaine.receptionistservice.service.EraHandlerParamService;
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
@RequestMapping("eraExtensionHandlerParam")
public class EraHandlerParamController {

    @Resource
    private EraHandlerParamService eraHandlerParamService;

    /**
     * 分页查询所有数据
     *
     * @param page            分页对象
     * @param eraHandlerParam 查询实体
     * @return 所有数据
     */
    @PostMapping("page")
    public Response<IPage<EraHandlerParam>> selectAll(Page<EraHandlerParam> page, EraHandlerParam eraHandlerParam) {
        return Response.ok(this.eraHandlerParamService.page(page, new QueryWrapper<>(eraHandlerParam)));
    }

    @GetMapping("getParamsByHandlerId")
    public Response<List<EraHandlerParam>> getParamsByHandlerId(@RequestParam String handlerId) {
        return Response.ok(this.eraHandlerParamService.list(new QueryWrapper<EraHandlerParam>().eq("param_handler_id", handlerId)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Response<EraHandlerParam> selectOne(@PathVariable Serializable id) {
        return Response.ok(this.eraHandlerParamService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param eraHandlerParam 实体对象
     * @return 新增结果
     */
    @PostMapping("add")
    public Response<Boolean> insert(@RequestBody EraHandlerParam eraHandlerParam) {
        return Response.ok(this.eraHandlerParamService.save(eraHandlerParam));
    }

    /**
     * 修改数据
     *
     * @param eraHandlerParam 实体对象
     * @return 修改结果
     */
    @PutMapping("edit")
    public Response<Boolean> update(@RequestBody EraHandlerParam eraHandlerParam) {
        return Response.ok(this.eraHandlerParamService.updateById(eraHandlerParam));
    }

    /**
     * 删除数据
     *
     * @param id 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public Response<Boolean> delete(@RequestParam("id") String id) {
        return Response.ok(this.eraHandlerParamService.removeById(id));
    }
}