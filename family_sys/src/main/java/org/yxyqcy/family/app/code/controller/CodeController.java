package org.yxyqcy.family.app.code.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yxyqcy.family.app.code.model.CodeSchema;
import org.yxyqcy.family.app.code.model.CodeTable;
import org.yxyqcy.family.app.code.service.CodeService;
import org.yxyqcy.family.common.constant.MessageConstant;
import org.yxyqcy.family.common.controller.PaginationController;
import org.yxyqcy.family.common.message.AjaxResponse;
import org.yxyqcy.family.sys.util.UserUtils;

import javax.servlet.ServletOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by lcy on 17/6/28.
 */
@Controller
@RequestMapping("/code")
public class CodeController extends PaginationController<CodeTable> {


    @Autowired
    private CodeService codeServiceImpl;

    /**
     * 连接数据库    schema
     * @param codeSchema
     * @param ajaxResponse
     * @return
     */
    @RequiresPermissions("sys:code:connect")
    @RequestMapping("connectDb")
    @ResponseBody
    public AjaxResponse connectDb(CodeSchema codeSchema, AjaxResponse ajaxResponse){
        List<CodeTable> result = codeServiceImpl.getCodeTablesBySchema(codeSchema);
        if(null == result || 0 == result.size())
            ajaxResponse.setErrorMessage("数据库连接信息错误,或数据库不存在数据表!",null);
        else
            ajaxResponse.setSuccessMessage(MessageConstant.MESSAGE_ALERT_SUCCESS,result);
        return ajaxResponse;
    }

    /**
     * 导出code package
     * @return
     */
    @RequestMapping(value = "constructCode")
    @ResponseBody   // springMVC 下载 报页面找不到
    public void constructCode(CodeSchema codeSchema) throws IOException{
        ServletOutputStream out = null;
        InputStream fin = null;
        File file = null;
        codeSchema.setUserId(UserUtils.getUser().getBusinessId());
        String path = codeServiceImpl.generateCode(codeSchema);
        try{
            if(null == path)
                return ;
            file = new File(path);
            //不存在
            if(null == file || !file.exists())
                return ;
            // response
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/force-download");
            response.addHeader("Content-Disposition", "attachment;filename=cyCode.zip");
            out = response.getOutputStream();
            fin = new FileInputStream(file);
            byte[] buffer = new byte[1024];//缓冲区
            int bytesToRead = -1;
            // 通过循环将读入的Word文件的内容输出到浏览器中
            while((bytesToRead = fin.read(buffer)) != -1) {
                out.write(buffer, 0, bytesToRead);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            if(fin != null) fin.close();
            if(out != null) out.close();
            //if(file != null) file.delete(); // 删除临时文件
        }
    }

}
