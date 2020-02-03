package com.samton.platform.pm.dao;

import com.samton.platform.common.dao.AutoMapperInteger;
import com.samton.platform.pm.bean.entity.TCustLoginLog;
import com.samton.platform.pm.bean.vo.TCustLoginLogVo;

public interface TCustLoginLogMapper extends AutoMapperInteger<TCustLoginLog>{
        /**
         *通过ID查询用户登陆日志
         * @param logId
         * @return
         * @throws Exception
         */
        TCustLoginLogVo selectCustLoginLogById(Integer logId) throws Exception;
}