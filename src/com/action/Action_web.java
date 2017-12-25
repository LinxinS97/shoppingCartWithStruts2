package com.action;

import com.hibernate.AlterFactory;
import com.hibernate.ItemFactory;
import com.opensymphony.xwork2.ActionSupport;
import com.pojo.Item;
import com.pojo.SearchKeys;
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
    private int itemId;
    private Item item;
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

    //change page
    private int previous;
    private int next;

    //MeticulousSearch params
    private String isSearch = "0";
    private String search_name;
    private BigDecimal[] search_price;
    private String search_type;
    private int[] search_order;
    private int[] search_stock;

    //main.jsp param
    private List<Item> carouselItems;



    @Override
    public void setSession(Map<String, Object> map) {
        session = map;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

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

    public int getMaxPage() {
        return maxPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPrevious(int previous) {
        this.previous = previous;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    public void setIsSearch(String isSearch) {
        this.isSearch = isSearch;
    }

    public void setSearch_name(String search_name) {
        this.search_name = search_name;
    }

    public void setSearch_price(BigDecimal[] search_price) {
        this.search_price = search_price;
    }

    public void setSearch_type(String search_type) {
        this.search_type = search_type;
    }

    public void setSearch_order(int[] search_order) {
        this.search_order = search_order;
    }

    public void setSearch_stock(int[] search_stock) {
        this.search_stock = search_stock;
    }

    public List<Item> getCarouselItems() {
        return carouselItems;
    }




    //Action witch index jump into. Provide information for (main.jsp).
    //Information includes 4 images of CAROUSEL, 8 latest items info for the area below CAROUSEL.
    @Action(value = "Welcome", results = {
            @Result(location = "/main.jsp")
    })
    public String Welcome(){
        carouselItems = ItemFactory.getCarouselItem();
        itemList = ItemFactory.getLatestItem();
        return SUCCESS;
    }



    //This action will redirect to the main_seller.jsp
    //Information includes all items that this seller selling.
    //requestType1: get 8 items
    //requestType2: get all soled items
    //requestType3: get all order information
    //requestType4: changePage
    //requestType5: find items with keys
    @Action(value = "Management", results = {
            @Result(location = "/manage/manage.jsp"),
            @Result(name = "error", location = "/login.jsp"),
            @Result(name = "input", location = "/manage/manage.jsp")
    })
    public String Management(){

        if(session.get("user") == null)
            return ERROR;
        User user = (User)session.get("user");
        //每页显示最大商品数
        int max = 6;

        //page初始化
        if(page == 0 && session.get("page") == null)
            page = 1;
        else if(session.get("page") != null && page == 0)
            page = (int)session.get("page");
        System.out.println("page = " + page);

        //向前
        if(previous == 1 && page != 0){
            page = (int)session.get("page");
            page -= 1;
            System.out.println("page = " + page);
        }

        //向后
        if(next == 1){
            page = (int)session.get("page");
            page += 1;
            System.out.println("page = " + page);
        }

        //第一个物品的索引
        int firstIndex = page * max - max;

        if("1".equals(requestType)) {
            session.remove("searchKeys");
            SearchKeys keys = new SearchKeys();
            keys.setSearch_userId(user.getUserId());
            //调用查询方法
            com.pojo.Result res = ItemFactory.MeticulousSearch(
                    keys, firstIndex, max
            );
            maxPage = res.getMaxPage();
            itemList = res.getList();
            maxItem = res.getMaxItem();
            session.put("maxItem", maxItem);
            session.put("requestType", "1");
            session.put("page", page);
            System.out.println("maxPage = " + maxPage);

        } else if("5".equals(requestType)){
            SearchKeys keys = null;
            if(session.get("searchKeys") != null)
                keys = (SearchKeys)session.get("searchKeys");

            if(isSearch.equals("1")){
                if(search_price[0].equals(search_price[1]) && search_price[0].equals(new BigDecimal(0)))
                    search_price = null;

                if(search_order[0] == search_order[1] && search_order[0] == 0)
                    search_order = null;

                if(search_stock[0] == search_stock[1] && search_stock[0] == 0)
                    search_stock = null;

                if("全部".equals(search_type))
                    search_type = null;

                keys = new SearchKeys(search_name, search_price, search_type, search_order, search_stock);
                keys.setSearch_userId(user.getUserId());
                session.put("searchKeys", keys);
            }

            com.pojo.Result res = ItemFactory.MeticulousSearch(
                    keys, firstIndex, max
            );
            maxPage = res.getMaxPage();
            itemList = res.getList();
            maxItem = res.getMaxItem();
            session.put("maxItem", maxItem);
            session.put("requestType", "5");
            session.put("page", page);
            System.out.println("maxPage = " + maxPage);
        }
        return SUCCESS;
    }


    //跳转到商品的详细信息页面
    @Action(value = "getItemInfo", results = {
            @Result(name="manageInfo", location = "/manage/itemInfo.jsp"),
            @Result(name="mainInfo", location = "/itemInfo.jsp"),
            @Result(name = "error", location = "/login.jsp")
    })
    public String GetItemInfo(){
        item = ItemFactory.getItemWithItemId(itemId);
        if(requestType.equals("1")) {
            if(session.get("user") == null)
                return ERROR;
            return "manageInfo";
        } else{
            return "mainInfo";
        }
    }



    //把session中的user信息删除，退出登录
    @Action(value = "logout", results = {
            @Result(location = "/index.jsp")
    })
    public String Logout(){
        session.remove("user");
        return SUCCESS;
    }



    //发布商品
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

    //重新发布商品，可以更改商品除了用户id和商品id以外的所有信息
    @Action(value = "reUpload",
            results = {
                    @Result(type = "chain", location = "Management")},
            interceptorRefs = {
                    @InterceptorRef(value = "fileUpload", params = {
                            "allowedExtensions", ".jpg,.jpeg,.png",
                            "maximumSize", "1048576"
                    }),
                    @InterceptorRef(value = "defaultStack")}
    )
    public String ReUpload(){
        System.out.println("itemName: " + itemName + "\nprice: " + price + "\nstock :" + stock + "\n type: " + type);

        Item item = new Item();
        Item item_before = ItemFactory.getItemWithItemId(itemId);
        User user = (User)session.get("user");

        if(upload != null){
            System.out.println("upload name : " + uploadFileName[0]);
            String suffix = uploadFileName[0].split("\\.")[1];     //文件后缀，看不懂是傻逼
            String uuid = UUID.randomUUID() + "";
            String imgPath = "/itemImg/" + uuid + "." + suffix;
            item.setItemImg(imgPath);
            File itemImg = new File("/Users/elpis/FilesForShoppingCart_Struts2/itemsImgs");
            File img_before = new File("/Users/elpis/FilesForShoppingCart_Struts2/itemsImgs/"
                    + item_before.getItemImg().split("/")[2]);
            System.out.println(img_before.delete());
            try {
                //保存文件
                FileUtils.copyFile(upload[0], new File(itemImg, uuid + "." + suffix));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        item.setUserId(user.getUserId());
        item.setItemId(itemId);
        item.setItemName(itemName);
        item.setPrice(BigDecimal.valueOf(price));
        item.setStock(stock);
        item.setType(type);
        item.setUserId(user.getUserId());

        AlterFactory.alter(item);
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
