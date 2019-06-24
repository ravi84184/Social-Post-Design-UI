package com.nikdemo.socialpostdesignui.model;

public class FeedModel {

    int profile,post;
    String name,time,description;
    int like,view,comment;

    public FeedModel(int profile, int post, String name, String time, String description, int like, int view, int comment) {
        this.profile = profile;
        this.post = post;
        this.name = name;
        this.time = time;
        this.description = description;
        this.like = like;
        this.view = view;
        this.comment = comment;
    }

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }

    public int getPost() {
        return post;
    }

    public void setPost(int post) {
        this.post = post;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }
}
