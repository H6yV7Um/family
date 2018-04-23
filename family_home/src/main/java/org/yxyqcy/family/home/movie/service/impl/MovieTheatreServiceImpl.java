package org.yxyqcy.family.home.movie.service.impl;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.yxyqcy.family.common.service.BaseService;
import org.yxyqcy.family.home.movie.dao.MovieTheatreRepository;
import org.yxyqcy.family.home.movie.entity.MovieTheatre;
import org.yxyqcy.family.home.movie.service.MovieTheatreService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lcy on 17/2/3.
 */
@Service
@Transactional(readOnly = true,propagation = Propagation.NOT_SUPPORTED)
public class MovieTheatreServiceImpl extends BaseService implements MovieTheatreService {

    private static final long serialVersionUID = 2962623984993847924L;
    @Resource
    public MovieTheatreRepository movieTheatreRepository;

    @Override
    public List<MovieTheatre> queryMovieTheatres(MovieTheatre movieTheatre) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("flag",movieTheatre.getFlag());
        param.put("delFlag",movieTheatre.getDelFlag());
        return movieTheatreRepository.queryMovieTheatres(param);
    }
}
