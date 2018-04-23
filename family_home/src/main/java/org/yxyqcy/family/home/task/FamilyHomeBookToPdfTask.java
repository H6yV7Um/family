package org.yxyqcy.family.home.task;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yxyqcy.family.common.model.PersistModel;
import org.yxyqcy.family.common.util.CutPDFToImageUtil;
import org.yxyqcy.family.home.fbook.entity.FBook;
import org.yxyqcy.family.home.fbook.service.FBookService;

/**
 * Created by lcy on 17/11/18.
 */
@Component
@DisallowConcurrentExecution //同一个时间 只能执行同一个Job
public class FamilyHomeBookToPdfTask implements Job {

    // 源文件路径
    public static final String PDF_SOURCE_PATH = "pdfSourcePath";
    // 目标文件夹路径
    public static final String PDF_DEST_PATH = "pdfDestPath";
    // book id
    public static final String PDF_BOOK_ID = "pdfBookId";

    @Autowired
    private FBookService fBookServiceImpl;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        //String jobName = jobExecutionContext.getJobDetail().getName();
        JobDataMap data = jobExecutionContext.getJobDetail().getJobDataMap();
        String source = data.getString(PDF_SOURCE_PATH);
        String dest = data.getString(PDF_DEST_PATH);
        String bookId = data.getString(PDF_BOOK_ID);
        CutPDFToImageUtil util = new CutPDFToImageUtil();
        int pages = util.changePdfToImgByPdfBox(source,dest);
        //修改页数
        FBook fBook = new FBook();
        fBook.setBusinessId(bookId);
        fBook.setPages(pages);
        PersistModel persistModel = fBookServiceImpl.updateBookStateSelective(fBook);
    }
}
