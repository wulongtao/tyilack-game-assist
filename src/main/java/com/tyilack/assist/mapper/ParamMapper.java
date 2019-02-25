package com.tyilack.assist.mapper;


import com.tyilack.assist.dao.ParamDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 小小黑
 */
@Mapper
@Repository
public interface ParamMapper {

    /**
     * 根据参数名找参数值
     * @param name 名称
     * @return 系统参数信息
     */
    @Select("SELECT `value` FROM `game_param` WHERE `name` = #{name}")
    ParamDO findParamByName(@Param("name") String name);

    /**
     * 获取所有静态参数
     * @return 系统参数列表
     */
    @Select("SELECT `name`,`value` FROM `game_param`")
    List<ParamDO> listAllParams();

    /**
     * 保存系统参数信息
     * @param paramDO 系统参数对象
     * @return 插入结果
     */
    @Insert("INSERT INTO(`id`,`name`,`desc`,`value`,`gmt_create`,`gmt_modified`) VALUES(#{id}, #{name}, #{desc}, #{value}, #{gmtCreate}, #{gmtModified})")
    int saveParam(ParamDO paramDO);

}
