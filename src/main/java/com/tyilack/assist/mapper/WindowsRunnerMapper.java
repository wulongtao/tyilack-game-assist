package com.tyilack.assist.mapper;

import com.tyilack.assist.dao.WindowsRunnerDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wulongtao
 */
@Mapper
@Repository
public interface WindowsRunnerMapper {

    /**
     * 查询系统
     * @param gameId 游戏ID
     * @return 系统运行程序
     */
    @Select("SELECT `name`,`program_source`,`group_id`,`duration`,`repeat` FROM `game_windows_runner` WHERE `game_id`=#{gameId} ")
    List<WindowsRunnerDO> listWindowsRunnerByGame(@Param("gameId") Integer gameId);

    /**
     * 保存运行程序
     * @param windowsRunnerDO 系统运行程序对象
     * @return 插入结果
     */
    @Insert("INSERT INTO `game_windows_runner`(`game_id`,`group_id`,`program_source`,`duration`,`repeat`,`gmt_create`,`gmt_modified`) VALUES(#{gameId},#{programSource},#{location},#{operation},#{duration},#{repeat},#{gmtCreate},#{gmtModified})")
    int saveWindowsRunner(WindowsRunnerDO windowsRunnerDO);

}
