package org.yxyqcy.family.home.movie.service.impl;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.yxyqcy.family.common.component.PageUtil;
import org.yxyqcy.family.common.model.PersistModel;
import org.yxyqcy.family.common.service.BaseService;
import org.yxyqcy.family.common.util.IdGen;
import org.yxyqcy.family.common.util.PinYin4JUtil;
import org.yxyqcy.family.home.movie.dao.MovieRepository;
import org.yxyqcy.family.home.movie.entity.Movie;
import org.yxyqcy.family.home.movie.service.MovieService;
import org.yxyqcy.family.sys.util.UserUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with family.
 * author: cy
 * Date: 16/6/23
 * Time: 上午10:25
 * description: 电影service 实现
 */
@Service
@Transactional(readOnly = true,propagation = Propagation.NOT_SUPPORTED)
public class MovieServiceImpl extends BaseService implements MovieService {

    private static final long serialVersionUID = 8124583793870416085L;
    @Resource
    public MovieRepository movieRepository;


    @Override
    public List<Movie> queryMoviesByPagination(PageUtil pageUtil, Movie movie) {
        Map param = new HashMap<String,Object>();
        param.put("createBy",movie.getCreateBy());
        param.put("flag",movie.getFlag());
        param.put("delFlag",movie.getDelFlag());
        //电影名称 模糊查询
        if(StringUtils.isNotEmpty(movie.getName())){
            param.put("name",movie.getName());
            param.put("namePinyin",PinYin4JUtil.converterToFirstSpell(movie.getName()));
        }
        return movieRepository.queryMovies(param);
    }


    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public PersistModel persistMovie(Movie movie) {
        movie.setCommonBusinessId();
        UserUtils.setCurrentPersistOperation(movie);
        // pinyin
        if(StringUtils.isNotEmpty(movie.getName())){
            movie.setNamePinyin(PinYin4JUtil.converterToFirstSpell(movie.getName()));
        }
        int line = movieRepository.insertSelective(movie);
        return new PersistModel(line);
    }
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public PersistModel mergeMovie(Movie movie) {
        UserUtils.setCurrentMergeOperation(movie);
        // pinyin
        if(StringUtils.isNotEmpty(movie.getName())){
            movie.setNamePinyin(PinYin4JUtil.converterToFirstSpell(movie.getName()));
        }
        int line = movieRepository.updateSelective(movie);
        return new PersistModel(line);
    }
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public PersistModel removeMovie(String businessId) {
        Movie movie = new Movie();
        movie.setBusinessId(businessId);
        movie.setDeleted();
        UserUtils.setCurrentMergeOperation(movie);
        int line = movieRepository.deleteLogic(movie);
        return new PersistModel(line);
    }

    @Override
    public Movie queryMovieById(String id) {
        return movieRepository.selectByBusinessId(id);
    }
}
