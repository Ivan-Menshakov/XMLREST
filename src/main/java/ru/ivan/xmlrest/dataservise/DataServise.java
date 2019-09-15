package ru.ivan.xmlrest.dataservise;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.ivan.xmlrest.model.Box;
import ru.ivan.xmlrest.model.Item;

import java.util.ArrayList;
import java.util.List;


@Component
public class DataServise {

    Logger logger = LogManager.getLogger(DataServise.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Integer> getItems (Integer boxID,  String colorItem ) {
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("select id from item where color=? and CONTAINED_IN in (select id from box where id=? or CONTAINED_IN = ?)", colorItem, boxID, boxID);
        List<Integer> result = new ArrayList<>();
        while (rowSet.next()) {
            result.add(rowSet.getInt("id"));
        }
        return result;
    }

    public void addBox (Box box) {
        logger.debug("addBox: " + box);
            jdbcTemplate.update("insert into box (id , CONTAINED_IN) values (?,?)", box.getId(), box.getParentId());
    }

    public void addItem (Item item) {
        logger.debug("addItem: " + item);
        jdbcTemplate.update("insert into item (id, CONTAINED_IN, color) values (?,?,?)", item.getId(), item.getCONTAINED_IN(), item.getColor());
    }

}
