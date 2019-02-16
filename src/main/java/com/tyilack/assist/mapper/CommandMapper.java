package com.tyilack.assist.mapper;

import com.tyilack.assist.dao.CommandGroupItemDO;
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
    @Select("SELECT `location`,`operation`,`duration` FROM `game_command_group_item` WHERE `group_id`=#{groupId}")
    List<CommandGroupItemDO> listCommandByGroupId(@Param("groupId") Integer groupId);

}
