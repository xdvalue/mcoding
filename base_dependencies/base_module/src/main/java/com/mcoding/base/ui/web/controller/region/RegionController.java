package com.mcoding.base.ui.web.controller.region;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.base.ui.bean.region.Region;
import com.mcoding.base.ui.bean.region.RegionExample;
import com.mcoding.base.ui.service.region.RegionService;
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

@Api(value="地区")
@Controller
@RequestMapping("region")
public class RegionController {
    @Resource
    protected RegionService regionService;

    @ApiIgnore
    @RequestMapping("service/toAddView")
    public ModelAndView toAddView() {
        return new ModelAndView("region/region/toAddView");
    }

    @ApiIgnore
    @RequestMapping("service/toMainView")
    public ModelAndView toMainView() {
        return new ModelAndView("region/region/toMainView");
    }

    @ApiIgnore
    @RequestMapping("service/toUpdateViewById")
    public ModelAndView toUpdateViewById(int id) {
        ModelAndView view = new ModelAndView();
        Region region = this.regionService.queryObjById(id);
        view.addObject("region", region);
        view.setViewName("region/region/toAddView");
        return view;
    }

    @ApiOperation(httpMethod="POST", value="创建地区")
    @RequestMapping("service/create")
    @ResponseBody
    public JsonResult<String> create(@RequestBody Region region) {
        JsonResult<String> result = new JsonResult<>();
        this.regionService.addObj(region);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="编辑地区")
    @RequestMapping("service/edit")
    @ResponseBody
    public JsonResult<String> edit(@RequestBody Region region) {
        if (region.getRegionId() == null || region.getRegionId() <=0) {
            throw new CommonException("id 为空，保存失败");
        }
        JsonResult<String> result = new JsonResult<>();
        this.regionService.modifyObj(region);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="删除地区")
    @RequestMapping("service/deleteById")
    @ResponseBody
    public JsonResult<String> deleteById(int id) {
        JsonResult<String> result = new JsonResult<>();
        this.regionService.deleteObjById(id);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="GET", value="查询地区")
    @RequestMapping("service/findByPage")
    @ResponseBody
    public PageView<Region> findByPage(@ApiParam(value="分页索引",defaultValue="0") @RequestParam(defaultValue="0") String iDisplayStart, @ApiParam(value="每页的数量",defaultValue="10") @RequestParam(defaultValue="10") String iDisplayLength, @ApiParam(value="查询条件") String sSearch) {
        PageView<Region> pageView = new PageView<>(iDisplayStart, iDisplayLength);
        RegionExample example = new RegionExample();
        example.setPageView(pageView);
        if (StringUtils.isNotBlank(sSearch)) {
            // TODO Auto-generated method stub
        }
        return this.regionService.queryObjByPage(example);
    }

    @ApiOperation(httpMethod="GET", value="根据parentId查询地区列表")
    @RequestMapping("service/findByParentId")
    @ResponseBody
    public List<Region> findByParentId( @ApiParam(value="parentId") Integer parentRegionId) {
        RegionExample example = new RegionExample();
        RegionExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentRegionId);
        return this.regionService.queryAllObjByExample(example);
    }
}