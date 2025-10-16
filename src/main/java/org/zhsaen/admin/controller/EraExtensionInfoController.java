package cn.tineaine.receptionistservice.controller;


import cn.tineaine.receptionistservice.entity.EraExtensionInfo;
import cn.tineaine.receptionistservice.entity.Response;
import cn.tineaine.receptionistservice.service.EraExtensionInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * 表控制层
 *
 * @author zhaosw
 * @since 2025-01-11 20:49:42
 */
@RestController
@RequestMapping("eraExtensionInfo")
public class EraExtensionInfoController {

    @Resource
    private EraExtensionInfoService eraExtensionInfoService;

    /**
     * 分页查询所有数据
     *
     * @param page             分页对象
     * @param eraExtensionInfo 查询实体
     * @return 所有数据
     */
    @PostMapping("page")
    public Response<IPage<EraExtensionInfo>> selectAll(Page<EraExtensionInfo> page, EraExtensionInfo eraExtensionInfo) {
        return Response.ok(this.eraExtensionInfoService.page(page, new QueryWrapper<>(eraExtensionInfo)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping
    public Response<EraExtensionInfo> selectOne(@RequestParam String id) {
        return Response.ok(this.eraExtensionInfoService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param eraExtensionInfo 实体对象
     * @return 新增结果
     */
    @PostMapping("add")
    public Response<Boolean> insert(@RequestBody EraExtensionInfo eraExtensionInfo) {
        return Response.ok(this.eraExtensionInfoService.save(eraExtensionInfo));
    }

    /**
     * 修改数据
     *
     * @param eraExtensionInfo 实体对象
     * @return 修改结果
     */
    @PostMapping("edit")
    public Response<Boolean> update(@RequestBody EraExtensionInfo eraExtensionInfo) {
        return Response.ok(this.eraExtensionInfoService.updateById(eraExtensionInfo));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除结果
     */
    @DeleteMapping
    public Response<Boolean> delete(@RequestParam("id") String id) {
        return Response.ok(this.eraExtensionInfoService.removeById(id));
    }
}

