<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mcoding" uri="http://mcoding.cn/jsp/common"%>

<div class="row">
	<div class="col-md-12">
		<div class="tabbable tabbable-custom boxless tabbable-reversed">
			<ul class="nav nav-tabs">
				<li id="tab_li_0" class="active">
					<a href="#tab_0" data-toggle="tab">
						基础信息
					</a>
				</li>
				<li id="tab_li_1">
					<a href="#tab_1" data-toggle="tab">
						价格信息
					</a>
				</li>
				<li id="tab_li_2">
					<a href="#tab_2" data-toggle="tab">
						图片信息
					</a>
				</li>
			</ul>
			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-reorder"></i>
								<c:choose>
									<c:when test="${product!=null}">修改产品</c:when>
									<c:otherwise>添加产品</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="portlet-body form">
							<form action="#" id="productForm" class="form-horizontal">
								<input type="hidden" id="id" name="id" value="${product.id}" />

								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">商品名称						    </label>
										<div class="col-md-9">
										<input type="text" name="productName" value="${product.productName}"
											class="form-control input-inline input-medium"
											placeholder="请输入商品名称" required>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">商品条形码(69开头)</label>
										<div class="col-md-9">
										<input type="text" name="barCode" value="${product.barCode}"
											class="form-control input-inline input-medium"
											placeholder="请输入商品条形码(69开头)" required>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">商品防伪编码（4位数）</label>
										<div class="col-md-9">
										<input type="text" name="fakeCode" value="${product.fakeCode}"
											class="form-control input-inline input-medium"
											placeholder="请输入4位数的商品防伪编码" required>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">商品编码						    </label>
										<div class="col-md-9">
										<input type="text" name="numberCode" value="${product.numberCode}"
											class="form-control input-inline input-medium"
											placeholder="请输入商品编码" required>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">商品类目</label>
										<div class="col-md-9">
										
										<mcoding:dicGroupSelectTag dicGroupCode="product_category" id="type" name="type" selectedItemValue="${product.type}"/>
										
										</div>
									</div>
									<!-- div class="form-group">
										<label class="col-md-3 control-label">商品类型</label>
										<div class="col-md-9">
										<select  class="form-control input-medium" name="type" id="type" value="${product.type}">
											<option value="1"<c:if test="${product.type == '1'}">selected</c:if> >商品</option>
											<option value="2"<c:if test="${product.type == '2'}">selected</c:if> >赠品</option>
										</select>
										</div>
									</div -->
									<div class="form-group">
										<label class="col-md-3 control-label">商品标签</label>
										<div class="col-md-9">
										<select  class="form-control input-medium" name="label" id="label" value="${product.label}">
											<option value="1"<c:if test="${product.type == '1'}">selected</c:if> >普通商品</option>
											<option value="2"<c:if test="${product.type == '2'}">selected</c:if> >内购商品</option>
										</select>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">商品主题广告语						    </label>
										<div class="col-md-9">
										<input type="text" name="slogan" value="${product.slogan}"
											class="form-control input-inline input-medium"
											placeholder="请输入商品主题广告语">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">商品封面图						    </label>
										<div class="col-md-9">
											<input type="text" name="coverImg" id="coverImg" value="${product.coverImg}" readonly="readonly" class="form-control input-inline input-medium"/>
											<input type="button" id="coverImgBtn" value="选择商品封面图" class="btn btn-primary kindeditorMageUpload"/>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">商品排序</label>
										<div class="col-md-9">
										<input type="text" name="sequence" value="${product.sequence}"
											class="form-control input-inline input-medium"
											placeholder="请输入商品排序">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">上架状态 </label>
										<div class="col-md-9">
										<input type="checkbox" id="saleStatus" name ="saleStatus" value="${product.saleStatus}"
											   class="make-switch" data-on="success" data-off="danger"<c:if test="${product.saleStatus == 1}">checked</c:if>>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">积分商城是否兑换状态</label>
										<div class="col-md-9">
										<input type="checkbox" id="giftExchangeStatus" name ="giftExchangeStatus" value="${product.giftExchangeStatus}"
											   class="make-switch" data-on="success" data-off="danger"<c:if test="${product.giftExchangeStatus == 1}">checked</c:if>>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">商品是否设定为套餐</label>
										<div class="col-md-9">
										<input type="checkbox" id="setStatus" name ="setStatus" value="${product.setStatus}"
											   class="make-switch" data-on="success" data-off="danger"<c:if test="${product.setStatus == 1}">checked</c:if>>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">商品是否限购</label>
										<div class="col-md-9">
										<input type="checkbox" id="limitStatus" name ="limitStatus" value="${product.limitStatus}"
											   class="make-switch" data-on="success" data-off="danger"<c:if test="${product.limitStatus == 1}">checked</c:if>>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">商品限购总数量						    </label>
										<div class="col-md-9">
										<input type="text" name="limitQuota" value="${product.limitQuota}"
											class="form-control input-inline input-medium"
											placeholder="请输入商品限购总数量">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">每人限购件数						    </label>
										<div class="col-md-9">
										<input type="text" name="limitBuyQuota" value="${product.limitBuyQuota}"
											class="form-control input-inline input-medium"
											placeholder="请输入每人限购件数">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">商品详细内容						    </label>
										<div class="col-md-9">
										<textarea class="kindEditor" id="content" name="content">
											${product.content}
										</textarea>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">商品功能介绍						    </label>
										<div class="col-md-9">
										<textarea class="kindEditor" id="introduce" name="introduce">
											${product.introduce}
										</textarea>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">商品摘要	</label>
										<div class="col-md-9">
										<textarea class="kindEditor" id="summary" name="summary">
											${product.summary}
										</textarea>
										</div>
									</div>


									<div class="form-actions">
										<div class="col-md-offset-3 col-md-9">
											<button type="button" class="btn purple nextBtn" value="0">
												<i class="fa fa-check"></i> 下一步
											</button>
											<%--<button href="product/service/toMainView.html" type="button" class="btn default ajaxify">返 回</button>--%>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>

				<%--价格信息--%>
				<div class="tab-pane" id="tab_1">
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-reorder"></i>
								<c:choose>
									<c:when test="${edit!=null}">修改价格</c:when>
									<c:otherwise>增加价格</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="portlet-body form">
							<form action="#" id="priceForm" class="form-horizontal">
								<div class="form-body">
									<c:choose>
										<c:when test="${priceList!=null && priceList.size()>0 }">
											<c:forEach var="price" items="${priceList}" varStatus="status">
												<div class="form-group">
													<label class="control-label col-md-3">${price.sceneName}</label>
													<div class="col-md-9">
														<div class="input-inline input-medium">
															<input type="text" name="value" value="${price.value}" class="form-control touchspin" required>
															<input type="hidden" name="id" value="${price.id}"/>
														</div>
														<span class="help-block">
												<code>以分为单位录入</code>
											</span>
													</div>
												</div>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<div class="form-group">
												<label class="control-label col-md-3">原价</label>
												<div class="col-md-9">
													<div class="input-inline input-medium">
														<input type="text" name="value" class="form-control touchspin" required>
													</div>
													<span class="help-block">
															<code>以分为单位录入</code>
														</span>
												</div>
											</div>

											<div class="form-group">
												<label class="control-label col-md-3">销售价</label>
												<div class="col-md-9">
													<div class="input-inline input-medium">
														<input type="text" name="value" class="form-control touchspin" required>
													</div>
													<span class="help-block">
															<code>以分为单位录入</code>
														</span>
												</div>
											</div>
										</c:otherwise>
									</c:choose>
								</div>

								<div class="form-actions">
									<div class="col-md-offset-3 col-md-9">
										<button type="button" class="btn purple preBtn" value="1">
											<i class="fa fa-check"></i> 上一步
										</button>
										<button type="button" class="btn purple nextBtn" value="1">
											<i class="fa fa-check"></i> 下一步
										</button>
									</div>
								</div>
							</div>
							</form>
						</div>
				</div>

				<%--图片信息--%>
				<div class="tab-pane" id="tab_2">
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-reorder"></i>
								<c:choose>
									<c:when test="${edit!=null}">修改图片</c:when>
									<c:otherwise>增加图片</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="portlet-body form">
							<form action="#" id="imgForm" class="form-horizontal">
								<%--<input type="hidden" name="urlValues" id="urlValues" value="${product.url}">--%>
								<div class="form-body">
									<input type="button" id="addImgBtn" value="添加图片" class="btn btn-primary  fileinput-button"/>
									<div id="imgUploadInput"></div>


									</div>
								</form>
						</div>
					</div>

					<div class="form-actions">
						<div class="col-md-offset-3 col-md-9">
							<button type="button" class="btn purple preBtn" value="2">
								<i class="fa fa-check"></i> 上一步
							</button>
							<button
									<c:choose>
										<c:when test="${product!=null}">id="singleUpdate"</c:when>
										<c:otherwise>id="singleAdd"</c:otherwise>
									</c:choose>
									type="button" class="btn purple">
								<i class="fa fa-check"></i> 提 交
							</button>
							<button id="backListPage"
									href="product/service/toMainView.html" type="button"
									class="btn default ajaxify">返 回</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script src="${basePath}resources/js/common/kindeditor.js" type="text/javascript"></script>
<script src="${basePath}resources/js/custom/product/product/add.js" type="text/javascript"></script>
<script type="text/javascript">
    DataTableList.init();
</script>
