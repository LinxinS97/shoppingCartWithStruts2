package com.action;

import com.hibernate.AlterFactory;
import com.hibernate.ItemFactory;
import com.opensymphony.xwork2.ActionSupport;
import com.pojo.Item;
import com.pojo.User;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;



public class Action_web extends ActionSupport implements SessionAware{
    private Map<String, Object> session;
    private String requestType;
    private int maxPage;
    private int page;
    private int maxItem;
    private List<Item> itemList;

    //register params
    private String userName;
    private String psw;
    private String tel;

    //item upload params
    private String itemName;
    private double price;
    private int stock;
    private String type;

    //upload params
    private String[] uploadFileName;
    private String[] uploadContentType;
    private File[] upload;




    public List<Item> getItemList() {
        return itemList;
    }

    public int getMaxItem() {
        return maxItem;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public void setUploadFileName(String[] uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public void setUploadContentType(String[] uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public void setUpload(File[] upload) {
        this.upload = upload;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        session = map;
    }



    public int getMaxPage() {
        return maxPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }












    //Action witch index jump into. Provide information for (main.jsp).
    //Information includes 4 images of CAROUSEL, 8 latest items info for the area below CAROUSEL.
    @Action(value = "Welcome", results = {
            @Result(location = "/main.jsp")
    })
    public String Welcome(){
        return SUCCESS;
    }



    //This action will redirect to the main_seller.jsp
    //Information includes all items that this seller selling.
    @Action(value = "Management", results = {
            @Result(location = "/manage/manage.jsp"),
            @Result(name = "ERROR", location = "/login.jsp")
    })
    public String Management(){
        if(session.get("user") == null)
            return ERROR;
        User user = (User)session.get("user");
        if("1".equals(requestType)) {
            com.pojo.Result res = ItemFactory.findItem(null, null, -1, user.getUserId(), 0, 8);
            page = 1;
            maxPage = res.getMaxPage();
            itemList = res.getList();
            maxItem = res.getMaxItem();
            session.put("maxItem", maxItem);
        }
        return SUCCESS;
    }



    //Remove 'User' info in session then return SUCCESS.
    @Action(value = "logout", results = {
            @Result(location = "/index.jsp")
    })
    public String Logout(){
        session.remove("user");
        return SUCCESS;
    }



    @Action(value = "upload",
            results = {
            @Result(type = "chain", location = "Management")},
            interceptorRefs = {
            @InterceptorRef(value = "fileUpload", params = {
                    "allowedExtensions", ".jpg,.jpeg,.png",
                    "maximumSize", "1048576"
            }),
            @InterceptorRef(value = "defaultStack")}
    )
    public String Upload(){
        System.out.println("itemName: " + itemName + "\nprice: " + price + "\nstock :" + stock + "\n type: " + type);
        System.out.println("upload name : " + uploadFileName[0]);

        String suffix = uploadFileName[0].split("\\.")[1];     //文件后缀，看不懂是傻逼
        String uuid = UUID.randomUUID() + "";
        String imgPath = "/itemImg/" + uuid + "." + suffix;
        Item item = new Item();
        User user = (User)session.get("user");

        item.setItemName(itemName);
        item.setPrice(BigDecimal.valueOf(price));
        item.setStock(stock);
        item.setType(type);
        item.setUserId(user.getUserId());
        item.setItemImg(imgPath);

        File userImg = new File("/Users/elpis/FilesForShoppingCart_Struts2/itemsImgs");
        try {
            //保存文件
            FileUtils.copyFile(upload[0], new File(userImg, uuid + "." + suffix));
        } catch (IOException e) {
            e.printStackTrace();
        }

        AlterFactory.add(item);
        return SUCCESS;
    }



    //注册Action，把手机号放到session中，方便下一个Action调用
    @Action(value = "RegisterUsername", results = {
            @Result(location = "/register/register_2.jsp")
    })
    public String RegisterUsername(){
        System.out.println("telNum=" + tel);
        session.put("telNum", tel);
        return SUCCESS;
    }



    //注册Action，拿用户名密码手机号和头像
    //头像根据路径扔到硬盘里，虚拟路径配合UUID扔进数据库
    @Action(value = "RegisterInfo", results = {
            @Result(location = "/index.jsp")
    }, interceptorRefs = {
            @InterceptorRef(value = "fileUpload", params = {
                    "allowedExtensions", ".jpg,.jpeg,.png",
                    "maximumSize", "1048576"
            }),
            @InterceptorRef(value = "defaultStack")
    })
    public String RegisterInfo(){
        System.out.println("userName = " + userName);
        System.out.println("psw = " + psw);
        tel = (String) session.get("telNum");

        System.out.println("upload name : " + uploadFileName[0]);
        System.out.println("upload type : " + uploadContentType[0]);
        System.out.println("suffix : " + uploadFileName[0].split("\\.")[1]);

        String suffix = uploadFileName[0].split("\\.")[1];     //文件后缀，看不懂是傻逼
        String uuid = UUID.randomUUID() + "";
        String imgPath = "/userImg/" + uuid + "." + suffix;

        session.put("userImg", imgPath);

        //把上一个session中放进来的userName移除，不然注册完就自动登录了
        session.remove("userName");
        File userImg = new File("/Users/elpis/FilesForShoppingCart_Struts2/usersImgs");
        try {
            //保存文件
            FileUtils.copyFile(upload[0], new File(userImg, uuid + "." + suffix));
        } catch (IOException e) {
            e.printStackTrace();
        }

        User user = new User();
        user.setUserName(userName);
        user.setPassword(psw);
        user.setTeleNum(tel);
        user.setUserImg(imgPath);
        AlterFactory.add(user);
        return SUCCESS;
    }
}
