package org.tlf.springcloud.service.impl;

import org.tlf.springcloud.dao.StorageDao;
import org.tlf.springcloud.service.StorageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zzyy
 * @date 2020/3/8 15:44
 **/
@Service
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageDao storageDao;

    /**
     * 减库存
     *
     * @param productId
     * @param count
     * @return
     */
    @Override
    public void decrease(Long productId, Integer count) {
        storageDao.decrease(productId, count);
    }
}
