package cn.tineaine.receptionistservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
@RequestMapping("simx")
public class SimxController {

    // 注入ymal中的文件路径
    @Value("${engine.version_path}")
    private String filePath;

    @GetMapping("getEngineRunner")
    public ResponseEntity<Resource> getEngineRunner(@RequestParam String version) {
        System.out.println("downloadFile" + filePath);
        // 转换为路径
        String enginePath;
        // 拼接文件路径
        // 分析当前操作系统
        String osName = System.getProperty("os.name");
        if (osName.contains("Windows")) {
            // 如果是windows系统
            filePath = filePath.replace("/", "\\");
            enginePath = filePath + "\\engine\\" + version + "\\engine.exe";
        } else {
            filePath = filePath.replace("\\", "/");
            enginePath = filePath + "/engine/" + version + "/engine";
        }

        System.out.println("enginePath" + enginePath);

        File file = new File(enginePath);

        // TODO：返回标准报错
        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        Resource resource = new FileSystemResource(file);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}