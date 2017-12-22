package com.action;

import com.hibernate.UserFactory;
import com.opensymphony.xwork2.ActionSupport;
import com.pojo.User;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;


import java.util.Map;

@ParentPackage(value = "json-default")
public class Action_json extends ActionSupport implements SessionAware{
    private Map<String, Object> session;
    private String request_type;
    private String psw;
    private String tel;
    private String options;
    private String itemId;
    private String itemName;
    private String price;
    private String flag;


    public void setRequest_type(String request_type) {
        this.request_type = request_type;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getOptions() {
        return options;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFlag() {
        return flag;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        session = map;
    }




    //return flag = true when telNum is existed in database.
    @Action(value = "ifTelNumExist", results = {
            @Result(type = "json")
    })
    public String IfTelNumExist(){
        flag = "false";
        if(UserFactory.findUser(-1, tel) != null){
            flag = "true";
        }
        return SUCCESS;
    }


    //When user login, put user's info into SESSION then return SUCCESS.
    @Action(value = "login", results = {
            @Result(type = "json")
    })
    public String Login(){
        System.out.println("Action_json_Login----tel = " + tel);
        System.out.println("Action_json_Login----psw = " + psw);
        System.out.println("Action_json_Login----options = " + options);
        User user = UserFactory.findUser(-1, tel);
        if(user != null){
            if(user.getPassword().equals(psw)){
                flag = "success";
                session.put("user", user);
            } else{
                flag = "pswError";
            }
        } else{
            flag = "unknownUserName";
        }
        return SUCCESS;
    }

    //Return SUCCESS if 'User' info has existed in SESSION.
    @Action(value = "ifLogin", results = {
            @Result(type = "json")
    })
    public String IfLogin(){
        flag = "false";

        if(session.get("user") != null){
            User user = (User)session.get("user");
            if(!"".equals(user.getUserName())){
                flag = "true";
            }
        }
        return SUCCESS;
    }

    //Put ITEM info into user's SHOPPING CART then return SUCCESS.
    @Action(value = "addCart", results = {
            @Result(type = "json")
    })
    public String AddCart(){
        return SUCCESS;
    }

    //Delete ITEM from user's SHOPPING CART then return SUCCESS.
    @Action(value = "deleteCart", results = {
            @Result(type = "json")
    })
    public String DeleteCart(){
        return SUCCESS;
    }


}