package com.hui.mapper;

import com.hui.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Mapper
@Repository
public interface OrderMapper {

    @Select("select * from tb_order where id = #{id}")
    Order findById(Long id);
}
