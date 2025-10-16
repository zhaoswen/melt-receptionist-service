package org.zhsaen.admin.service.impl;

import org.zhsaen.admin.dao.EraExtensionInfoDao;
import org.zhsaen.admin.dao.EraHandlerDao;
import org.zhsaen.admin.dao.EraHandlerGroupDao;
import org.zhsaen.admin.dao.EraHandlerParamDao;
import org.zhsaen.admin.entity.EraExtension;
import org.zhsaen.admin.entity.EraHandler;
import org.zhsaen.admin.entity.EraHandlerGroup;
import org.zhsaen.admin.entity.EraHandlerParam;
import org.zhsaen.admin.service.EraDesignService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EraDesignServiceImpl implements EraDesignService {
    @Resource
    private EraExtensionInfoDao eraExtensionInfoDao;
    @Resource
    private EraHandlerGroupDao eraHandlerGroupDao;
    @Resource
    private EraHandlerDao eraHandlerDao;
    @Resource
    private EraHandlerParamDao eraHandlerParamDao;

    @Override
    public EraExtension getExtensionConfig(String extensionId) {
        EraExtension eraExtension = new EraExtension();
        // 查询主要配置数据
        eraExtension.setInfo(eraExtensionInfoDao.selectById(extensionId));
        if (eraExtension.getInfo() == null) {
            // return null;
            throw new RuntimeException("extensionId not found");
        }
        // 查询处理器配置组数据
        List<EraHandlerGroup> handlerGroups = eraHandlerGroupDao
                .selectList(new QueryWrapper<EraHandlerGroup>().eq("extension_id", extensionId));
        for (int i = 0; i < handlerGroups.size(); i++) {
            EraHandlerGroup handlerGroup = handlerGroups.get(i);
            // 查询处理器数据
            List<EraHandler> funcs = eraHandlerDao.selectList(new QueryWrapper<EraHandler>().eq("group_id", handlerGroup.getId()));
            for (int j = 0; j < funcs.size(); j++) {
                EraHandler func = funcs.get(j);
                // 查询处理器参数数据
                List<EraHandlerParam> params = eraHandlerParamDao
                        .selectList(new QueryWrapper<EraHandlerParam>().eq("param_handler_id", func.getId()));
                func.setParams(params);
                funcs.set(j, func);
            }
            handlerGroup.setFunc(funcs);
            handlerGroups.set(i, handlerGroup);
        }
        eraExtension.setHandlers(handlerGroups);
        return eraExtension;
    }

    @Override
    public String createExtensionConfig(EraExtension eraExtension) {
        eraExtensionInfoDao.insert(eraExtension.getInfo());
        for (EraHandlerGroup eraHandlerGroup : eraExtension.getHandlers()) {
            eraHandlerGroupDao.insert(eraHandlerGroup);
            for (EraHandler eraHandler : eraHandlerGroup.getFunc()) {
                eraHandlerDao.insert(eraHandler);
                for (EraHandlerParam eraHandlerParam : eraHandler.getParams()) {
                    eraHandlerParamDao.insert(eraHandlerParam);
                }
            }
        }
        return "ok";
    }

    @Override
    public String updateExtensionConfig(EraExtension eraExtension) {
        eraExtensionInfoDao.updateById(eraExtension.getInfo());
        for (EraHandlerGroup eraHandlerGroup : eraExtension.getHandlers()) {
            eraHandlerGroupDao.updateById(eraHandlerGroup);
            for (EraHandler eraHandler : eraHandlerGroup.getFunc()) {
                eraHandlerDao.updateById(eraHandler);
                for (EraHandlerParam eraHandlerParam : eraHandler.getParams()) {
                    eraHandlerParamDao.updateById(eraHandlerParam);
                }
            }
        }
        return "ok";
    }

}
