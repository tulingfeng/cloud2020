package org.tlf.springcloud.service.impl;

import org.tlf.springcloud.dao.StorageDao;
import org.tlf.springcloud.service.StorageService;

import javax.annotation.Resource;

public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageDao storageDao;

    @Override
    public void decrease(Long productId, Integer count) {

        storageDao.decrease(productId, count);
    }
}
