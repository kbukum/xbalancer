package org.oopdev.xbalancer.domain;

import org.oopdev.xbalancer.common.util.Strings;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;


@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    @Id
    @Column(name = "OID", length = 32)
    private String oid;

    @Version
    @Column(name = "DATA_VERSION")
    private long version;

    @Column(name = "CREATE_DATE")
    private Date createDate;
    @Column(name = "CREATE_USER")
    private String createUser;
    @Column(name = "UPDATE_DATE")
    private Date updateDate;
    @Column(name = "UPDATE_USER")
    private String updateUser;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    @PrePersist
    void onPrePersist() {
        if (!Strings.has(this.oid)) {
            this.oid = UUID.randomUUID().toString();
        }
        this.createDate = new Date();
    }

    @PreUpdate
    void onPreUpdate() {
        this.updateDate = new Date();
    }
}
