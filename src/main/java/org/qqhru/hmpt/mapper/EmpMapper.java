package org.qqhru.hmpt.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.qqhru.hmpt.dto.EmpDto;
import org.qqhru.hmpt.pojo.Emp;

import java.util.List;

public interface EmpMapper {

    //此处不要使用注解: 因为后续还做条件查询
    List<Emp> findByPage(EmpDto empDto);


    //保存员工对象
    @Insert("insert into emp values( #{id},#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime},#{password})")
    void save(Emp emp);

    void delete(Integer[] ids);

    //根据id查询员工对象
    @Select("select * from emp where id = #{id}")
    Emp findById(Integer id);

    //更新员工对象
    @Update("update emp set username = #{username},name = #{name},gender = #{gender},image = #{image},job = #{job},entrydate = #{entrydate},dept_id = #{deptId},create_time = #{createTime},update_time = #{updateTime} where id = #{id}")
    void update(Emp emp);

    @Select("select * from emp where username=#{username} and password=#{password}")
    Emp findByNameAndPwd(Emp emp);
}
