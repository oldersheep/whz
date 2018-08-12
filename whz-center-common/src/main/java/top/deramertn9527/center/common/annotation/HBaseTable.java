package top.deramertn9527.center.common.annotation;

import java.lang.annotation.*;

/**
 * HBase 注解
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface HBaseTable {

    String tableName();

    String familyName();

    String rowKeyColumnName();
}