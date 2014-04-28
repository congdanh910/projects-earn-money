package com.dwsj.ws;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.axis2.context.MessageContext;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.dwsj.utils.Constant;
import com.dwsj.utils.Utils;

public class DWSJServive {
	private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.HH.mm");
	
	
	private MessageContext getContextPath(){
		return MessageContext.getCurrentMessageContext(); 
	}
	
	/*
	 * 1 : request success 0 : request fail -1 : type not accept
	 */
	public int uploadImage(byte[] data, String type) {
		try {
			if (!Utils.checkTypes(type) && data.length > 0 && getContextPath() != null) {
				return -1;
			}
//			File file = new File(folder.getAbsolutePath() + "/" + sdf.format(new Date()) + "." + type);
//			FileOutputStream outputStream = new FileOutputStream(file);
//			outputStream.write(data);
//			outputStream.flush();
//			outputStream.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
		return 1;
	}
}
