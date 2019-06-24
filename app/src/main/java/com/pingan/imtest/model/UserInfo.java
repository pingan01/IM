package com.pingan.imtest.model;

/**
 * Created time : 2019/6/21 10:43.
 *
 * @author LKKJ
 */
public class UserInfo {

    private String userId;//ID
    private String name;//昵称
    private String portraitUri;//头像

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPortraitUri() {
        return portraitUri;
    }

    public void setPortraitUri(String portraitUri) {
        this.portraitUri = portraitUri;
    }

    public UserInfo(String userId, String name, String portraitUri) {
        this.userId = userId;
        this.name = name;
        this.portraitUri = portraitUri;
    }
}
