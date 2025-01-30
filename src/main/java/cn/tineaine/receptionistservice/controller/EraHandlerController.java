package cn.tineaine.receptionistservice.controller;


import cn.tineaine.receptionistservice.entity.EraHandler;
import cn.tineaine.receptionistservice.entity.Response;
import cn.tineaine.receptionistservice.service.EraHandlerService;
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
@RequestMapping("eraExtensionHandler")
public class EraHandlerController {

    @Resource
    private EraHandlerService eraExtensionHandlerService;

    /**
     * 分页查询所有数据
     *
     * @param page       分页对象
     * @param eraHandler 查询实体
     * @return 所有数据
     */
    @PostMapping("page")
    public Response<IPage<EraHandler>> selectAll(Page<EraHandler> page, EraHandler eraHandler) {
        return Response.ok(this.eraExtensionHandlerService.page(page, new QueryWrapper<>(eraHandler)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping
    public Response<EraHandler> selectOne(@RequestParam String id) {
        return Response.ok(this.eraExtensionHandlerService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param eraHandler 实体对象
     * @return 新增结果
     */
    @PostMapping("add")
    public Response<Boolean> insert(@RequestBody EraHandler eraHandler) {
        System.out.println(eraHandler);
        return Response.ok(this.eraExtensionHandlerService.save(eraHandler));
    }

    /**
     * 修改数据
     *
     * @param eraHandler 实体对象
     * @return 修改结果
     */
    @PostMapping("edit")
    public Response<Boolean> update(@RequestBody EraHandler eraHandler) {
        return Response.ok(this.eraExtensionHandlerService.updateById(eraHandler));
    }

    /**
     * 删除数据
     *
     * @param id 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public Response<Boolean> delete(@RequestParam("id") String id) {
        return Response.ok(this.eraExtensionHandlerService.removeById(id));
    }
}

