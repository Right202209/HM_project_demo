package org.qqhru.hmpt.exception;

import org.qqhru.hmpt.vo.ResultVo;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 此类的目的 就是为了处理程序中 抛出的异常
 */
@RestControllerAdvice  //此注解登场 , 所有的异常都会执行当前类
public class MyException {
    //自己书写方法处理异常  Exception 也叫兜底异常  没有进入任何异常匹配的方法时 执行此方法
    @ExceptionHandler(Exception.class) //此注解用于 异常来匹配类型  Exception.class 只要是Exception就会执行此方法
    public ResultVo execute(Exception e){
        e.printStackTrace();//打印异常
        return ResultVo.error("对不起,服务器异常,请联系管理员");
    }
    @ExceptionHandler(NullPointerException.class)  //空指针所执行的方法
    public ResultVo execute2(){
        return  ResultVo.error("对不起,服务器有空对象");
    }

    @ExceptionHandler(IndexOutOfBoundsException.class) //数组越界异常
    public ResultVo execute3(){
        return  ResultVo.error("对不起,数组越界");
    }


    @ExceptionHandler(DuplicateKeyException.class) //数组越界异常
    public ResultVo execute4(){
        return  ResultVo.error("对不起,服务器已经存在该数据");
    }
}
