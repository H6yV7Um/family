package org.yxyqcy.family.common.highchart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * base char 模型
 */
public class BaseChartModel {


    public Chart getChart() {
        return chart;
    }

    public void setChart(Chart chart) {
        this.chart = chart;
    }

    public BaseChartModel(Chart chart) {
        this.chart = chart;
    }

    public BaseChartModel() {
    }

    private Chart chart;


    @JsonIgnore //统计  时间比例  4 年  7月  10日
    private Integer timeSqu ;

    public Integer getTimeSqu() {
        return timeSqu;
    }

    public void setTimeSqu(Integer timeSqu) {
        this.timeSqu = timeSqu;
    }


    //图表
    class Chart {
        private String type;

        public String getType() {
            return type;
        }

        public Chart() {
        }

        public Chart(String type) {
            this.type = type;
        }
    }

    @JsonIgnore
    public  final Chart PIE_CHART = new Chart("pie");

    @JsonIgnore  //统计项 group by
    private String item ;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }


    @JsonIgnore
    public Integer durationM  = 24 ;  //月 缺省显示 最大月
    @JsonIgnore
    public Integer durationD  = 30 ;  //天 缺省显示 最大天
    @JsonIgnore
    public Integer durationY  = 10 ;  //年 缺省显示 最大年
}
