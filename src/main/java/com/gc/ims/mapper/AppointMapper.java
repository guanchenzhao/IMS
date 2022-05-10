package com.gc.ims.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gc.ims.entity.AppointEntity;
import com.gc.ims.model.dto.AppointVo;
import com.gc.ims.model.param.AppointParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AppointMapper extends BaseMapper<AppointEntity> {


  Page<AppointVo> customPageList(@Param("page") Page page, @Param("paramCondition") AppointParam paramCondition);

}
