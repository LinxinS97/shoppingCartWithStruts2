package com.hibernate;

import com.pojo.*;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;

public class ItemFactory {


    public static void main(String[] args){
//        Session session = HibernateFactory.getSession();
//        BigDecimal[] price = new BigDecimal[2];
//        price[0] = new BigDecimal(40.00);
//        price[1] = new BigDecimal(200.00);
//
//        int[] orderNum = new int[2];
//        orderNum[0] = 0;
//        orderNum[1] = 100;
//
//        List<Item> items = ItemFactory.MeticulousSearch("测试", price, "服装", orderNum, null, 0, 8).getList();
//
//        for(Item item : items){
//            System.out.println(item.getItemName());
//        }
    }

    /**
     * 查找商品的方法，可以根据关键字、类别、商品ID和用户ID进行查找
     * 更新：用Criteria代替hql，可读性高，条件拼接方便
     *
     * @param key       商品名字中的关键字
     * @param type      商品类别
     * @param itemId    商品唯一识别
     * @param userId    用户唯一识别
     *
     * @return Result     返回包含商品信息的集合和分页的信息
     */
    public static Result findItem(String key, String type, int itemId, int userId, int first, int max){
        int maxPage;
        int maxItem = 0;
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
        Predicate ItemKeyRestriction = builder.and();
        Predicate ItemTypeRestriction = builder.and();
        Predicate ItemItemIdRestriction = builder.and();
        Predicate ItemUserIdRestriction = builder.and();

        if(key != null){
            ItemKeyRestriction = builder.and(
                    builder.like(root.get(Item_.itemName), "%" + key + "%")
            );

        }
        if(type != null){
            ItemTypeRestriction = builder.and(
                    builder.equal(root.get(Item_.type), type)
            );

        }
        if(itemId != -1){
            ItemItemIdRestriction = builder.and(
                    builder.equal(root.get(Item_.itemId), itemId)
            );

        }
        if (userId != -1){
            ItemUserIdRestriction = builder.and(
                    builder.equal(root.get(Item_.userId), userId)
            );
        }
        criteria.where( builder.and(
                ItemKeyRestriction,
                ItemTypeRestriction,
                ItemItemIdRestriction,
                ItemUserIdRestriction
        ) );
        size = session.createQuery(criteria).getResultList().size();
        maxPage = size / max == 0 ? 1 : size % max == 0 ? size / max : size / max + 1;
        maxItem = size;
        items = session.createQuery(criteria)
                .setFirstResult(first)
                .setMaxResults(max)
                .getResultList();

        session.close();
        return new Result(maxPage, maxItem, items);
    }


    /**
     * 精细搜索，综合商品的各种参数进行搜索
     * 可选参数：商品名关键字、价格区间、类型、成交量
     *
     * @param keys      包含所有搜索关键字
     * @return  Result  包含结果集和分页信息
     */
    public static Result MeticulousSearch(SearchKeys keys, int first, int max){
        int maxPage;
        int maxItem = 0;
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
        Predicate ItemNameRestriction = builder.and();
        Predicate ItemPriceRestriction = builder.and();
        Predicate ItemTypeRestriction = builder.and();
        Predicate ItemOrderNumRestriction = builder.and();
        Predicate ItemStockNumRestriction = builder.and();

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
                ItemNameRestriction,
                ItemPriceRestriction,
                ItemTypeRestriction,
                ItemOrderNumRestriction,
                ItemStockNumRestriction
        ));
        size = session.createQuery(criteria).getResultList().size();
        maxPage = size / max == 0 ? 1 : size % max == 0 ? size / max : size / max + 1;
        maxItem = size;
        items = session.createQuery(criteria)
                .setFirstResult(first)
                .setMaxResults(max)
                .getResultList();
        session.close();
        return new Result(maxPage, maxItem, items);
    }

}
