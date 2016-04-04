package com.lyp.neulife.bean;

import java.io.Serializable;

/**
 * Created by lyp on 2016/3/24.
 */
public class BeanMovie implements Serializable{

    private int id;
    private String movieName;       //电影名称
    private String movieDescription;//电影描述
    private String movieImgUrl;     //图片URL
    private int movieImgLocal;      //本地图片res
    private String movieDuration;   //电影时长
    private String movieType;       //电影类型
    private String movieBrowseNum;  //浏览量

    private int paletteColor;       //文本区域背景色

    public BeanMovie(String movieName, String movieImgUrl, String movieDuration, String movieType) {
        this.movieName = movieName;
        this.movieImgUrl = movieImgUrl;
        this.movieDuration = movieDuration;
        this.movieType = movieType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }

    public String getMovieImgUrl() {
        return movieImgUrl;
    }

    public void setMovieImgUrl(String movieImgUrl) {
        this.movieImgUrl = movieImgUrl;
    }

    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    public String getMovieBrowseNum() {
        return movieBrowseNum;
    }

    public void setMovieBrowseNum(String movieBrowseNum) {
        this.movieBrowseNum = movieBrowseNum;
    }

    public String getMovieDuration() {
        return movieDuration;
    }

    public void setMovieDuration(String movieDuration) {
        this.movieDuration = movieDuration;
    }

    public int getMovieImgLocal() {
        return movieImgLocal;
    }

    public void setMovieImgLocal(int movieImgLocal) {
        this.movieImgLocal = movieImgLocal;
    }

    public int getPaletteColor() {
        return paletteColor;
    }

    public void setPaletteColor(int paletteColor) {
        this.paletteColor = paletteColor;
    }
}
