package com.mcoding.base.ui.service.attachment;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.mcoding.base.core.BaseService;
import com.mcoding.base.ui.bean.attachment.Attachment;
import com.mcoding.base.ui.bean.attachment.AttachmentExample;

public interface AttachmentService extends BaseService<Attachment, AttachmentExample>{
	
	/**
	 * 保存文件
	 * @param file
	 * @return
	 * @throws IOException 
	 */
	public Attachment saveFile(File file) throws IOException;
	
	/**
	 * 保存上传的文件
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public Attachment saveFile(MultipartFile file) throws Exception;

	/**
	 * 裁剪并保存文件
	 * @param image
	 * @return
	 * @throws IOException 
	 */
	public Attachment cropAndSaveFile(MultipartFile image, int x, int y, int w, int h) throws IOException;

}
