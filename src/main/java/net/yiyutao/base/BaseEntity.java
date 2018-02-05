package net.yiyutao.base;

/**
 * @author masterYI
 * Date: 2017/9/20
 * Time: 10:16
 * Description:父类实体类。定义了每个实体类必须包含的属性
 */
public class BaseEntity {

    /**
     * 创建人
     */
    protected String createuser;

    /**
     * 创建时间
     */
    protected Long createtime;

    /**
     * 修改人
     */
    protected String updateuser;

    /**
     * 修改时间
     */
    protected Long updatetime;

    /**
     * 备注
     */
    protected String remark;

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public String getUpdateuser() {
        return updateuser;
    }

    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser;
    }

    public Long getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Long updatetime) {
        this.updatetime = updatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "createuser='" + createuser + '\'' +
                ", createtime=" + createtime +
                ", updateuser='" + updateuser + '\'' +
                ", updatetime=" + updatetime +
                ", remark='" + remark + '\'' +
                '}';
    }
}
