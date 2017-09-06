package com.mcoding.base.ui.service.attachment.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.PageView;
import com.mcoding.base.ui.bean.attachment.Attachment;
import com.mcoding.base.ui.bean.attachment.AttachmentExample;
import com.mcoding.base.ui.bean.store.Store;
import com.mcoding.base.ui.persistence.attachment.AttachmentMapper;
import com.mcoding.base.ui.service.attachment.AttachmentService;
import com.mcoding.base.ui.utils.StoreUtils;
import com.mcoding.base.ui.web.controller.attachment.AttachmentController;
import com.mcoding.base.utils.image.ImageUtils;

@Service
public class AttachmentServiceImpl implements AttachmentService{

	@Value("#{sysConfig['resource.location']}")
	protected String resourcePath;
	
	@Resource
	protected AttachmentMapper attachmentMapper;
	
	/**
	 * 创建保存路径的相对路径，如果找到store，就加storeId为顶层目录
	 * @return
	 */
	private String createRelateFolderPath(){
		Store store = StoreUtils.getStoreFromThreadLocal();
		if (store == null) {
			String relateFolderPath = DateFormatUtils.format(new Date(),
					"yyyy" + File.separator + "MM" + File.separator + "dd" + File.separator);
			return relateFolderPath;
		}else{
			String relateFolderPath = "store" +store.getId() + File.separator + DateFormatUtils.format(new Date(),
					"yyyy" + File.separator + "MM" + File.separator + "dd" + File.separator);
			return relateFolderPath;
		}
		
	}
	
	/**
	 * 创建 保存的目标文件
	 * @param fileSuffix
	 * @return
	 */
	private File createSaveTarget(String fileSuffix, String relateFolderPath){
		String absoluteFolderRootPath = resourcePath + File.separator;

		String folderPathStr = absoluteFolderRootPath + relateFolderPath;
		File folderPath = new File(folderPathStr);
		if (!folderPath.exists()) {
			folderPath.mkdirs();
		}

		String fileName = String.valueOf(System.currentTimeMillis()) + "_" + (int) (Math.random() * 1000000000) + "." + fileSuffix;
		File tmp = new File(folderPathStr, fileName);
		return tmp;
	}
	
	@Override
	public Attachment saveFile(File file) throws IOException {
		if (file == null || !file.exists()) {
			throw new NullPointerException("保存的文件不存在");
		}
		
		String relateFolderPath = this.createRelateFolderPath();
		String suffix = FilenameUtils.getExtension(file.getName());
		if (StringUtils.isBlank(suffix)) {
			throw new CommonException("不接受没有后缀名的文件");
		}
		
		File saveTarget = this.createSaveTarget("jpg", relateFolderPath);
		FileUtils.copyFile(file, saveTarget);
		
		Attachment attachment = new Attachment();
		attachment.setCreateTime(new Date());
		attachment.setFileName(saveTarget.getName());
		attachment.setFileRename(saveTarget.getName().toLowerCase());
		attachment.setFilePath(relateFolderPath.replaceAll("\\" + File.separator, Attachment.SEPARATOR));
		attachment.setFileSuffix(suffix);
		attachment.setFileSize(Long.valueOf(FileUtils.readFileToByteArray(file).length).doubleValue());
		attachment.setFileSaveDes(Attachment.FILE_SAVE_DES_LOCAL);
		this.attachmentMapper.insertSelective(attachment);
		
		String url = AttachmentController.DISPLAY_URL_PREFIX + attachment.getId();
		
		Attachment attachmentTmpe = new Attachment();
		attachmentTmpe.setId(attachment.getId());
		attachmentTmpe.setFileUrl(url);
		this.attachmentMapper.updateByPrimaryKeySelective(attachmentTmpe);

		attachment.setFileUrl(url);
		return attachment;
	}
	
	@CacheEvict(value={"attachmentService"}, allEntries=true)
	@Override
	public Attachment saveFile(MultipartFile file) throws Exception {
		
		String relateFolderPath = this.createRelateFolderPath();
		
		String fileSuffix = FilenameUtils.getExtension(file.getOriginalFilename());
		
		File tmp = this.createSaveTarget(fileSuffix, relateFolderPath);
		
		file.transferTo(tmp);

		Attachment attachment = new Attachment();
		attachment.setCreateTime(new Date());
		attachment.setFileName(file.getOriginalFilename());
		attachment.setFileRename(tmp.getName());
		attachment.setFilePath(relateFolderPath.replaceAll("\\" + File.separator, Attachment.SEPARATOR));
		attachment.setFileSuffix(fileSuffix);
		attachment.setFileSize(Long.valueOf(file.getSize()).doubleValue());
		attachment.setFileSaveDes(Attachment.FILE_SAVE_DES_LOCAL);
		this.attachmentMapper.insertSelective(attachment);
		
		String url = AttachmentController.DISPLAY_URL_PREFIX + attachment.getId();
		
		Attachment attachmentTmpe = new Attachment();
		attachmentTmpe.setId(attachment.getId());
		attachmentTmpe.setFileUrl(url);
		this.attachmentMapper.updateByPrimaryKeySelective(attachmentTmpe);

		attachment.setFileUrl(url);
		return attachment;
	}
	
	@CacheEvict(value={"attachmentService"}, allEntries=true)
	@Override
	public Attachment cropAndSaveFile(MultipartFile file, int x, int y, int desWidth, int desHeight) throws IOException {
        
		String relateFolderPath = this.createRelateFolderPath();
		
		String fileSuffix = FilenameUtils.getExtension(file.getOriginalFilename());
		
		File tmp = this.createSaveTarget(fileSuffix, relateFolderPath);
		
		BufferedImage desImage = ImageUtils.cropImage(file.getInputStream(), x, y, desWidth, desHeight);
		ImageIO.write(desImage, fileSuffix, tmp);
        
		Attachment attachment = new Attachment();
		attachment.setCreateTime(new Date());
		attachment.setFileName(file.getOriginalFilename());
		attachment.setFileRename(tmp.getName());
		attachment.setFilePath(relateFolderPath.replaceAll("\\" + File.separator, Attachment.SEPARATOR));
		attachment.setFileSuffix(fileSuffix);
		attachment.setFileSize(Long.valueOf(file.getSize()).doubleValue());

		this.attachmentMapper.insertSelective(attachment);

		return attachment;
	}
	
	@CacheEvict(value={"attachmentService"}, allEntries=true)
	@Override
	public void addObj(Attachment t) {
		this.attachmentMapper.insertSelective(t);
	}

	@CacheEvict(value={"attachmentService"}, allEntries=true)
	@Override
	public void deleteObjById(int id) {
		this.attachmentMapper.deleteByPrimaryKey(id);
	}

	@CacheEvict(value={"attachmentService"}, allEntries=true)
	@Override
	public void modifyObj(Attachment t) {
		if (t.getId() == null || t.getId() == 0) {
			throw new NullPointerException("id 为空，不能修改");
		}
		this.attachmentMapper.updateByPrimaryKeySelective(t);
	}

	@Cacheable(value="attachmentService", key="'AttachmentService_' + #root.methodName + '_' +#id")
	@Override
	public Attachment queryObjById(int id) {
		return this.attachmentMapper.selectByPrimaryKey(id);
	}

	@Cacheable(value="attachmentService", key="'AttachmentService_' + #root.methodName + '_'+ #example.toJson()")
	@Override
	public List<Attachment> queryAllObjByExample(AttachmentExample example) {
		return this.attachmentMapper.selectByExample(example);
	}

	@Cacheable(value="attachmentService", key="'AttachmentService_' + #root.methodName + '_'+ #example.toJson()")
	@Override
	public PageView<Attachment> queryObjByPage(AttachmentExample example) {
		PageView<Attachment> pageView = example.getPageView();
		if (pageView == null) {
			pageView = new PageView<>(1, 10);
			example.setPageView(pageView);
		}
		pageView.setQueryResult(this.attachmentMapper.selectByExampleByPage(example));
		return pageView;
	}

}
