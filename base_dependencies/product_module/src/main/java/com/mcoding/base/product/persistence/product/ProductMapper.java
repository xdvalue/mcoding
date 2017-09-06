package com.mcoding.base.product.persistence.product;

import com.mcoding.base.core.PageView;
import com.mcoding.base.product.bean.product.Product;
import com.mcoding.base.product.bean.product.ProductExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ProductMapper {
    int countByExample(ProductExample example);

    int deleteByExample(ProductExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    List<Product> selectByExampleWithBLOBs(ProductExample example);

    List<Product> selectByExample(ProductExample example);

    Product selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByExampleWithBLOBs(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKeyWithBLOBs(Product record);

    int updateByPrimaryKey(Product record);

	List<Product> selectByExampleByPage(ProductExample example);

    List<Product> findConditionByPage(@Param("example")ProductExample example, @Param("categoryId") Integer categoryId);
}