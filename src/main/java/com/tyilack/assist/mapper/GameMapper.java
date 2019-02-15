package com.tyilack.assist.mapper;

import com.tyilack.assist.dao.GameDO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @author wulongtao
 */
@Mapper
@Repository
public interface GameMapper {

    /**
     * 保存游戏信息
     * @param gameDO
     * @return
     */
    @Insert("INSERT INTO `game`(`id`,`name`,`logo`,`status`,`complete_operation`,`gmt_create`,`gmt_modified`) VALUES(#{id},#{name},#{logo},#{status},#{completeOperation},#{gmtCreate},#{gmtModified})")
    int saveGame(GameDO gameDO);

    /**
     * 根据ID查找游戏
     * @param id
     * @return
     */
    @Select("SELECT `name`,`logo`,`status`,`complete_operation` FROM `game` WHERE `id`=#{id}")
    GameDO findGameById(@Param("id") String id);

    /**
     * 更新游戏状态
     * @param id
     * @return
     */
    @Update("UPDATE `game` SET `status`=#{status} WHERE `id`=#{id}")
    int updateGameStatusById(@Param("id") String id);

}
