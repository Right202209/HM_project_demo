package org.qqhru.hmpt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * pojo : 数据库和java之间交互的对象
 * vo :   服务器响应给浏览器的对象
 * dto:   浏览器传递给服务器的对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpDto {
    private String name ;
    private String gender ;
    private String begin ;
    private String end;

    private Integer page ;
    private Integer pageSize;
}
