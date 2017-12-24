package com.hibernate;

import com.pojo.*;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class ItemFactory {


//    public static void main(String[] args){
//        List<Item> items = ItemFactory.getCarouselItem();
//
//        for(Item item : items){
//            System.out.println(item.getItemName());
//        }
//    }

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
                    builder.equal( root.get(Item_.userId), keys.getSearch_userId())
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


    /**
     * 用于查找main.jsp上轮播图片的产品图片的方法，查询销量前三的产品
     *
     * @return  items  包含结果集的集合
     */
    public static List<Item> getCarouselItem(){
        Session session = HibernateFactory.getSession();
        List<Item> items;
        Query<Item> q = session.createQuery("from Item i order by completeOrder desc", Item.class).setMaxResults(3);
        items = q.list();
        session.close();

        return items;
    }
}
