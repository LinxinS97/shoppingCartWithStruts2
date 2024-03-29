package com.action;

import com.hibernate.*;
import com.opensymphony.xwork2.ActionSupport;
import com.pojo.*;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;


public class Action_web extends ActionSupport implements SessionAware{


    private Map<String, Object> session;


    private String requestType;
    private int maxPage;
    private int page;
    private int maxItem;
    private int itemId;
    private Item item;
    private List<Item> itemList;
    private List<CartItem> cartList;
    private List<OrderWapper> orderList;
    private List<CommentWrapper> commentList;

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
    private String search_status;
    private String[] search_orderTime;

    //main.jsp param
    private List<Item> carouselItems;
    private Map<String, Integer> orderCount;


    private int orderCount_m;
    private int uploadedItem;

    //user search param
    private String location;
    private String isTag;


    @Override
    public void setSession(Map<String, Object> map) {
        session = map;
    }

    public List<CommentWrapper> getCommentList() {
        return commentList;
    }

    public void setSearch_status(String search_status) {
        this.search_status = search_status;
    }

    public void setSearch_orderTime(String[] search_orderTime) {
        this.search_orderTime = search_orderTime;
    }

    public void setUploadedItem(int uploadedItem) {
        this.uploadedItem = uploadedItem;
    }

    public Map<String, Integer> getOrderCount() {
        return orderCount;
    }

    public int getOrderCount_m() {
        return orderCount_m;
    }

    public List<OrderWapper> getOrderList() {
        return orderList;
    }

    public List<CartItem> getCartList() {
        return cartList;
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

    public void setLocation(String location) {
        this.location = location;
    }

    public void setIsTag(String isTag) {
        this.isTag = isTag;
    }

    public String getRequestType() {
        return requestType;
    }




    //Action witch index jump into. Provide information for (main.jsp).
    //Information includes 4 images of CAROUSEL, 8 latest items info for the area below CAROUSEL.
    @Action(value = "Welcome", results = {
            @Result(location = "/main.jsp")
    })
    public String Welcome(){
        carouselItems = ItemFactory.getCarouselItem();
        itemList = ItemFactory.getLatestItem();
        if(session.get("user") != null) {
            User user = (User) session.get("user");
            orderCount = new HashMap<>();

            orderCount.put("delivery", OrderFactory.getSumOfUserOrder(user.getUserId(), 1));
            orderCount.put("get", OrderFactory.getSumOfUserOrder(user.getUserId(), 2));
            orderCount.put("comment", OrderFactory.getSumOfUserOrder(user.getUserId(), 3));
            session.put("orderCount", orderCount);
        }
        return SUCCESS;
    }



    //This action will redirect to the main_seller.jsp
    //Information includes all items that this seller selling.
    //requestType1: get 8 items
    //requestType2: get all soled items
    //requestType3: get all order information
    //requestType4: changePage
    //requestType5: find items with keys
    //requestType6: find order with keys
    @Action(value = "Management", results = {
            @Result(location = "/manage/manage.jsp"),
            @Result(name = "error", location = "/login.jsp"),
            @Result(name = "input", location = "/manage/manage.jsp")
    })
    public String Management(){

        if(session.get("user") == null)
            return ERROR;
        User user = (User)session.get("user");
        orderCount_m = OrderFactory.getSumOfUserOrder(user.getUserId(), 0);
        uploadedItem = ItemFactory.MeticulousSearch(new SearchKeys(user.getUserId()),0, 10000).getMaxItem();
        session.put("orderCount", orderCount_m);
        session.put("uploadedItem", uploadedItem);
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

        //我的商品（超链接）
        if("1".equals(requestType)) {
            session.remove("page");
            session.put("requestType", "1");
            SearchKeys keys = new SearchKeys();
            keys.setSearch_userId(user.getUserId());
            //调用查询方法
            com.pojo.Result<Item> res = ItemFactory.MeticulousSearch(
                    keys, firstIndex, max
            );
            maxPage = res.getMaxPage();
            itemList = res.getList();
            maxItem = res.getMaxItem();
            session.put("maxItem", maxItem);
            session.put("page", 1);
            System.out.println("maxPage = " + maxPage);

        }
        //订单管理（超链接）
        else if("3".equals(requestType)){
            session.remove("page");
            session.remove("searchKeys");
            session.put("requestType", "3");
            orderList = new ArrayList<>();

            SearchKeys keys = new SearchKeys();
            keys.setSearch_userId(user.getUserId());
            com.pojo.Result<List> res = OrderFactory.getOrderWapper(keys, firstIndex, max);
            for(List i : res.getList()){
                orderList.add(new OrderWapper((Item)i.get(0), (UserOrder)i.get(1)));
            }

            maxPage = res.getMaxPage();
            maxItem = res.getMaxItem();
            session.put("maxItem", maxItem);
            session.put("page", 1);
            System.out.println("maxPage = " + maxPage);

        }
        //我的商品（查询）
        else if("5".equals(requestType)){
            session.put("requestType", "5");
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

            com.pojo.Result<Item> res = ItemFactory.MeticulousSearch(
                    keys, firstIndex, max
            );
            maxPage = res.getMaxPage();
            itemList = res.getList();
            maxItem = res.getMaxItem();
            session.put("maxItem", maxItem);
            session.put("page", page);
            System.out.println("maxPage = " + maxPage);
        }
        //订单查询
        else if("7".equals(requestType)){
            session.put("requestType", "7");
            SearchKeys keys = null;
            if(session.get("searchKeys") != null)
                keys = (SearchKeys)session.get("searchKeys");

            if(isSearch.equals("1")){
                if(search_price[0].equals(search_price[1]) && search_price[0].equals(new BigDecimal(0)))
                    search_price = null;

                if("全部".equals(search_type))
                    search_type = null;

                if(search_orderTime[0].equals(search_orderTime[1]) && search_orderTime[0].equals(""))
                    search_orderTime = null;



                keys = new SearchKeys(search_name, search_price,
                        search_type, search_orderTime, Integer.parseInt(search_status));
                keys.setSearch_userId(user.getUserId());
                session.put("searchKeys", keys);
            }

            com.pojo.Result<List> res = OrderFactory.getOrderWapper(
                    keys, firstIndex, max
            );
            orderList = new ArrayList<>();
            for(List i : res.getList()){
                orderList.add(new OrderWapper((Item)i.get(0), (UserOrder)i.get(1)));
            }
            maxPage = res.getMaxPage();
            maxItem = res.getMaxItem();
            session.put("maxItem", maxItem);
            session.put("page", page);
            System.out.println("maxPage = " + maxPage);
        }
        return SUCCESS;
    }



    @Action(value = "getSearchResult", results = {
            @Result(location = "/search_result.jsp")
    })
    public String GetSearchResult(){
        //每页显示最大商品数
        int max = 5;

        if(isTag != null){
            session.remove("page");
            page = 1;
        }
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
        SearchKeys keys;
        if(itemName != null || type != null){
            keys = new SearchKeys();
            keys.setSearch_type(type);
            keys.setSearch_name(itemName);
            session.put("searchKeys", keys);
        }else{
            keys = (SearchKeys)session.get("searchKeys");
        }
        com.pojo.Result<Item> res = ItemFactory.getSearchResult(location, keys, firstIndex, max);

        maxPage = res.getMaxPage();
        itemList = res.getList();

        session.put("location", location);
        session.put("page", page);
        return SUCCESS;
    }


    //显示购物车的商品
    @Action(value = "getCartItem", results = {
            @Result(name = "error", location = "/login.jsp"),
            @Result(location = "/user_cart.jsp")
    })
    public String GetCartItem(){
        if(session.get("user") == null)
            return ERROR;
        User user = (User)session.get("user");
        com.pojo.Result<List> res = CartFactory.GetUserCartList(user.getUserId(), 0, 10000);
        cartList = new ArrayList<>();
        for(List i : res.getList()){
            cartList.add(new CartItem((Item)i.get(0), (int)i.get(1), (String)i.get(2), (int)i.get(3)));
        }
        maxItem = res.getMaxItem();

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
        List<List> list = CommentFactory.getItemComment(itemId);
        commentList = new ArrayList<>();
        for(List i : list)
            commentList.add(new CommentWrapper((User)i.get(0), (UserComment)i.get(1)));

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
