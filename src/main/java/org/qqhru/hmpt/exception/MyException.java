package org.qqhru.hmpt.exception;

import lombok.extern.slf4j.Slf4j;
import org.qqhru.hmpt.vo.ResultVo;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class MyException {

    /**
     * 处理所有不可知异常
     */
    @ExceptionHandler(Exception.class)
    public ResultVo handleException(Exception e) {
        log.error("系统异常: ", e);
        return ResultVo.error("服务器繁忙，请稍后再试");
    }

    /**
     * 处理空指针异常
     */
    @ExceptionHandler(NullPointerException.class)
    public ResultVo handleNullPointerException(NullPointerException e) {
        log.error("空指针异常: ", e);
        return ResultVo.error("操作失败，存在空对象引用");
    }

    /**
     * 处理索引越界异常
     */
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResultVo handleIndexOutOfBoundsException(IndexOutOfBoundsException e) {
        log.error("索引越界异常: ", e);
        return ResultVo.error("操作失败，索引越界");
    }

    /**
     * 处理数据库主键冲突异常
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public ResultVo handleDuplicateKeyException(DuplicateKeyException e) {
        log.error("主键冲突异常: ", e);
        return ResultVo.error("数据已存在，请勿重复操作");
    }
}
