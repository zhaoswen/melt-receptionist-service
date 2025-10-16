package org.zhsaen.admin.service;

import org.zhsaen.admin.entity.EraExtension;

public interface EraDesignService {
    EraExtension getExtensionConfig(String extensionId);

    String createExtensionConfig(EraExtension eraExtension);

    String updateExtensionConfig(EraExtension eraExtension);
}
