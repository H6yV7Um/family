package org.yxyqcy.family.sys.mapper.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.mapping.MappedStatement;
import org.yxyqcy.family.common.util.anno.OperateFlagAnno;
import org.yxyqcy.family.sys.mapper.FamilyMapperCons;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.entity.EntityTable;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

import java.lang.reflect.Field;
import java.util.*;

import static tk.mybatis.mapper.mapperhelper.EntityHelper.getEntityTable;

/**
 * Created by lcy on 17/11/16.
 *
 * 逻辑删除 mapper
 */
public class LogicalDeleteMapperImpl  extends MapperTemplate {






    public LogicalDeleteMapperImpl(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    /**
     * 逻辑删除
     * @param record
     * @return
     */
    public String logicalDelete(MappedStatement record){
        //WebappClassLoader 加载, 使用 cacheConstructor ,未加载属性,所以需要实例化,再获取class
        Class<?> entityClass = getEntityClass(record);
        //获取表的各项属性
        EntityTable table = getEntityTable(entityClass);
        Set<EntityColumn> cols = table.getEntityClassColumns();
        //获取表的各项属性 数组
        List<Field> fields = new ArrayList<Field>();
        Object t = null;
        Class tClass = null;
        try {
            t = Class.forName(entityClass.getName()).newInstance();
            tClass = t.getClass();
            while(Object.class != tClass){
                fields.addAll(Arrays.asList(tClass.getDeclaredFields()));
                tClass = tClass.getSuperclass();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //开始拼sql
        StringBuilder sql = new StringBuilder();
        //sql.append(" <script> "); //不支持script
        sql.append(SqlHelper.updateTable(entityClass, this.tableName(entityClass)));
        sql.append(" <trim prefix=\"SET\" suffixOverrides=\",\"> ");
        Set<EntityColumn> columnList = EntityHelper.getColumns(entityClass);
        Iterator var6 = columnList.iterator();
        //遍历
        while(var6.hasNext()) {
            EntityColumn column = (EntityColumn)var6.next();
            for (int i = 0; i < fields.size(); i++) {
                if(!column.getProperty().equals(fields.get(i).getName()))
                    continue;
                OperateFlagAnno meta = fields.get(i).getAnnotation(OperateFlagAnno.class);
                /*逻辑操作字段*/
                if(meta != null){
                    String jdbcType = meta.jdbcTypeName();
                    /*时间戳*/
                    if(StringUtils.isNotBlank(jdbcType) &&
                            FamilyMapperCons.JdbcType.TIMESTAMP.equals(jdbcType))
                        sql.append("<if test=\"" + column.getProperty() + " !=null \" > "
                                + column.getColumn()
                                + " = #{" + column.getProperty() + "} , </if>");

                    else
                        sql.append("<if test=\"" + column.getProperty() + " !=null and " +
                            column.getProperty() + " !='' \" > " + column.getColumn()
                            + " = #{" + column.getProperty()  + "} , </if>");
                }
                break;
            }
        }
        sql.append(" </trim> ");
        sql.append(SqlHelper.wherePKColumns(entityClass));
        //sql.append(" </script> ");
        return sql.toString();
    }
}
