package cn.sunxingdong.mpam.biz.intf.channel.service;

import cn.sunxingdong.mpam.biz.intf.channel.model.WeChatMessageDto;

public interface WeChatService {

    public String processMessage(WeChatMessageDto messageDto);
}
