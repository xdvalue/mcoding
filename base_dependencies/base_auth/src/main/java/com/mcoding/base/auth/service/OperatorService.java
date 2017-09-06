package com.mcoding.base.auth.service;

import java.util.List;

import com.mcoding.base.auth.bean.Operator;
import com.mcoding.base.auth.bean.OperatorExample;
import com.mcoding.base.core.BaseService;
import com.mcoding.base.core.PageView;

/**
 * Created by LiBing on 2014-07-17  14:02
 */
public interface OperatorService extends BaseService<Operator, OperatorExample> {

    PageView<Operator> queryOperatorsByPage(String menuId);

    List<Operator> queryOperatorsByMenuId(String menuId);

	void editOperatorsByMenuId(Operator operator);

    List<Operator> queryUserOperatorsByMenuId(String menuId, List<Integer> userRoleIds);

	PageView<Operator> queryRoleOperatorsByMenuId(int roleId, int menuId, List<Integer> userRoleIds);
}
