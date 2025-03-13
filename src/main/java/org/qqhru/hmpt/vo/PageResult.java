package org.qqhru.hmpt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 页面使用的 分页对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult {
    private Long total ; //总记录数据
    private List rows; //分页的数据
    //Object表示任何类型数据
}
