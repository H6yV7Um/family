package org.yxyqcy.family.common.model;

/**
 * Created with family.
 * author: cy
 * Date: 16/6/28
 * Time: 下午3:08
 * description: md editor  上传  响应
 */
public class MdResponse {
    /*
        上传的后台只需要返回一个 JSON 数据，结构如下：
         {
             success : 0 | 1,           // 0 表示上传失败，1 表示上传成功
                 message : "提示的信息，上传成功或上传失败及错误信息等。",
             url     : "图片地址"        // 上传成功时才返回
         }
     */

    private Integer success = 0 ;

    private String message = "上传失败";

    private String url = null;


    public MdResponse() {
    }

    public MdResponse(Integer success, String message, String url) {
        this.success = success;
        this.message = message;
        this.url = url;
    }

    public Integer getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getUrl() {
        return url;
    }
}
