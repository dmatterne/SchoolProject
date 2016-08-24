package be.david.school.beans;

import be.david.school.dbmodel.Users;

import java.sql.Date;

/**
 * Created by David on 24/08/2016.
 */
public class Authentication {

    private Id user_id;
    private Users user;
    private Connection con;
    private boolean debug_mode = false;

    public Authentication(Connection con) {
        this.con = con;
        con.createConnection();
    }

    public void setPersonId(String user_id) {
        if(user_id == null) {
            user_id = "";
        }

        if(!user_id.equals("")) {
            con.getEm().find()
            this.user_id = new Id(user_id);
            this.getUserInfo();
        }

    }

    private void getUserInfo() {
        try {
            this.user = (Users)Users._open(this.con.getConnection(), this.user_id);
        } catch (Exception var2) {
            ;
        }

    }

    public String getUsrFunction() {
        try {
            return this.user.getusrFunction();
        } catch (Exception var2) {
            return "";
        }
    }

    public boolean isAdmin() {
        if(this.debug_mode) {
            return this.debug_mode;
        } else {
            if(this.user != null) {
                try {
                    if(this.user.getusrFunction().equalsIgnoreCase("Admin")) {
                        return true;
                    }
                } catch (Exception var2) {
                    return false;
                }
            }

            return false;
        }
    }

    public boolean isLoggedIn() {
        return this.debug_mode?this.debug_mode:this.user != null;
    }

    public boolean isLoggedOut() {
        return this.debug_mode?this.debug_mode:this.user == null;
    }

    public boolean isEmployee() {
        if(this.debug_mode) {
            return this.debug_mode;
        } else {
            if(this.user != null) {
                try {
                    if(this.user.getusrFunction().equalsIgnoreCase("Employee")) {
                        return true;
                    }
                } catch (Exception var2) {
                    return false;
                }
            }

            return false;
        }
    }

    public String getUsrUserName() {
        try {
            return this.user.getusrUserName();
        } catch (Exception var2) {
            return "";
        }
    }

    public Integer getcntID() {
        try {
            return this.user.getcntID();
        } catch (Exception var2) {
            return Integer.valueOf(0);
        }
    }

    public Id getId() {
        try {
            return this.user.getId();
        } catch (Exception var2) {
            return null;
        }
    }

    public String getusrAddress() {
        try {
            return this.user.getusrAddress();
        } catch (Exception var2) {
            return "";
        }
    }

    public Date getusrBirthday() {
        try {
            return this.user.getusrBirthday();
        } catch (Exception var2) {
            return null;
        }
    }

    public String getusrEmail() {
        try {
            return this.user.getusrEmail();
        } catch (Exception var2) {
            return "";
        }
    }

    public String getusrFunction() {
        try {
            return this.user.getusrFunction();
        } catch (Exception var2) {
            return "";
        }
    }

    public Long getusrID() {
        try {
            return this.user.getusrID();
        } catch (Exception var2) {
            return new Long(0L);
        }
    }

    public String getusrName() {
        try {
            return this.user.getusrName();
        } catch (Exception var2) {
            return "";
        }
    }

    public String getusrPassword() {
        try {
            return this.user.getusrPassword();
        } catch (Exception var2) {
            return "";
        }
    }

    public String getusrSex() {
        try {
            return this.user.getusrSex();
        } catch (Exception var2) {
            return "";
        }
    }

    public Long getusrTel() {
        try {
            return this.user.getusrTel();
        } catch (Exception var2) {
            return new Long(0L);
        }
    }

    public String getusrUserName() {
        try {
            return this.user.getusrUserName();
        } catch (Exception var2) {
            return "";
        }
    }

    public int save() throws CacheException {
        return this.user.save();
    }

    public void setcntID(Integer value) throws CacheException {
        this.user.setcntID(value);
    }

    public void setusrAddress(String value) throws CacheException {
        this.user.setusrAddress(value);
    }

    public void setusrBirthday(Date value) throws CacheException {
        this.user.setusrBirthday(value);
    }

    public void setusrEmail(String value) throws CacheException {
        this.user.setusrEmail(value);
    }

    public void setusrFunction(String value) throws CacheException {
        this.user.setusrFunction(value);
    }

    public void setusrID(Long value) throws CacheException {
        this.user.setusrID(value);
    }

    public void setusrName(String value) throws CacheException {
        this.user.setusrName(value);
    }

    public void setusrPassword(String value) throws CacheException {
        this.user.setusrPassword(value);
    }

    public void setusrSex(String value) throws CacheException {
        this.user.setusrSex(value);
    }

    public void setusrTel(Long value) throws CacheException {
        this.user.setusrTel(value);
    }

    public void setusrUserName(String value) throws CacheException {
        this.user.setusrUserName(value);
    }

    public Id getUser_id() {
        return this.user_id;
    }

    public void setUser_id(Id user_id) {
        this.user_id = user_id;
    }
}
