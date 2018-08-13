package top.deramertn9527.center.api.doman;


import java.io.Serializable;
import java.util.Date;

public class Test implements Serializable {


    private static final long serialVersionUID = -4564424356857501724L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 外键
     */
    private Long rfId;
    /**
     * 活动名称
     */
    private String name;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRfId() {
        return rfId;
    }

    public void setRfId(Long rfId) {
        this.rfId = rfId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", rfId=" + rfId +
                ", name='" + name + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
