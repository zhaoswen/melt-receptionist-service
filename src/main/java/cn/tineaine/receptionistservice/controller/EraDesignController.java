package cn.tineaine.receptionistservice.controller;

import cn.tineaine.receptionistservice.entity.EraExtension;
import cn.tineaine.receptionistservice.entity.Response;
import cn.tineaine.receptionistservice.service.EraDesignService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("era")
public class EraDesignController {

    @Resource
    private EraDesignService eraDesignService;

    /**
     * 处理获取扩展配置
     *
     * @param extensionId 扩展的唯一标识符，注意是全路径标识
     * @return 包含扩展配置信息的响应对象
     */
    @GetMapping("getExtensionConfig")
    public Response<EraExtension> getExtensionConfig(@RequestParam String extensionId) {
        try {
            return Response.ok(eraDesignService.getExtensionConfig(extensionId));
        } catch (Exception e) {
            return Response.error(e.getMessage(), 500);
        }
    }

    // 创建扩展配置
    @PostMapping("createExtensionConfig")
    public Response<String> createExtensionConfig(@RequestBody EraExtension eraExtension) {
        try {
            return Response.ok(eraDesignService.createExtensionConfig(eraExtension));
        } catch (Exception e) {
            return Response.error(e.getMessage(), 500);
        }
    }

    // 更新扩展
    @PostMapping("updateExtensionConfig")
    public Response<String> updateExtensionConfig(@RequestBody EraExtension eraExtension) {
        try {
            return Response.ok(eraDesignService.updateExtensionConfig(eraExtension));
        } catch (Exception e) {
            return Response.error(e.getMessage(), 500);
        }
    }

    @GetMapping("test")
    public Response<String> test() {
        return Response.ok("test");
    }
}