package com.hibernate;

import com.pojo.*;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class ItemFactory {


    public static void main(String[] args){
//        List<List> list= getItemAndItemCommentWithItemId(4);
//
//        for(List item : list){
//            System.out.println(item.get(0));
//            System.out.println(item.get(1));
//        }
    }

    /**
     * 精细搜索(manage.jsp)，综合商品的各种参数进行搜索
     * 可选参数：商品名关键字、价格区间、类型、成交量
     *
     * @param keys      包含所有搜索关键字
     * @return  Result  包含结果集和分页信息
     */
    public static Result<Item> MeticulousSearch(SearchKeys keys, int first, int max){
        int maxPage;
        int maxItem;
        int size;
        List<Item> items;

        Session session = HibernateFactory.getSession();
        //初始化Criteria对象
        CriteriaBuilder builder = session.getCriteriaBuilder();
        //设置查询对象
        CriteriaQuery<Item> criteria = builder.createQuery(Item.class);
        //设置查询根对象
        Root<Item> root = criteria.from(Item.class);

        //初始化限制条件
        //userId
        Predicate ItemUserIdRestriction = builder.and();
        //商品名称
        Predicate ItemNameRestriction = builder.and();
        //价格区间
        Predicate ItemPriceRestriction = builder.and();
        //商品类型
        Predicate ItemTypeRestriction = builder.and();
        //订单数量区间
        Predicate ItemOrderNumRestriction = builder.and();
        //商品库存区间
        Predicate ItemStockNumRestriction = builder.and();


        if(keys.getSearch_userId() != -1){
            ItemUserIdRestriction = builder.and(
                    builder.equal( root.get(Item_.userId), keys.getSearch_userId() )
            );
        }

        if(keys.getSearch_name() != null){
            ItemNameRestriction = builder.and(
                    builder.like( root.get(Item_.itemName), "%"+keys.getSearch_name()+"%" )
            );
        }
        if(keys.getSearch_price() != null){
            ItemPriceRestriction = builder.and(
                    builder.between( root.get(Item_.price), keys.getSearch_price()[0], keys.getSearch_price()[1] )
            );
        }
        if(keys.getSearch_type() != null){
            ItemTypeRestriction = builder.and(
                    builder.equal( root.get(Item_.type), keys.getSearch_type() )
            );
        }
        if(keys.getSearch_order() != null){
            ItemOrderNumRestriction = builder.and(
                    builder.between( root.get(Item_.completeOrder),
                            keys.getSearch_order()[0], keys.getSearch_order()[1] )
            );
        }
        if(keys.getSearch_stock() != null){
            ItemStockNumRestriction = builder.and(
                    builder.between( root.get(Item_.stock), keys.getSearch_stock()[0], keys.getSearch_stock()[1])
            );
        }

        criteria.where( builder.and(
                ItemUserIdRestriction,
                ItemNameRestriction,
                ItemPriceRestriction,
                ItemTypeRestriction,
                ItemOrderNumRestriction,
                ItemStockNumRestriction
        ) );
        size = session.createQuery(criteria).getResultList().size();
        maxPage = size / max == 0 ? 1 : size % max == 0 ? size / max : size / max + 1;
        maxItem = size;
        items = session.createQuery(criteria)
                .setFirstResult(first)
                .setMaxResults(max)
                .getResultList();
        session.close();
        return new Result<>(maxPage, maxItem, items);
    }


    /**
     * 用户根据keyword或type进行搜索
     *
     * @return  4中不同的结果，销量、人气、价格顺序、价格倒序
     */
    public static Result<Item> getSearchResult(String location, SearchKeys keys, int first, int max){
        int maxPage;
        int maxItem;

        List<Item> items;
        String hql = "";

        Session s = HibernateFactory.getSession();
        String hql_order =              "from Item where itemName like ?1 order by completeOrder desc";
        String hql_order_type =         "from Item where type=?1 order by completeOrder desc";
        String hql_popular =            "from Item where itemName like ?1";
        String hql_popular_type =       "from Item where type=?1";
        String hql_price =              "from Item where itemName like ?1 order by price";
        String hql_price_type =         "from Item where type=?1 order by price";
        String hql_price_desc =         "from Item where itemName like ?1 order by price desc";
        String hql_price_desc_type =    "from Item where type=?1 order by price desc";

        Query<Item> q;

        switch (location){
            case "order":
                if(keys.getSearch_type() != null)
                    hql = hql_order_type;
                 else
                    hql = hql_order;
                break;
            case "popular":
                if(keys.getSearch_type() != null)
                    hql = hql_popular_type;
                else
                    hql = hql_popular;
                break;
            case "price":
                if(keys.getSearch_type() != null)
                    hql = hql_price_type;
                 else
                    hql = hql_price;
                break;
            case "price_desc":
                if(keys.getSearch_type() != null)
                    hql = hql_price_desc_type;
                 else
                    hql = hql_price_desc;
                break;
        }

        if(keys.getSearch_type() != null){
            q = s.createQuery(hql, Item.class);
            q.setParameter(1, keys.getSearch_type());
        } else {
            q = s.createQuery(hql, Item.class);
            q.setParameter(1, "%" + keys.getSearch_name() + "%");
        }

        maxItem = q.list().size();
        maxPage = maxItem / max == 0 ? 1 : maxItem % max == 0 ? maxItem / max : maxItem / max + 1;

        q.setFirstResult(first);
        q.setMaxResults(max);
        items = q.list();
        return new Result<>(maxPage, maxItem, items);
    }


    /**
     * 用于查找main.jsp上轮播图片的产品图片的方法，查询销量前三的产品
     *
     * @return  items  包含结果集的集合
     */
    public static List<Item> getCarouselItem(){
        Session session = HibernateFactory.getSession();
        Query<Item> q = session.createQuery("from Item i order by completeOrder desc", Item.class).setMaxResults(3);
        List<Item> items = q.list();
        session.close();

        return items;
    }

    /**
     * 用于查找main.jsp上最新商品的方法，包含8个对象
     *
     * @return  items  包含结果集的集合
     */
    public static List<Item> getLatestItem(){
        Session session = HibernateFactory.getSession();
        Query<Item> q = session.createQuery("from Item i order by completeOrder desc", Item.class).setMaxResults(8);
        List<Item> items = q.list();
        session.close();

        return items;
    }


    /**
     * 用于返回用商品id查找的唯一商品
     *
     * @param itemId 商品id
     * @return Item  包含单个商品信息的Item对象
     */
    public static Item getItemWithItemId(int itemId){
        Session session = HibernateFactory.getSession();
        Query<Item> q= session.createQuery("from Item where itemId=?1", Item.class);
        q.setParameter(1, itemId);
        List<Item> items = q.list();
        session.close();
        return items.get(0);
    }

}
