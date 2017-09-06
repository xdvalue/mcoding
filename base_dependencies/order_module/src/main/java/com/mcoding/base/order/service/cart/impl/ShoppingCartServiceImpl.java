package com.mcoding.base.order.service.cart.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.Constant;
import com.mcoding.base.core.PageView;
import com.mcoding.base.order.bean.cart.ShoppingCart;
import com.mcoding.base.order.bean.cart.ShoppingCartExample;
import com.mcoding.base.order.persistence.cart.ShoppingCartMapper;
import com.mcoding.base.order.service.cart.ShoppingCartService;
import com.mcoding.base.product.bean.product.Product;
import com.mcoding.base.product.service.product.ProductService;

@Service("shoppingCartService")
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Resource
    protected ShoppingCartMapper shoppingCartMapper;
    
    @Autowired
    protected ProductService productService;

    @CacheEvict(value={"shoppingCart"}, allEntries=true)
    @Override
    public void addObj(ShoppingCart t) {
        this.shoppingCartMapper.insertSelective(t);
    }

    @CacheEvict(value={"shoppingCart"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {
        this.shoppingCartMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"shoppingCart"}, allEntries=true)
    @Override
    public void modifyObj(ShoppingCart t) {
        if (t.getId() == null || t.getId() ==0) {
            throw new NullPointerException("id 为空，无法更新");
        }
        this.shoppingCartMapper.updateByPrimaryKeySelective(t);
    }

    @Cacheable(value="shoppingCart", key="'ShoppingCartService_' + #root.methodName + '_' +#id")
    @Override
    public ShoppingCart queryObjById(int id) {
        return this.shoppingCartMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="shoppingCart", key="'ShoppingCartService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public List<ShoppingCart> queryAllObjByExample(ShoppingCartExample example) {
        return this.shoppingCartMapper.selectByExample(example);
    }

    @Cacheable(value="shoppingCart", key="'ShoppingCartService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public PageView<ShoppingCart> queryObjByPage(ShoppingCartExample example) {
        PageView<ShoppingCart> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.shoppingCartMapper.selectByExampleByPage(example));
        return pageView;
    }

    @CacheEvict(value={"shoppingCart"}, allEntries=true)
    @Transactional
	@Override
	public void addProductIntoCart(Product product, int nums, int memberId) {
//		Product product = this.productService.queryProductDetailById(productId);
		if (!Constant.YES_INT.equals(product.getSaleStatus())) {
			throw new CommonException("产品还没有上架，无法放入购物车");
		}
		if (CollectionUtils.isEmpty(product.getPriceList())) {
			throw new CommonException("产品还没有标价，无法放入购物");
		}
		
	    ShoppingCartExample example = new ShoppingCartExample();
	    example.createCriteria().andMemberIdEqualTo(memberId).andProductIdEqualTo(product.getId());
	    
	    List<ShoppingCart> list = this.shoppingCartMapper.selectByExample(example);
	    if (CollectionUtils.isNotEmpty(list)) {
			ShoppingCart shoppingCart = list.get(0);
			nums = shoppingCart.getNums() + nums;
			if (nums <=0) {
				throw new CommonException("产品已经不能再少了");
			}
			shoppingCart.setNums(nums);
			this.modifyObj(shoppingCart);
		}else{
			if (nums <=0) {
				throw new CommonException("产品已经不能再少了");
			}
			
			ShoppingCart shoppingCart = new ShoppingCart();
			shoppingCart.setMemberId(memberId);
			shoppingCart.setNums(nums);
			shoppingCart.setProductId(product.getId());
			shoppingCart.setProductImg(product.getCoverImg());
			shoppingCart.setStoreId(product.getStoreId());
			shoppingCart.setProductName(product.getProductName());
//			shoppingCart.setType(type);
			this.addObj(shoppingCart);
		}
		
	}

    @Cacheable(value="shoppingCart", key="'ShoppingCartService_' + #root.methodName + '_'+ #scene + '_' + #memberId + '_' + #storeId")
	@Override
	public List<ShoppingCart> findByMember(String scene, int memberId, int storeId) {
		
    	ShoppingCartExample example = new ShoppingCartExample();
    	example.createCriteria()
    	       .andMemberIdEqualTo(memberId)
    	       .andStoreIdEqualTo(storeId);
    	
    	List<ShoppingCart> list = this.queryAllObjByExample(example);
    	for(int i=0; CollectionUtils.isNotEmpty(list) && i<list.size(); i++){
    		ShoppingCart shopCart = list.get(i);
    		shopCart.setProduct(this.productService.queryProductDetailById(scene, shopCart.getProductId()));
    	}
    	
		return list;
	}

    @CacheEvict(value={"shoppingCart"}, allEntries=true)
	@Override
	public void clearByMemberId(int memberId, int storeId) {
    	ShoppingCartExample example = new ShoppingCartExample();
    	example.createCriteria().andMemberIdEqualTo(memberId).andStoreIdEqualTo(storeId);
    	
    	this.shoppingCartMapper.deleteByExample(example);
	}

    @CacheEvict(value={"shoppingCart"}, allEntries=true)
    @Transactional
	@Override
	public void updateCarts(List<ShoppingCart> shopCartList, int memberId) {
		
		for(int i=0; CollectionUtils.isNotEmpty(shopCartList) && i< shopCartList.size(); i++){
			ShoppingCart shopCart = shopCartList.get(i);
			Product product = this.productService.queryObjById(shopCart.getProductId());
			
			if (!Constant.YES_INT.equals(product.getSaleStatus())) {
				throw new CommonException("产品["+product.getProductName()+"]还没有上架，无法放入购物车");
			}
			
			if (shopCart.getNums() ==null || shopCart.getNums() <=0) {
				throw new CommonException("产品["+product.getProductName()+"]已经不能再少了");
			}
			
			if (shopCart.getId() !=null) {
				ShoppingCart tmp = new ShoppingCart();
				tmp.setId(shopCart.getId());
				tmp.setNums(shopCart.getNums());
				this.modifyObj(tmp);
				continue;
			}
			
			ShoppingCartExample example = new ShoppingCartExample();
		    example.createCriteria().andMemberIdEqualTo(memberId).andProductIdEqualTo(product.getId());
		    
		    List<ShoppingCart> list = this.shoppingCartMapper.selectByExample(example);
		    if (CollectionUtils.isEmpty(list)) {
		    	ShoppingCart tmp = new ShoppingCart();
				tmp.setMemberId(memberId);
				tmp.setNums(shopCart.getNums());
				tmp.setProductId(product.getId());
				tmp.setProductImg(product.getCoverImg());
				tmp.setStoreId(product.getStoreId());
				tmp.setProductName(product.getProductName());
				this.addObj(tmp);
			}else{
				ShoppingCart tmp = new ShoppingCart();
				tmp.setId(list.get(0).getId());
				tmp.setNums(shopCart.getNums());
				this.modifyObj(tmp);
			}
		}
	}
}