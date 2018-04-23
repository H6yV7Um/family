package org.yxyqcy.family.sys.dictionary.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.yxyqcy.family.sys.dictionary.entity.Dictionary;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 
 * @author yangxuenan
 * description data dictionary
 *
 */
@Repository
public interface DictionaryRepository extends Mapper<Dictionary>{
	 /**
     * 根据id查询
     * @param id
     * @return
     */
    @Select("select * from sys_dictionary where id = #{id}")
    @ResultMap(value = "BaseResultMap")
    Dictionary get(@Param("id") String id);
    
    /**
     * 分页查询
     * @param dictionary
     * @return
     */
    @Select("<script>select sys_dictionary.*,sys_user.NAME as username  from sys_dictionary INNER JOIN sys_user ON sys_dictionary.create_by = sys_user.business_id where "
    		+ "sys_dictionary.DEL_FLAG='0' <if test=\"name !=null \">and sys_dictionary.name like concat('%',#{name},'%') </if> "
    		+ "</script>")
    /**
     * @Select  查询  第一列不能为null 否则查不出来  不知道是不是bug  建议id放在第一列
     */
    @ResultMap(value = "BaseResultMap" )
    List<Dictionary> queryDictionaries(Dictionary dictionary);

    /**
     * 查询所有类型
     */
	@Select("select type from sys_dictionary GROUP BY type")
	List<Dictionary> selectAllType();
	
	/**
	 * 根据类型查询
	 */
	@Select("select * from sys_dictionary where type=#{type}")
	@ResultMap(value = "BaseResultMap" )
	List<Dictionary> selectAllByType(@Param("type") String type);
}
