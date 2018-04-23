package org.yxyqcy.family.home.fbook.service.impl;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.yxyqcy.family.common.component.PageUtil;
import org.yxyqcy.family.common.config.Global;
import org.yxyqcy.family.common.model.PersistModel;
import org.yxyqcy.family.home.blog.entity.BlogClassify;
import org.yxyqcy.family.home.blog.util.BlogUtil;
import org.yxyqcy.family.home.fbook.dao.FBookRepository;
import org.yxyqcy.family.home.fbook.entity.FBook;
import org.yxyqcy.family.home.fbook.service.FBookService;
import org.yxyqcy.family.home.task.FamilyHomeBookToPdfTask;
import org.yxyqcy.family.sys.util.UserUtils;

import java.io.File;
import java.util.*;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;


/**
 * Created by yxyqcy on 2017-11-13 23:12:26.
 */
@Service
@Transactional(readOnly = true,propagation = Propagation.NOT_SUPPORTED)
public class FBookServiceImpl implements FBookService{

    @Autowired
    private FBookRepository fBookRepository;



    @Override
    public FBook selectOne(String businessId){
        return fBookRepository.selectByPrimaryKey(businessId);
    }

    @Override
    public List<FBook> queryFBooksByPagination(PageUtil pageUtil, FBook model) {
        Map param = new HashMap<String,Object>();
        param.put("createBy",model.getCreateBy());
        param.put("flag",model.getFlag());
        param.put("delFlag",model.getDelFlag());
        param.put("isTop",model.getIsTop());
        param.put("name",model.getName());
        //是否是public的
        param.put("isPublic",model.getIsPublic());
        param.put("publicFlag",model.getPublicFlag());
        param.put("classify",model.getClassify());
        return fBookRepository.queryFBooks(param);
    }

    @Override
    public List<FBook> queryFBooks(FBook model) {
        Map param = new HashMap<String,Object>();
        param.put("code",model.getCode());
        param.put("flag",model.getFlag());
        param.put("delFlag",model.getDelFlag());
        return fBookRepository.queryFBooks(param);
    }


    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public PersistModel persistFbook(FBook model){
        model.setCommonBusinessId();
        UserUtils.setCurrentPersistOperation(model);
        model.setIsTop(FBook.isNotTJ);
        this.generatorFbookFile(model);
        int line = fBookRepository.insertSelective(model);
        //FamilyLogger.sysInfo(UserUtils.getUser().getBusinessId(),UserUtils.getUser().getLoginName()+"新增了ID为"+user.getBusinessId()+"的用户");
        return new PersistModel(line);
    }

    /**
     * 构建   fbook file
     * @param model
     */
    private void generatorFbookFile(FBook model) {
        //配置目录
        String abPath =  Global.getConfig("base_upload_location") ;
        String stPath = Global.getConfig("book_upload_location") + File.separator + UserUtils.getUser().getBusinessId()
                + File.separator;
        //图片从临时目录中移出
        if(StringUtils.isNotEmpty(model.getBookUrl())){
            File file = new File(abPath + model.getBookUrl());
            String fileName = file.getName();
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            String destPah = abPath + stPath + model.getBusinessId() + "." + suffix;
            File dest = new File(destPah);
            //有源图片,无目标图 防止修改操作 不改图片
            if(file.exists() && !dest.exists()){
                try {
                    FileUtils.moveFile(file,dest);

                    JobDataMap dataMap = new JobDataMap();
                    //任务参数
                    dataMap.put(FamilyHomeBookToPdfTask.PDF_SOURCE_PATH,destPah);
                    dataMap.put(FamilyHomeBookToPdfTask.PDF_DEST_PATH,abPath + stPath + File.separator + model.getBusinessId());
                    dataMap.put(FamilyHomeBookToPdfTask.PDF_BOOK_ID,model.getBusinessId());

                    // 通过SchedulerFactory获取一个调度器实例
                    SchedulerFactory sf = new StdSchedulerFactory();
                    Scheduler sched = sf.getScheduler();
                    JobDetail jobDetail = JobBuilder.newJob(FamilyHomeBookToPdfTask.class)
                            //.withIdentity("bookToPdf","bookToPdfGroup")
                            .setJobData(dataMap).build();// 任务名，任务组，任务执行类

                    // 触发器
                    //CronTrigger trigger = new CronScheduleBuilder.("bookToPdfTrigger", "bookToPdfTriggerGroup");// 触发器名,触发器组
                    Trigger trigger = TriggerBuilder.newTrigger()
                            //.withIdentity("bookToPdfTrigger", "bookToPdfTriggerGroup")
                            .startNow()
                            .withSchedule(simpleSchedule()
                                    .withIntervalInSeconds(1)
                                    .withRepeatCount(1)).build();

                    // Tell quartz to schedule the job using our trigger
                    sched.scheduleJob(jobDetail, trigger);

                    // 启动调度器
                    sched.start();
                    model.setBookUrl(stPath + model.getBusinessId() + "." + suffix);
                    model.setBookCover(stPath +  model.getBusinessId()  + File.separator + "1.png");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public PersistModel removeFBook(String id) {
        FBook fBook = new FBook();
        fBook.setBusinessId(id);
        UserUtils.setCurrentRemoveOperation(fBook);
        /*逻辑删除*/
        int line = fBookRepository.logicalDelete(fBook);
        return new PersistModel(line);
    }

    @Override
    public List<String> browseBookById(String id) {
        String abPath =  Global.getConfig("base_upload_location") ;
        final List<String> list = new ArrayList<>();
        FBook example = new FBook();
        example.setBusinessId(id);
        example.setNotDeleted();
        //example.setIsTop(FBook.isTJ);
        FBook book = fBookRepository.selectOne(example);
        /*空*/
        if(null == book || StringUtils.isBlank(book.getBookUrl()))
            return list;
        String bookBaseUrl = book.getBookUrl();
        if(StringUtils.isBlank(bookBaseUrl))
            return list;
        bookBaseUrl = bookBaseUrl.substring(0,bookBaseUrl.lastIndexOf("."));
        File baseDir = new File(abPath + bookBaseUrl);
        final String  bookWebUrl = bookBaseUrl;
        /*文件夹*/
        if(!baseDir.exists())
            return list;
        String[] fileList = baseDir.list();
        if(0 == fileList.length)
            return list;
        Arrays.stream(fileList).sorted((a, b) -> {
            String aString = a.substring(0, a.lastIndexOf("."));
            String bString = b.substring(0, b.lastIndexOf("."));
            Integer aIndex = Integer.parseInt(aString);
            Integer bIndex = Integer.parseInt(bString);
            return aIndex.compareTo(bIndex);
        }).forEach((e)->{
            list.add(bookWebUrl + File.separator +  e);
        });
        return list;
    }

    @Override
    public FBook queryFBookById(String id) {

        return fBookRepository.selectByPrimaryKey(id);
    }

    @Override
    public List<Map> staticBookCountByClassify() {
        List<BlogClassify> classifyList = BlogUtil.getAllBlogClassify();
        List<Map> list = fBookRepository.queryStaticBookCountByClassify();
        for (Map map : list) {
            for (BlogClassify classify: classifyList) {
                //匹配
                if(map.get("classify").equals(classify.getType())) {
                    map.put("name",classify.getName());
                    break;
                }
            }
        }
        return list;
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public PersistModel topBlog(FBook book) {
        UserUtils.setCurrentMergeOperation(book);
        book.setIsPublic(null);//防止修改public
        int line = fBookRepository.updateBookState(book);
        return new PersistModel(line);
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public PersistModel publicBook(FBook book) {
        UserUtils.setCurrentMergeOperation(book);
        if(FBook.isNotPub.equals(book.getIsPublic()))
            book.setIsTop(FBook.isNotTJ);//改成private 自动取消推荐
        else
            book.setIsTop(null);//防止修改top
        int line = fBookRepository.updateBookState(book);
        return new PersistModel(line);
    }


    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public PersistModel updateBookStateSelective(FBook fBook) {
        int  result = fBookRepository.updateByPrimaryKeySelective(fBook);
        return new PersistModel(result);
    }
}
