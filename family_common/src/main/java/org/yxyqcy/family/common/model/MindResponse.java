package org.yxyqcy.family.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with family.
 * author: cy
 * Date: 16/6/28
 * Time: 下午3:08
 * description: md editor  上传  响应
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MindResponse {
    /*
        {errno: <错误号, 无错误则为 0>, msg: <错误信息>, data: {url: <返回的 URL>}}；
     */

    private Integer errno = 0 ;

    private String msg = "上传失败";


    private MindResponseData data;

    public MindResponse(Integer errno, String msg, String url) {
        this.errno = errno;
        this.msg = msg;
        this.data = new MindResponseData(url);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class MindResponseData {
        private String url = "";
    }


}
