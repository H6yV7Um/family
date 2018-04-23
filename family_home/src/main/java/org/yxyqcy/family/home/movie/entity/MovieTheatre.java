package org.yxyqcy.family.home.movie.entity;

import org.yxyqcy.family.common.entity.IdEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by lcy on 17/2/3.
 */
@Entity
@Table(name = "f_movie_theatre")
public class MovieTheatre extends IdEntity<Movie> {
    private static final long serialVersionUID = 5672072272983025345L;

    private String name;
    private String address;
    private String remarks;

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    private Date orderTime;

    public MovieTheatre() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getRemarks() {
        return remarks;
    }

    @Override
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
