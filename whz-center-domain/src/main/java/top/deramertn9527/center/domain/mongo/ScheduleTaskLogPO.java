package top.deramertn9527.center.domain.mongo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * 类描述: 日志对象
 *
 */
@Document(collection = "whz_schedule_task_log")
@Data
public class ScheduleTaskLogPO implements Serializable {

    private static final long serialVersionUID = -8982411901810329046L;

    private Long id;

    private Date created;

    private Date startTime;

    private Date endTime;

    private Long useTime;

    private String ip;

    private Boolean execStatus = Boolean.TRUE;

    private String execBeanId;

    private Long execNum;
}
