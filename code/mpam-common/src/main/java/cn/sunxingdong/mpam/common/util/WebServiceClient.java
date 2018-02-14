package cn.sunxingdong.mpam.common.util;

import com.google.gwt.dev.protobuf.ServiceException;
import org.apache.axiom.om.*;
import org.apache.axiom.soap.SOAPFactory;
import org.apache.axiom.soap.SOAPHeaderBlock;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamConstants;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Author: sun.xingdong
 * CreateDate: 2018/2/9 13:13
 */
public class WebServiceClient {
    public static void main(String[] args) throws AxisFault {
        try {
            String soapBindingAddress = "http://localhost:8080/ocswebservices/services/UserServiceSoap";
            ServiceClient sender = new ServiceClient();
            EndpointReference endpointReference = new EndpointReference(soapBindingAddress);
            Options options = new Options();
            options.setTo(endpointReference);
            sender.setOptions(options);
            OMFactory fac = OMAbstractFactory.getOMFactory();
            // 这个和qname差不多，设置命名空间
            OMNamespace omNs = fac.createOMNamespace("",  "");
            OMElement data = fac.createOMElement("SetServiceforDRM", omNs);
            // 对应参数的节点
            String[] strs = new String[] { "MSISDN", "ServiceCode", "ServiceType", "Action", "EffType" };
            // 参数值
            String[] val = new String[] { "8618900001879", "188", "0", "0", "2" };
            for (int i = 0; i < strs.length; i++) {
                QName qname=new QName(strs[i]);
                OMElement inner = fac.createOMElement(qname);
                inner.setText(val[i]);
                data.addChild(inner);
            }
            // 发送数据，返回结果
            OMElement result = sender.sendReceive(data);
            System.out.println(result.toString());
            List<OMElement> list = new LinkedList<OMElement>();
            getAllOmElements(result, list);
            String resultCode = "";
            String resultDesc = "";
            for (OMElement omElement: list) {
                String localName = omElement.getLocalName();
                if ("ResultCode".equals(localName)) {
                    resultCode = omElement.getText().trim();
                }
                else if ("ResultDesc".equals(localName)) {
                    resultDesc = omElement.getText().trim();
                }
            }
            System.out.println(resultDesc);
        } catch (AxisFault ex) {
            ex.printStackTrace();
        }
//
//        Service sv = new Service();
//        Call call = null;
//        try {
//            call = (Call) sv.createCall();
//            String eCSWebserviceUrl = "";
//            //设置要调用的接口地址
//            call.setTargetEndpointAddress(new URL(eCSWebserviceUrl));
//            call.setOperationName(new QName("SetServiceforDRM"));
//            //设置参数
//            call.addParameter("MSISDN", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
//            call.addParameter("ServiceCode", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
//            call.addParameter("ServiceType", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
//            call.addParameter("Action", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
//            call.addParameter("EffType", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
//
//            call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
//            //开始调用方法
//            Object result = call.invoke(new Object[]{
//                    "8618900001879", "188", "0", "0", "2"});
//
//        } catch (ServiceException e) {
//            //logger.error(e);
//        } catch (MalformedURLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (RemoteException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }

    }

    private static void getAllOmElements(OMElement omElement, List<OMElement> list) {
        Iterator iterator = omElement.getChildElements();
        while (iterator.hasNext()) {
            OMNode omNode = (OMNode) iterator.next();
            if (omNode.getType() == OMNode.ELEMENT_NODE) {
                list.add((OMElement) omNode);
                getAllOmElements((OMElement) omNode, list);
            }
        }
    }
}
