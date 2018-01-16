package cn.sunxingdong.mpam.channel.service;

import cn.sunxingdong.mpam.channel.model.WeChatMessageDto;

public interface WeChatService {

    public String processMessage(WeChatMessageDto messageDto);
}
