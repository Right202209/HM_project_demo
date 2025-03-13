package org.qqhru.hmpt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVo {
    private Integer code;//响应码，1 代表成功; 0 代表失败
    private String msg;  //响应信息 描述字符串
    private Object data; //返回的数据

    //增删改 成功响应
    public static ResultVo success(){
        return new ResultVo(1,"success",null);
    }
    //查询 成功响应
    public static ResultVo success(Object data){
        return new ResultVo(1,"success",data);
    }
    //失败响应
    public static ResultVo error(String msg){
        return new ResultVo(0,msg,null);
    }
}