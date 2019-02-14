package com.tyilack.assist.mapper;

import com.tyilack.assist.dao.WindowsRunnerDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface WindowsRunnerMapper {

    @Select("SELECT `name`,`programe_source`,`location`,`operation`,`duration`,`repeat` FROM `game_windows_runner` ")
    List<WindowsRunnerDO> listAllWindowsRunner();

}
