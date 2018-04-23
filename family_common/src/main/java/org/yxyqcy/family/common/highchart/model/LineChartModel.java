package org.yxyqcy.family.common.highchart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.yxyqcy.family.common.highchart.util.ChartModelUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class LineChartModel extends BaseChartModel{

    private List<Series> series;

    private XAxis xAxis;


    public List<Series> getSeries() {
        return series;
    }

    public void setSeries(List<Series> series) {
        this.series = series;
    }

    public XAxis getxAxis() {
        return xAxis;
    }

    public void setxAxis(XAxis xAxis) {
        this.xAxis = xAxis;
    }
    
    public void setEmpty() {

        this.setSeries(new ArrayList<Series>());
        XAxis axis = new XAxis();
        axis.setCategories(new ArrayList<String>());
        this.setxAxis(axis);
    }

    //分类
    public class Series{
        private String name;

        private List<Object> data ;

        public Series() {
        }

        public Series(String name, List<Object> data) {
            this.name = name;
            this.data = data;
        }

        public String getName() {
        	
        	
        	//格式字符串  显示名称
            if("direction".equals(getItem())){
                if("1".equals(name)){
                    return "南";
                }
                else if("2".equals(name)){
                    return " 北";
                }
                else {
                    return "";
                }
            }

            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Object> getData() {
            return data;
        }

        public void setData(List<Object> data) {
            this.data = data;
        }
    }



    // 分类
    class XAxis{

        private boolean crosshair;

        private List<String> categories ;

        public List<String> getCategoriesDate() {


            return categories;
        }

        public void setCategoriesDate(List<String> categoriesDate) {
            this.categoriesDate = categoriesDate;
        }
        @JsonIgnore
        private List<String> categoriesDate ;

        public XAxis() {
        }

        public boolean isCrosshair() {
            return crosshair;
        }

        public void setCrosshair(boolean crosshair) {
            this.crosshair = crosshair;
        }

        public List<String> getCategories() {
            return categories;
        }

        public void setCategories(List<String> categories) {
            this.categories = categories;
        }
    }

    /**
     * 折线图转换
     * @param mapChart
     * @param item
     */
    public void generateFromMap(List<Map> mapChart, String item, Integer timeSqu, String startTime, String endTime) {
        //构建x轴
        try {
            this.generateDate(timeSqu, startTime, endTime);
            //遍历 mapChart
            for (Map map : mapChart){
            	String itemName= ChartModelUtil.resultMapToString(map, "ITEM");
            	Double count = Double.parseDouble(ChartModelUtil.resultMapToString(map, "ITEMCOUNT"));
                String duration = ChartModelUtil.resultMapToString(map, "DURATION");
                Series series = this.getSeriesByItemName(itemName);
                int index = this.getIntemNameIndex(duration);
                series.getData().set(index,count);
            }



        }catch (Exception e){
        	e.printStackTrace();
        }


    }

    /**
     * 根据itemName 找index
     * @param duration
     * @return
     */
    private int getIntemNameIndex(String duration) {
        //补0  start  1991-3-3   1991-03-03
        String[] durationS = duration.split("-");
        if(durationS.length == 2){
            if(durationS[1].startsWith("0")){
                duration = durationS[0] + "-" +durationS[1].substring(1);
            }
        }
        else if(durationS.length == 3){
            if(durationS[1].startsWith("0") && durationS[2].startsWith("0") ){
                duration = durationS[0] + "-" +durationS[1].substring(1) +  "-" +durationS[2].substring(1);;
            }else if (durationS[1].startsWith("0")){
                duration = durationS[0] + "-" +durationS[1].substring(1) +  "-" +durationS[2];
            }else if (durationS[2].startsWith("0")){
                duration = durationS[0] + "-" +durationS[1] +  "-" +durationS[2].substring(1);;
            }
        }
        //补0  end
        int index = 0 ;
        List<String> list = this.getxAxis().getCategoriesDate();

        for (int i = 0 ; i < list.size() ; i ++ ) {
            if(null != duration && duration.equals(list.get(i))){
                index = i ;
                break;
            }
        }
        return index;
    }

    /**
     * 根据itemName  获取 series
     * @param itemName
     * @return
     */
    private Series getSeriesByItemName(String itemName) {
    	boolean flag = false;
        Series series = null;
        if(null == this.getSeries()){
            List<Series> seriesList = new ArrayList<Series>();
            List<Object> data = new ArrayList<Object>();
            for(int i = 0 ; i < this.getxAxis().getCategories().size(); i++ )
                data.add(0);
            series = new Series();
            series.setName(itemName);
            series.setData(data);
            seriesList.add(series);
            this.setSeries(seriesList);
        }
        else {
            for(Series s : this.getSeries()){
                if(itemName != null && itemName.equals(s.getName())){
                    series = s;
                    flag = true;
                    break;
                }
            }
            if(!flag){
            	List<Object> data = new ArrayList<Object>();
            	for(int i = 0 ; i < this.getxAxis().getCategories().size(); i++ )
                    data.add(0);
            	series = new Series();
                series.setName(itemName);
                series.setData(data);
                this.getSeries().add(series);
            }
        }
        return series;
    }


    /**
     * 构建categories 显示用  categoriesDate 取值比较用
     * @param timeSqu
     * @param startTime
     * @param endTime
     * @throws Exception
     */
    public void generateDate(Integer timeSqu, String startTime, String endTime) throws Exception {
        List<String> categories = new ArrayList<String>();
        List<String> categoriesDate = new ArrayList<String>();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        XAxis axis = new XAxis();
        if(4 == timeSqu){//年
            Calendar calS = Calendar.getInstance();
            Calendar calE = Calendar.getInstance();
            calS.setTime(sdf.parse(startTime));
            calE.setTime(sdf.parse(endTime));
            //开始年 结束年
            int yearS = calS.get(Calendar.YEAR);
            int yearE = calE.get(Calendar.YEAR);
            int duration = yearE - yearS + 1 ;
            for(int i = 0 ; i < duration ; i ++,yearS++){
                categories.add("" + yearS);
                categoriesDate.add("" + yearS);
                //结束跳出
                if(0 == yearE - yearS)
                    break;
            }

        }else if(7 == timeSqu){//月
            Calendar calS = Calendar.getInstance();
            Calendar calE = Calendar.getInstance();
            calS.setTime(sdf.parse(startTime));
            calE.setTime(sdf.parse(endTime));
            //开始月 结束月
            int yearS = calS.get(Calendar.MONTH);
            int yearE = calE.get(Calendar.MONTH);
            int result = ChartModelUtil.getMonth(sdf.parse(startTime),sdf.parse(endTime))+1;
            result = (result == 0 ? 0 : Math.abs(result));
            for(int i = 0 ; i < result ; i ++){
                categories.add("" + calS.get(Calendar.YEAR) + "-" + (calS.get(Calendar.MONTH)+1));
                categoriesDate.add("" + calS.get(Calendar.YEAR) + "-" + (calS.get(Calendar.MONTH)+1));
                //12月 转到 下一年1月
                if(11 == calS.get(Calendar.MONTH)){
                    calS.add(Calendar.YEAR,1);
                    calS.set(Calendar.MONTH,0);
                }
                else {
                    calS.add(Calendar.MONTH, 1);
                }

            }

        }else if(10 == timeSqu){//日
            Calendar calS = Calendar.getInstance();
            Calendar calE = Calendar.getInstance();
            calS.setTime(sdf.parse(startTime));
            calE.setTime(sdf.parse(endTime));
            //开始年 结束年
            int yearS = calS.get(Calendar.MONTH);
            int yearE = calE.get(Calendar.MONTH);
            int result = ChartModelUtil.daysBetween(sdf.parse(startTime),sdf.parse(endTime))+1;
            result = (result == 0 ? 0 : Math.abs(result));
            for(int i = 0 ; i < result ; i ++){
                categories.add("" + calS.get(Calendar.YEAR) + "-" + (calS.get(Calendar.MONTH)+1)
                +"-"+(calS.get(Calendar.DAY_OF_MONTH)));
                categoriesDate.add("" + calS.get(Calendar.YEAR) + "-" + (calS.get(Calendar.MONTH)+1)
                        +"-"+(calS.get(Calendar.DAY_OF_MONTH)));
                //当前月  天超出
                if(calS.getActualMaximum(Calendar.DAY_OF_MONTH) == calS.get(Calendar.DAY_OF_MONTH)){
                    //是否为12月
                    if(11 == calS.get(Calendar.MONTH)){
                        calS.add(Calendar.YEAR,1);
                        calS.set(Calendar.MONTH,0);
                        calS.set(Calendar.DAY_OF_MONTH,1);
                    }
                    else {
                        calS.add(Calendar.MONTH, 1);
                        calS.set(Calendar.DAY_OF_MONTH,1);
                    }
                }
                else{
                    calS.add(Calendar.DAY_OF_MONTH,1);
                }


            }

        }

        axis.setCategories(categories);
        axis.setCategoriesDate(categoriesDate);
        this.setxAxis(axis);
    }

    /**
     * sql demo
     *
     *  ("<script>select "
        + "${item} AS ITEM ,"
        + " left ( date_format (bhr.STARTING_TIME, '%Y-%m-%d'),  ${timeSqu}  ) as DURATION, "
        + " count(bhr.BUSINESS_ID) AS ITEMCOUNT FROM b_house_repair bhr left join b_house bh on bhr.HOUSE_ID = bh.BUSINESS_ID "
        + " WHERE 1=1  AND bhr.DEL_FLAG = '0' and bh.DEL_FLAG = '0' and bhr.flag = '1' and bh.flag = '1' "
        + " <if test=\"mainType !=null and mainType !='' \"> and bhr.MAINTAIN_TYPE = #{mainType} </if>"
        + " <if test=\"startTime !=null \"> and bhr.STARTING_TIME &gt;=str_to_date(#{startTime},'%Y-%m-%d %H:%i:%S') </if>"
        + " <if test=\"endTime !=null \"> and bhr.STARTING_TIME &lt;=str_to_date(#{endTime},'%Y-%m-%d %H:%i:%S') </if>"
        + " GROUP BY ${item} ,"
        + " left ( date_format (bhr.STARTING_TIME, '%Y-%m-%d'),  ${timeSqu} )"
        +"</script>")
     */

}
