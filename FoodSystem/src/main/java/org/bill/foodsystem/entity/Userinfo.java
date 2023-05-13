package org.bill.foodsystem.entity;

public class Userinfo {
    private Integer userid;
    private String username;
    private String passwd;
    private String nickname;
    public Integer getUserid() {
        return userid;
    }
    public void setUserid(Integer userid) {
        this.userid = userid;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPasswd() {
        return passwd;
    }
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    @Override
    public String toString() {
        return "Userinfo [userid=" + userid + ", username=" + username + ", password=" + passwd + ", nickname="
                + nickname + "]";
    }
    public Userinfo(Integer userid, String username, String passwd, String nickname) {
        super();
        this.userid = userid;
        this.username = username;
        this.passwd = passwd;
        this.nickname = nickname;
    }
    public Userinfo() {
        super();
    }
}
