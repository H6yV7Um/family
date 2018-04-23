package org.yxyqcy.family.home.movie.service;

import org.yxyqcy.family.common.component.PageUtil;
import org.yxyqcy.family.home.movie.entity.MovieTheatre;

import java.util.List;

/**
 * Created by lcy on 17/2/3.
 */
public interface MovieTheatreService {
    /**
     * 查询影院
     * @param movieTheatre  param
     */
    public List<MovieTheatre> queryMovieTheatres(MovieTheatre movieTheatre);
}
