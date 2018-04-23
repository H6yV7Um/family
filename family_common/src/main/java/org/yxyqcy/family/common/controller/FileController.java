package org.yxyqcy.family.common.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.yxyqcy.family.common.config.Global;
import org.yxyqcy.family.common.constant.MessageConstant;
import org.yxyqcy.family.common.message.AjaxResponse;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created with family.
 * author: cy
 * Date: 16/6/28
 * Time: 下午3:28
 * description:  文件操作
 */
@Controller
public class FileController<T> extends PaginationController<T> {
    protected static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    protected static final String SEPARATOR;
    Logger logger = Logger.getLogger(FileController.class);

    static {
        SEPARATOR = File.separator;
    }

    protected String globalCommonFileUpload(MultipartFile multipartFile, String savingPatternOrFullName, String savingAbsolutePath) {
        String finalSavingPath;
        String returnFinalFileName;
        label32: {
            String fileExtendsName = "." + getSuffix(multipartFile.getOriginalFilename());
            finalSavingPath = "";
            savingPatternOrFullName = savingPatternOrFullName.toUpperCase();
            returnFinalFileName = "";
            switch(savingPatternOrFullName.hashCode()) {
                case 2090926:
                    if(savingPatternOrFullName.equals("DATE")) {
                        returnFinalFileName = getCurrentDateTimeString() + fileExtendsName;
                        finalSavingPath = savingAbsolutePath + SEPARATOR + returnFinalFileName;
                        break label32;
                    }
                    break;
                case 2616251:
                    if(savingPatternOrFullName.equals("UUID")) {
                        returnFinalFileName = getUUIDString() + fileExtendsName;
                        finalSavingPath = savingAbsolutePath + SEPARATOR + returnFinalFileName;
                        break label32;
                    }
            }

            returnFinalFileName = savingPatternOrFullName;
            finalSavingPath = savingAbsolutePath + SEPARATOR + savingPatternOrFullName;
        }

        File file = new File(finalSavingPath);
        if(!file.mkdirs()) {
            file.mkdirs();
        }

        try {
            multipartFile.transferTo(file);
        } catch (IllegalStateException var10) {
            var10.printStackTrace();
        } catch (IOException var11) {
            var11.printStackTrace();
        }

        return returnFinalFileName;
    }



    public static String getSuffix(String filename) {
        String suffix = "";
        int pos = filename.lastIndexOf(46);
        if(pos > 0 && pos < filename.length() - 1) {
            suffix = filename.substring(pos + 1);
        }

        return suffix;
    }


    protected static String getUUIDString() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

    protected static String getCurrentDateTimeString() {
        return simpleDateFormat.format(new Date());
    }


    protected void transformCommonFile(MultipartFile file,String tempPath,String idEncode){
        AjaxResponse ajax = new AjaxResponse();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = null;
        try {
            String abPath =  Global.getConfig("base_upload_location") ;

            String finalFile = this.globalCommonFileUpload(file,idEncode,abPath + tempPath);
            out = response.getWriter();
            ajax.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,tempPath + File.separator + finalFile);
            out.print(JSONObject.toJSONString(ajax,
                    SerializerFeature.WriteMapNullValue));
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
