package com.hibernate;

import com.pojo.Item;
import com.pojo.Item_;
import com.pojo.Result;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class ItemFactory {


    public static void main(String[] args){
        Session session = HibernateFactory.getSession();

//        //全新的数据库查询方式Criteria，用来替代hql，可读性高
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<Item> criteria = builder.createQuery(Item.class);
//        Root<Item> root = criteria.from(Item.class);
//
//        criteria.select(root);
//
//        //Item_.itemName is an example of the static form of JPA Metamodel reference
//        Predicate ItemNameRestriction = builder.and(
//                builder.like(root.get(Item_.itemName), "%测试%")
//        );
//
//        Predicate ItemPriceRestriction = builder.and(
//                builder.between(root.get(Item_.price), new BigDecimal(100), new BigDecimal(200))
//        );
//
//        Predicate ItemTypeRestriction = builder.and(
//                builder.equal(root.get(Item_.type), "动漫")
//        );
//
//        criteria.where( builder.and(ItemNameRestriction, ItemPriceRestriction, ItemTypeRestriction) );
//
        List<Item> items = ItemFactory.findItem("测试", "数码", -1, 10000, 0, 10).getList();

        for(Item item : items){
            System.out.println(item.getItemName());
        }
    }

    /**
     * 查找商品的方法，可以根据关键字、类别、商品ID和用户ID进行查找
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

}
