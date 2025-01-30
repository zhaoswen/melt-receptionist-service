package cn.tineaine.receptionistservice.service;

import cn.tineaine.receptionistservice.entity.EraExtension;

public interface EraDesignService {
    EraExtension getExtensionConfig(String extensionId);

    String createExtensionConfig(EraExtension eraExtension);

    String updateExtensionConfig(EraExtension eraExtension);
}
