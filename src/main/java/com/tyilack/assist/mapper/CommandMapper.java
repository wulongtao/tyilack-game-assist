package com.tyilack.assist.mapper;

import com.tyilack.assist.dao.CommandGroupItemDO;
import com.tyilack.assist.dao.TaskCommandGroupDO;
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
public interface CommandMapper {

    /**
     * 获取指令集中的所有指令
     * @param groupId
     * @return
     */
    @Select("SELECT `condition`,`location`,`operation`,`duration` FROM `game_command_group_item` WHERE `group_id`=#{groupId}")
    List<CommandGroupItemDO> listCommandByGroupId(@Param("groupId") Integer groupId);

    /**
     * 根据游戏ID查询游戏的前置指令
     * @param gameId
     * @return
     */
    @Select("SELECT `condition`,`location`,`operation`,`duration` FROM `game_pre_command` AS `a` JOIN `game_command_group_item` AS `b` ON `a`.`group_id`=`b`.`group_id` WHERE `a`.`game_id`=#{gameId}")
    List<CommandGroupItemDO> listPreCommandByGameId(@Param("gameId") Integer gameId);

    /**
     * 查询任务中所有的指令集ID
     * @param taskId
     * @return
     */
    @Select("SELECT `group_id` FROM `game_task_command_group` WHERE `task_id`=#{taskId} ORDER BY `order` ASC")
    List<TaskCommandGroupDO> listTaskCommandGroupByTaskId(@Param("taskId") Integer taskId);

}
