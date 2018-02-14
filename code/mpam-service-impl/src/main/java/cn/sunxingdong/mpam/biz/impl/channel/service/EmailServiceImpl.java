package cn.sunxingdong.mpam.biz.impl.channel.service;

import cn.sunxingdong.mpam.biz.intf.channel.model.EmailMessageDto;
import cn.sunxingdong.mpam.biz.intf.channel.service.EmailService;
import cn.sunxingdong.mpam.biz.intf.file.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

/**
 * Author: sun.xingdong
 * CreateDate: 2018/2/13 10:48
 */
@Service("emailService")
public class EmailServiceImpl implements EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    FileService fileService;

    @Override
    public void send(EmailMessageDto dto) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(dto.getSysAccount());
//        message.setTo(dto.getUserAccount());
//        message.setSubject(dto.getSubject());
//        message.setText(dto.getPlainText());
//        mailSender.send(message);

        MimeMessagePreparator preparator = getMessagePreparator(dto);
        mailSender.send(preparator);
    }

    @Override
    public void reply(EmailMessageDto dto) {

    }

    @Override
    public void sendRedirect(EmailMessageDto dto) {

    }

    private MimeMessagePreparator getMessagePreparator(final EmailMessageDto dto) {
        return new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
                helper.setFrom(dto.getSysAccount());
                helper.setTo(dto.getUserAccount().split(";"));
                if (!StringUtils.isEmpty(dto.getCcAccount())) {
                    helper.setCc(dto.getCcAccount().split(";"));
                }

                helper.setSubject(dto.getSubject());
                helper.setText(dto.getPlainText(), dto.getContent());
                if (!StringUtils.isEmpty(dto.getAttachment())) {
                    String[] attachmentArr = dto.getAttachment().split(";");
                    for (int i = 0; i < attachmentArr.length; i++) {
                        File file = new File(attachmentArr[i]);
                        fileService.download(file, attachmentArr[i]);
                        helper.addAttachment(attachmentArr[i], file);
                    }
                }

                helper.setSentDate(dto.getCreateDate());

//                mimeMessage.setFrom(new InternetAddress(dto.getSysAccount()));
//                mimeMessage.setSubject(dto.getSubject());
//
//                // 设置接收者地址
//                String[] receivers = dto.getUserAccount().split(";");
//                Address[] tos = new InternetAddress[receivers.length];
//                for (int i = 0; i < receivers.length; i++) {
//                    tos[i] = new InternetAddress(receivers[i]);
//                }
//                mimeMessage.setRecipients(Message.RecipientType.TO, tos);
//
//                // 设置抄送
//                if (!StringUtils.isEmpty(dto.getCcAccount())) {
//                    String[] ccAccounts = dto.getCcAccount().split(";");
//                    Address[] ccs = new InternetAddress[ccAccounts.length];
//                    for (int i = 0; i < ccAccounts.length; i++) {
//                        ccs[i] = new InternetAddress(ccAccounts[i]);
//                    }
//                    mimeMessage.setRecipients(Message.RecipientType.CC, ccs);
//                }
//
//                mimeMessage.setSentDate(new Date());
            }
        };
    }
}
