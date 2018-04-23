package org.yxyqcy.family.home.movie.entity;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.yxyqcy.family.common.entity.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with family.
 * author: cy
 * Date: 16/6/23
 * Time: 上午10:14
 * description: 电影entity
 */
@Entity
@Table(name = "f_movie")
public class Movie extends IdEntity<Movie> {

    private static final long serialVersionUID = 3084423813656106050L;
    @Column(name = "fly_count")
    private Integer flyCount;// 看电影人数  缺省2
    private Float price;// 单人票价格
    private String location;// 厅&位置
    private String name;	// 电影名称
    private String movieTheatre;	// 电影院
    private String movieTheatreName;	// 电影院名称

    private String namePinyin; //name pinyin



    public String getNamePinyin() {
        return namePinyin;
    }

    public void setNamePinyin(String namePinyin) {
        this.namePinyin = namePinyin;
    }

    public String getMovieTheatreName() {
        return movieTheatreName;
    }

    public void setMovieTheatreName(String movieTheatreName) {
        this.movieTheatreName = movieTheatreName;
    }

    private Integer movieInterval;	// 电影时长

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    private Date movieDate;	// 电影开始时间
    private Float star; // 评价  0-5星

    public Movie() {
    }

    public Float getStar() {
        return star;
    }

    public void setStar(Float star) {
        this.star = star;
    }

    public Integer getFlyCount() {
        return flyCount;
    }

    public void setFlyCount(Integer fcount) {
        this.flyCount = fcount;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Length(min = 1,message = "电影名长度最小为1")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMovieTheatre() {
        return movieTheatre;
    }

    public void setMovieTheatre(String movieTheatre) {
        this.movieTheatre = movieTheatre;
    }

    public Integer getMovieInterval() {
        return movieInterval;
    }

    public void setMovieInterval(Integer movieInterval) {
        this.movieInterval = movieInterval;
    }

    public Date getMovieDate() {
        return movieDate;
    }

    public String getFormatterMovieDate(){
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if(null != getMovieDate()){
            return dataFormat.format(getMovieDate());
        }
        return "";
    }
    public void setMovieDate(Date movieDate) {
        this.movieDate = movieDate;
    }
}
