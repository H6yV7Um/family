package org.yxyqcy.family.home.account.entity;


import org.yxyqcy.family.common.entity.IdEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created with family.
 * author: cy
 * Date: 16/12/17
 * Time: 下午9:13
 * description:  帐号
 */
@Entity
@Table(name = "f_account")
public class Account extends IdEntity<Account> {

    private static final long serialVersionUID = 8547757006935104272L;

    public Account() {
    }

    private String url;
    private String description;
    private String title;
    private String account;
    private String passwd;
    private String type;
    private Date seqDate;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getSeqDate() {
        return seqDate;
    }

    public void setSeqDate(Date seqDate) {
        this.seqDate = seqDate;
    }
}
