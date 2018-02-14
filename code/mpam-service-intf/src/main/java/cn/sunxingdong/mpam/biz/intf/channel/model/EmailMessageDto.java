package cn.sunxingdong.mpam.biz.intf.channel.model;

import java.sql.Date;
import java.util.List;

/**
 * Author: sun.xingdong
 * CreateDate: 2018/2/13 10:47
 */
public class EmailMessageDto {

    private Long mailId;

    private String sysAccount;

    private String userAccount;

    private String ccAccount;

    private String subject;

    private String plainText;

    private String content;

    private String attachment;

    private Date createDate;

    private Date updateDate;

    private Long origMailId;

    public Long getMailId() {
        return mailId;
    }

    public void setMailId(Long mailId) {
        this.mailId = mailId;
    }

    public String getSysAccount() {
        return sysAccount;
    }

    public void setSysAccount(String sysAccount) {
        this.sysAccount = sysAccount;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getCcAccount() {
        return ccAccount;
    }

    public void setCcAccount(String ccAccount) {
        this.ccAccount = ccAccount;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPlainText() {
        return plainText;
    }

    public void setPlainText(String plainText) {
        this.plainText = plainText;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Long getOrigMailId() {
        return origMailId;
    }

    public void setOrigMailId(Long origMailId) {
        this.origMailId = origMailId;
    }
}
