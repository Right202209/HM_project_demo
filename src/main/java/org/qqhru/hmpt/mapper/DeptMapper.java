package org.qqhru.hmpt.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.qqhru.hmpt.pojo.Dept;

import java.util.List;

public interface DeptMapper {
    //查询部门数据
    @Select("select * from dept")
    List<Dept> findAll();

    //删除部门
    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    //新增部门
    @Insert("insert into dept values(#{id},#{name},#{createTime},#{updateTime})")
    void save(Dept dept);

    //修改部门名称
    @Update("update dept set name = #{name},update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);

    //检索部门
    @Select("select * from dept where id = #{id}")
    Dept findById(Integer id);
}
