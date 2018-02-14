package cn.sunxingdong.mpam.controller;

import cn.sunxingdong.mpam.biz.intf.channel.model.WeChatMessageDto;
import cn.sunxingdong.mpam.biz.intf.channel.service.WeChatService;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;

@Controller
@RequestMapping("/channels/weChat")
public class WeChatController {

    /**
     * WeiXinAdapter <br>
     */
    @Autowired
    private WeChatService weChatService;
    
    @ResponseBody
    @RequestMapping(value = "/messages")
    public void handleHttpRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        /** 读取接收到的xml消息 */
        StringBuffer sb = new StringBuffer();
        InputStream is = request.getInputStream();
        InputStreamReader isr = new InputStreamReader(is, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String s = "";
        while ((s = br.readLine()) != null) {
            sb.append(s);
        }
        String xml = sb.toString(); //次即为接收到微信端发送过来的xml数据

        String result = "";
        /** 判断是否是微信接入激活验证，只有首次接入验证时才会收到echostr参数，此时需要把它直接返回 */
        String echostr = request.getParameter("echostr");
        if (echostr != null && echostr.length() > 1) {
            result = echostr;
        } else {
            //正常的微信处理流程
            result = weChatService.processMessage(getMsgEntity(xml));
        }

        try {
            OutputStream os = response.getOutputStream();
            os.write(result.getBytes("UTF-8"));
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析微信xml消息
     * @param strXml
     * @return
     */
    public WeChatMessageDto getMsgEntity(String strXml){
        WeChatMessageDto msg = null;
        try {
            if (strXml.length() <= 0 || strXml == null)
                return null;

            // 将字符串转化为XML文档对象
            Document document = DocumentHelper.parseText(strXml);
            // 获得文档的根节点
            Element root = document.getRootElement();
            // 遍历根节点下所有子节点
            Iterator<?> iter = root.elementIterator();

            // 遍历所有结点
            msg = new WeChatMessageDto();
            //利用反射机制，调用set方法
            //获取该实体的元类型
            Class<?> c = Class.forName("com.cedarsuen.mpam.model.WeChatMessageDto");
            msg = (WeChatMessageDto)c.newInstance();//创建这个实体的对象

            while(iter.hasNext()){
                Element ele = (Element)iter.next();
                //获取set方法中的参数字段（实体类的属性）
                Field field = c.getDeclaredField(ele.getName());
                //获取set方法，field.getType())获取它的参数数据类型
                Method method = c.getDeclaredMethod("set"+ele.getName(), field.getType());
                //调用set方法
                method.invoke(msg, ele.getText());
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("xml 格式异常: "+ strXml);
            e.printStackTrace();
        }
        return msg;
    }
}