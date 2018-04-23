package org.activiti.dao;

import org.activiti.entity.WorkflowForm;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created with family.
 * author: cy
 * Date: 16/12/19
 * Time: 下午9:10
 * description:  帐号 dao
 */
@Repository
public interface WorkflowFormRepository extends Mapper<WorkflowForm> {



    @Select("<script>select f.* from acf_form f where 1=1 " +



            "<if test=\"createBy !='' and createBy !=null \"> and f.create_by = #{createBy} </if>" +
            "<if test=\"flag !='' and flag !=null \"> and f.flag = #{flag} </if>" +
            "<if test=\"delFlag !='' and delFlag !=null \"> and f.del_flag = #{delFlag} </if>" +
            "</script>")
    @ResultMap(value = "BaseResultMap" )
    List<WorkflowForm> queryForms(Map param);
}
