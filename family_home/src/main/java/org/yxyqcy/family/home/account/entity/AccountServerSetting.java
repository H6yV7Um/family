package org.yxyqcy.family.home.account.entity;

import org.yxyqcy.family.common.entity.IdEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by lcy on 17/2/13.
 */
@Entity
@Table(name = "f_account_serset")
public class AccountServerSetting extends IdEntity<AccountServerSetting> {
    private static final long serialVersionUID = 109442269298623659L;
    public AccountServerSetting() {
    }

    private String description;
    private String title;
    private String account;
    private String passwd;
    private String port;
    private Date seqDate;
    private String serverId;

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Date getSeqDate() {
        return seqDate;
    }

    public void setSeqDate(Date seqDate) {
        this.seqDate = seqDate;
    }
}
