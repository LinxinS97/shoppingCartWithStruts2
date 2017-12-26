package com.action;

import com.hibernate.AlterFactory;
import com.hibernate.CartFactory;
import com.hibernate.ItemFactory;
import com.hibernate.UserFactory;
import com.opensymphony.xwork2.ActionSupport;
import com.pojo.Cart;
import com.pojo.Item;
import com.pojo.ItemPK;
import com.pojo.User;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;


import java.io.File;
import java.util.List;
import java.util.Map;

@ParentPackage(value = "json-default")
public class Action_json extends ActionSupport implements SessionAware{
    private Map<String, Object> session;
    private String psw;
    private String tel;
    private String options;
    private int itemId;
    private String flag;
    private int num;
    private String location;

    //navBarInfo
    private List<Item> navBar_cart;
    private int cartSize;




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

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getFlag() {
        return flag;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getLocation() {
        return location;
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

                com.pojo.Result<Item> res = CartFactory.GetUserCart(user.getUserId(), 0, 3);
                navBar_cart = res.getList();
                cartSize = res.getMaxItem();

                session.put("user", user);
                session.put("navBar_cart", navBar_cart);
                session.put("cartSize", cartSize);
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




    //Delete ITEM from user's SHOPPING CART then return SUCCESS.
    @Action(value = "deleteCart", results = {
            @Result(type = "json")
    })
    public String DeleteCart(){
        Cart cart = new Cart();
        User user = (User)session.get("user");
        cart.setUserId(user.getUserId());
        cart.setItemId(itemId);
        AlterFactory.delete(cart);
        flag = "true";
        com.pojo.Result<Item> res = CartFactory.GetUserCart(user.getUserId(), 0, 3);
        navBar_cart = res.getList();
        cartSize = res.getMaxItem();

        session.put("navBar_cart", navBar_cart);
        session.put("cartSize", cartSize);
        return SUCCESS;
    }




    @Action(value = "operateCart", results = {
            @Result(type = "json")
    })
    public String OperateCart(){
        User user = (User)session.get("user");
        flag = CartFactory.IfItemExist(user.getUserId(), itemId) + "";
        if(flag.equals("false")){
            Cart cart = new Cart();
            cart.setItemId(itemId);
            cart.setUserId(user.getUserId());
            cart.setNum(num);
            AlterFactory.add(cart);

            com.pojo.Result<Item> res = CartFactory.GetUserCart(user.getUserId(), 0, 3);
            navBar_cart = res.getList();
            cartSize = res.getMaxItem();

            session.put("navBar_cart", navBar_cart);
            session.put("cartSize", cartSize);
        }
        return SUCCESS;
    }



    //Delete ITEM from Table "item" then return SUCCESS.
    @Action(value = "deleteItem", results = {
            @Result(type = "json")
    })
    public String DeleteItem(){
        System.out.println(itemId);
        Item item = ItemFactory.getItemWithItemId(itemId);
        String[] imgName = item.getItemImg().split("/");
        System.out.println(imgName.length);
        System.out.println(imgName[2]);
        File itemImg = new File("/Users/elpis/FilesForShoppingCart_Struts2/itemsImgs/" + imgName[2]);
        itemImg.delete();
        AlterFactory.delete(item);
        CartFactory.ChangeItemDisabled(itemId);
        return SUCCESS;
    }


    @Action(value = "getLocation", results = {
            @Result(type = "json")
    })
    public String GetLocation(){
        location = (String) session.get("location");
        return SUCCESS;
    }

}
