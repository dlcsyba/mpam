package cn.sunxingdong.mpam.biz.intf.channel.service;

import cn.sunxingdong.mpam.biz.intf.channel.model.EmailMessageDto;

/**
 * Author: sun.xingdong
 * CreateDate: 2018/2/13 10:47
 */
public interface EmailService {

    /**
     * 发送邮件
     * @param dto
     */
    public void send(EmailMessageDto dto);

    /**
     * 回复
     * @param dto
     */
    public void reply(EmailMessageDto dto);

    /**
     * 转发
     * @param dto
     */
    public void sendRedirect(EmailMessageDto dto);

}
