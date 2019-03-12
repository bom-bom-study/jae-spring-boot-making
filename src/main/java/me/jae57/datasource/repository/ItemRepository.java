package me.jae57.datasource.repository;

import me.jae57.datasource.model.Item;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemRepository {

    private final JdbcTemplate jdbcTemplate;

    public ItemRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Item> getAllItems(){
        return jdbcTemplate.query("select * from item",(result, rowNum)->new Item(result.getInt("id"),result.getString("product_name"),result.getString("company"),result.getString("category")));
    }

    public Item getItem(int itemId){
        String query = "SELECT * FROM ITEM WHERE ID=?";
        // new Object[]{itemId}
        // 아래와 같다
        // Object[] objs = new Object[1];
        // objs[0] = itemId;
        return jdbcTemplate.queryForObject(query,(result, rowNum)->new Item(result.getInt("id"),result.getString("product_name"),result.getString("company"),result.getString("category")),itemId);
    }

    public int addItem(int id, String name, String category){
        String query = "INSERT INTO ITEM VALUES(?,?,?)";
        return jdbcTemplate.update(query,id,name,category);
    }

    public int deleteItem(int id){
        String query = "DELETE FROM ITEM WHERE ID=?";
        return jdbcTemplate.update(query, id);
    }

}
