package com.mcoding.base.dst.web.controller.promotion;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.base.dst.bean.promotion.DstPromotProductPoster;
import com.mcoding.base.dst.bean.promotion.DstPromotProductPosterExample;
import com.mcoding.base.dst.service.promotion.DstPromotProductPosterService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

@Api(value="产品推广二维码")
@Controller
@RequestMapping("dstPromotProductPoster")
public class DstPromotProductPosterController {
    @Resource
    protected DstPromotProductPosterService dstPromotProductPosterService;

    @ApiIgnore
    @RequestMapping("service/toAddView")
    public ModelAndView toAddView() {
        return new ModelAndView("order/dstPromotProductPoster/toAddView");
    }

    @ApiIgnore
    @RequestMapping("service/toMainView")
    public ModelAndView toMainView() {
        return new ModelAndView("order/dstPromotProductPoster/toMainView");
    }

    @ApiIgnore
    @RequestMapping("service/toUpdateViewById")
    public ModelAndView toUpdateViewById(int id) {
        ModelAndView view = new ModelAndView();
        DstPromotProductPoster dstPromotProductPoster = this.dstPromotProductPosterService.queryObjById(id);
        view.addObject("dstPromotProductPoster", dstPromotProductPoster);
        view.setViewName("order/dstPromotProductPoster/toAddView");
        return view;
    }

    @ApiOperation(httpMethod="POST", value="创建产品推广二维码")
    @RequestMapping("service/create")
    @ResponseBody
    public JsonResult<String> create(@RequestBody DstPromotProductPoster dstPromotProductPoster) {
        JsonResult<String> result = new JsonResult<>();
        this.dstPromotProductPosterService.addObj(dstPromotProductPoster);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="编辑产品推广二维码")
    @RequestMapping("service/edit")
    @ResponseBody
    public JsonResult<String> edit(@RequestBody DstPromotProductPoster dstPromotProductPoster) {
        if (dstPromotProductPoster.getId() == null || dstPromotProductPoster.getId() <=0) {
            throw new CommonException("id 为空，保存失败");
        }
        JsonResult<String> result = new JsonResult<>();
        this.dstPromotProductPosterService.modifyObj(dstPromotProductPoster);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="删除产品推广二维码")
    @RequestMapping("service/deleteById")
    @ResponseBody
    public JsonResult<String> deleteById(int id) {
        JsonResult<String> result = new JsonResult<>();
        this.dstPromotProductPosterService.deleteObjById(id);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="GET", value="查询产品推广二维码")
    @RequestMapping("service/findByPage")
    @ResponseBody
    public PageView<DstPromotProductPoster> findByPage(@ApiParam(value="分页索引",defaultValue="0") @RequestParam(defaultValue="0") String iDisplayStart, @ApiParam(value="每页的数量",defaultValue="10") @RequestParam(defaultValue="10") String iDisplayLength, @ApiParam(value="查询条件") String sSearch) {
        PageView<DstPromotProductPoster> pageView = new PageView<>(iDisplayStart, iDisplayLength);
        DstPromotProductPosterExample example = new DstPromotProductPosterExample();
        example.setPageView(pageView);
        if (StringUtils.isNotBlank(sSearch)) {
            // TODO Auto-generated method stub
        }
        return this.dstPromotProductPosterService.queryObjByPage(example);
    }
}