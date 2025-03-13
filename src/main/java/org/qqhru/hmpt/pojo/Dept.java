package org.qqhru.hmpt.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * entity 实体类
 * pojo javaBean vo dto domain po ... 实体类
 *
 * entity  domain  pojo   服务器与数据库的交互对象 放操作数据库实体类
 * vo : view object  专门浏览器跟服务器交互的对象
 * DTO : 专门用于数据传输的对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dept {
    private Integer id;
    private String name;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
