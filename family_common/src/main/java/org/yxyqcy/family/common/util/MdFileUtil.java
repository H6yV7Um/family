package org.yxyqcy.family.common.util;

import org.pegdown.PegDownProcessor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by lcy on 17/2/17.
 */
public class MdFileUtil {

    /**
     * md to html
     * 转格式可能有问题(#haha  # haha 空格样式问题) 暂不使用
     * @return
     */
    public static String conversionHtmlFromMd(String mdContent){
        PegDownProcessor pdp = new PegDownProcessor(Integer.MAX_VALUE);
        return pdp.markdownToHtml(mdContent);
    }
}
