package org.yxyqcy.family.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.yxyqcy.family.common.config.Global;
import org.yxyqcy.family.common.controller.ResponseController;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by lcy on 17/3/28.
 */
@Controller
@RequestMapping("/support")
public class SupportController   extends ResponseController{


    /**
     * 根据byte生成 photo
     * service echart
     */
    @RequestMapping("/changePhotoFromByte")
    public void changePhotoFromByte(@RequestParam(value = "photoFirst",required = false) String photoFirst,
                                    @RequestParam(value = "photoSecond",required = false) String photoSecond){
        BASE64Decoder decoder = new BASE64Decoder();

        try {
            String[] url = photoFirst.split(",");
            String u = url[1];
            //data:image/png;base64, 去掉描述信息
            byte[] buffer = new BASE64Decoder().decodeBuffer(u);
            //生成图片
            OutputStream out = null;
            out = new FileOutputStream(new File(Global.getConfig("base_upload_location")
            + Global.getConfig("temp_upload_location") + File.separator + "2.jpg"));
            out.write(buffer);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

}
