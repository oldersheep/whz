package top.deramertn9527.center.domain.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;


@Data
public class MongoSequence {
    @Id
    private String id;
    private Long seq;
}
