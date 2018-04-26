package com.terwergreen.middle.user.dto;

public class UserDTO {
    /**
     * 账户ID
     */
    private String userId;
    /**
     * 账户ID
     */
    private String accoutId;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 简介
     */
    private String userProfile;
    /**
     * 手机
     */
    private String mobile;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccoutId() {
        return accoutId;
    }

    public void setAccoutId(String accoutId) {
        this.accoutId = accoutId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(String userProfile) {
        this.userProfile = userProfile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
