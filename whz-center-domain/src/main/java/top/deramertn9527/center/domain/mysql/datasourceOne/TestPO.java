package top.deramertn9527.center.domain.mysql.datasourceOne;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Data
@Entity
@Table(name = "test")
public class TestPO implements Serializable {
    private static final long serialVersionUID = 2547259719754360814L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 外键
     */
    @Column(name = "rf_id")
    private Long rfId;
    /**
     * 活动名称
     */
    private String name;

    @Column(name = "create_time")
    private Date createTime;
}
