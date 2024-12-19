package org.river.article.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.river.article.pojo.entity.Channel;

import java.util.List;

@Mapper
public interface ChannelMapper {
    List<Channel> getChannelList();

    Channel getChannelById(Integer id);

    void modifyChannel(Channel channel);

    void deleteById(Integer id);
}
