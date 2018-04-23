package org.yxyqcy.family.common.highchart.model;

import org.yxyqcy.family.common.highchart.util.ChartModelUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PieChartModel extends BaseChartModel {

    private List<Series> series;

    public List<Series> getSeries() {
        return series;
    }

    public void setSeries(List<Series> series) {
        this.series = series;
    }

    public PieChartModel() {
        super.setChart(super.PIE_CHART);
    }

    //分类
    public class Series{
        //默认 样式
        private String name = "Brands";
        private boolean colorByPoint  = true;
        private List<Series_Data> data ;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isColorByPoint() {
            return colorByPoint;
        }

        public void setColorByPoint(boolean colorByPoint) {
            this.colorByPoint = colorByPoint;
        }

        public List<Series_Data> getData() {
            return data;
        }

        public void setData(List<Series_Data> data) {
            this.data = data;
        }
    }

    public class Series_Data{
        private String name ;
        private float y ;

        public Series_Data() {
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

        public float getY() {
            return y;
        }

        public void setY(float y) {
            this.y = y;
        }
    }

    //根据map 转换
    public void generateFromMap(List<Map> mapChart, String item) {
        List<Series_Data> data = new ArrayList<Series_Data>();
        //记 总数
        Integer sumData = 0 ;
        //结果集不多  遍历2次
        for (Map<String,Object> map : mapChart) {
            //sumData += ((BigDecimal)map.get("ITEMCOUNT")).intValue();
            sumData += Integer.parseInt(ChartModelUtil.resultMapToString(map, "ITEMCOUNT"));
            
        }

        for (Map<String,Object> map : mapChart) {
            Series_Data ser = new Series_Data();
        	String itemName= ChartModelUtil.resultMapToString(map, "ITEM");
        	ser.setName(itemName);
            ser.setY(Float.parseFloat(ChartModelUtil.resultMapToString(map, "ITEMCOUNT")) / sumData);
            data.add(ser);
            if(12 == data.size())
            	break;
        }
        List<Series> seriesList = new ArrayList<Series>();
        Series series = new Series();
        series.setData(data);
        seriesList.add(series);
        this.setSeries(seriesList);
    }
}
