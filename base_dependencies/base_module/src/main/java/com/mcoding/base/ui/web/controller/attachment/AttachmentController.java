package com.mcoding.base.ui.web.controller.attachment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.JsonResult;
import com.mcoding.base.ui.bean.attachment.Attachment;
import com.mcoding.base.ui.persistence.attachment.AttachmentMapper;
import com.mcoding.base.ui.service.attachment.AttachmentService;
import com.mcoding.base.ui.utils.StoreUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="文件上传下载管理")
@Controller
@RequestMapping("attachment")
public class AttachmentController {
	public static Logger log = LoggerFactory.getLogger(AttachmentController.class);

	public static final String DOWN_URL_PREFIX = "attachment/front/downloadFile/";
	public static final String DISPLAY_URL_PREFIX = "attachment/front/displayFile/";
	public static final Long CACHE_EXPIRED_TIME = 30 * 24 * 60 * 60 * 1000l;

	@Autowired
	private AttachmentService attachmentService;

	@Autowired
	private AttachmentMapper attachmentMapper;

	@Value("#{sysConfig['resource.location']}")
	private String resourcePath;

	/**
	 * 上传附件
	 * 
	 * @param file
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@ApiOperation(value="上传附件", httpMethod="POST")
	@ResponseBody
	@RequestMapping(value = { "front/upload", "service/upload" })
	public JsonResult<Attachment> upload(@RequestParam(required = true) MultipartFile file,
			HttpServletRequest request) throws Exception {
		if (file ==null ||  file.isEmpty()) {
			throw new CommonException("上传的文件不能为空");
		}
		
		Attachment fileUpload = this.attachmentService.saveFile(file);
		
		JsonResult<Attachment> result = new JsonResult<>();
		result.setStatus("00");
		result.setMsg("success");
		result.setData(fileUpload);

		return result;
	}
	
	/**
	 * 上传附件
	 * 
	 * @param request
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@ApiOperation(value="上传附件(针对kindeditor)", httpMethod="POST")
	@RequestMapping(value = { "front/uploadForKindeditor", "service/uploadForKindeditor" })
	@ResponseBody
	public Map<String, Object> uploadForKindeditor(MultipartHttpServletRequest request) throws Exception {
		Map<String, MultipartFile> fileMap = request.getFileMap();
		
		Map<String, Object> result = new Hashtable<>();
		
		if (MapUtils.isEmpty(fileMap)) {
			result.put("error", "上传的文件不能为空");
//			return JsonUtilsForMcoding.writeValueAsString(result);
			return result;
		}
		
		List<Attachment> attachmentList = new ArrayList<>();
		Iterator<String> keyIterator = fileMap.keySet().iterator();
		while (keyIterator.hasNext()) {
			MultipartFile file = fileMap.get(keyIterator.next());
			
			Attachment attachment = this.attachmentService.saveFile(file);
			attachmentList.add(attachment);
		}
		String servername = StoreUtils.getStoreFromThreadLocal().getStoreDomain().split(";")[0];
		if (CollectionUtils.isEmpty(attachmentList) && attachmentList.size() >1) {
			List<String> urlList = new ArrayList<>();
			String titleName = new String(); 
			for(int i=0; i<attachmentList.size(); i++){
				urlList.add(attachmentList.get(i).getFileUrl());
				titleName = titleName + "," + attachmentList.get(i).getFileName();
			}

			result.put("url",  servername+urlList);
			result.put("title", titleName);
			
		}else{
			result.put("url", servername+attachmentList.get(0).getFileUrl());
			result.put("title", attachmentList.get(0).getFileName());
		}
		
		result.put("error", 0);
		
		
		return result;
//		return JsonUtilsForMcoding.writeValueAsString(result);
	}

	@ApiOperation(value="上传,并根据参数裁剪图片", httpMethod="POST")
	@RequestMapping("front/uploadAndCropImage")
	@ResponseBody
	public JsonResult<Attachment> uploadAndCropImage(HttpServletRequest request,
			@RequestParam(value = "attachment", required = true) MultipartFile image, int x, int y, int w, int h) throws IOException {
		if (image ==null ||  image.isEmpty()) {
			throw new CommonException("上传的文件不能为空");
		}
		
		if (x<0 || y<0 || w<=0 || h<=0) {
			throw new CommonException("裁剪的参数有误，无法进行保存");
		}
		
		if (!image.getContentType().matches("image/.*")) {
			throw new CommonException("上传的文件不是图片，无法进行裁剪保存");
		}
		
        Attachment fileUpload = this.attachmentService.cropAndSaveFile(image, x, y, w, h);
		
		JsonResult<Attachment> result = new JsonResult<>();
		result.setStatus("00");
		result.setMsg("success");
		result.setData(fileUpload);

		return result;
		
	}
	
	@ApiOperation(value ="在线查看文件", httpMethod="GET")
	@RequestMapping(value = "front/displayFile/{fileId}")
	public ModelAndView displayFile(@PathVariable int fileId, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (!this.isExpired(request, response)) {
				response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
				return null;
			}

			Attachment attachment = this.attachmentMapper.selectByPrimaryKey(fileId);
			if (attachment == null) {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 返回404状态码
				return null;
			}

			String fileAbsulutePath = this.resourcePath + File.separator
					+ attachment.getFilePath().replaceAll(Attachment.SEPARATOR, "\\" + File.separator)
					+ attachment.getFileRename();
			File file = new File(fileAbsulutePath);
			log.info("file exist[" + file.exists() + "]" + ",filePath:" + fileAbsulutePath);
			if (!file.exists()) {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 返回404状态码
				return null;
			}

			// 设置下载文件名, 设置expire
			this.setRespHeaderCache(response);

			FileInputStream inputStream = new FileInputStream(file);
			OutputStream outputStream = response.getOutputStream();
			byte[] tmp = new byte[1024];
			while (inputStream.read(tmp) != -1) {
				outputStream.write(tmp);
			}

			outputStream.flush();
			outputStream.close();
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
		}

		return null;
	}

	/**
	 * 下载附件
	 * 
	 * @param fileId
	 * @param request
	 * @param response
	 * @return
	 */
	@ApiOperation(value ="下载文件", httpMethod="GET")
	@RequestMapping(value = "front/downloadFile/{fileId}")
	@ResponseBody
	public ModelAndView downloadFile(@PathVariable int fileId, HttpServletRequest request,
			HttpServletResponse response) {
		try {

			Attachment attachment = this.attachmentMapper.selectByPrimaryKey(fileId);
			if (attachment == null) {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 返回404状态码
				return null;
			}

			String fileAbsulutePath = this.resourcePath + File.separator
					+ attachment.getFilePath().replaceAll(Attachment.SEPARATOR, "\\" + File.separator)
					+ attachment.getFileRename();
			File file = new File(fileAbsulutePath);
			log.info("file exist[" + file.exists() + "]" + ",filePath:" + fileAbsulutePath);
			if (!file.exists()) {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 返回404状态码
				return null;
			}

			response.reset();
			response.setHeader("Content-Disposition", "attachment; filename=\"" + attachment.getFileName() + "\"");
			response.setContentType("application/octet-stream;charset=UTF-8");
			// 设置下载文件名, 设置expire
//			this.setRespHeaderCache(response, URLEncoder.encode(attachment.getFileName(), "UTF-8"));

			FileInputStream inputStream = new FileInputStream(file);
			OutputStream outputStream = response.getOutputStream();
			byte[] tmp = new byte[1024];
			while (inputStream.read(tmp) != -1) {
				outputStream.write(tmp);
			}

			outputStream.flush();
			outputStream.close();
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
		}

		return null;
	}

	protected String getBaseUrl(HttpServletRequest request) {
		try {
			if (null == request) {
				log.error("请求对象为空，无法获取项目根UR");
				return null;
			}
			int port = request.getServerPort(); // 获取端口
			String portStr = (port == 80) ? "" : ":" + port; // 80端口可省略显示
			String baseUrl = request.getScheme() + "://" + request.getServerName() + portStr + request.getContextPath();
			return baseUrl;
		} catch (Exception e) {
			log.error("获取项目根访问URL出错", e);
			return null;
		}
	}

	public boolean isExpired(HttpServletRequest request, HttpServletResponse response) {
		long header = request.getDateHeader("If-Modified-Since");
		long now = System.currentTimeMillis();

		long expiredTime = 0l;
		if (header > 0) {
			expiredTime = header + CACHE_EXPIRED_TIME;
		}

		if (now > expiredTime) {
			// 如果超出缓存时间，过期
			return true;
		} else {
			return false;
		}
	}

	/**
	 *            缓存时间，单位 毫秒
	 * @param response
	 * @return
	 */
	public boolean setRespHeaderCache(HttpServletResponse response) {

		String maxAgeDirective = "max-age=" + (CACHE_EXPIRED_TIME / 1000);
		response.setHeader("Cache-Control", maxAgeDirective);

		response.addDateHeader("Last-Modified", System.currentTimeMillis());
		response.addDateHeader("Expires", System.currentTimeMillis() + CACHE_EXPIRED_TIME);

		response.setStatus(HttpServletResponse.SC_OK);
		return true;
	}

}
