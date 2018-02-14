package cn.sunxingdong.mpam.biz.impl.channel.service;

import cn.sunxingdong.mpam.common.api.TulingApiProcess;
import cn.sunxingdong.mpam.biz.intf.channel.model.WeChatMessageDto;
import cn.sunxingdong.mpam.biz.intf.channel.service.WeChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("weChatService")
public class WeChatServiceImpl implements WeChatService {

    @Autowired
    private TulingApiProcess tulingApiProcess;

    @Override
    public String processMessage(WeChatMessageDto messageDto) {
        /** 以文本消息为例，调用图灵机器人api接口，获取回复内容 */
        String result = "";
        if("text".endsWith(messageDto.getMsgType())){
            result = tulingApiProcess.getTulingResult(messageDto.getContent());
        }

        /** 此时，如果用户输入的是“你好”，在经过上面的过程之后，result为“你也好”类似的内容
         *  因为最终回复给微信的也是xml格式的数据，所有需要将其封装为文本类型返回消息
         * */
        result = formatXmlAnswer(messageDto.getFromUserName(), messageDto.getToUserName(), result);

        return result;
    }

    public String formatXmlAnswer(String to, String from, String content) {
        StringBuffer sb = new StringBuffer();
        Date date = new Date();
        sb.append("<xml><ToUserName><![CDATA[");
        sb.append(to);
        sb.append("]]></ToUserName><FromUserName><![CDATA[");
        sb.append(from);
        sb.append("]]></FromUserName><CreateTime>");
        sb.append(date.getTime());
        sb.append("</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[");
        sb.append(content);
        sb.append("]]></Content><FuncFlag>0</FuncFlag></xml>");
        return sb.toString();
    }
}
