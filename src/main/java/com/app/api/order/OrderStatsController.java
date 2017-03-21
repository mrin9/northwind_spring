package com.app.api.order;

import io.swagger.annotations.*;
//import springfox.documentation.annotations.*;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.*;
//import static org.springframework.http.MediaType.*;

import java.util.*;
import java.math.BigDecimal;
import com.app.api.*;
import com.app.model.order.*;
import com.app.model.order.stats.*;
import com.app.repo.*;
import static com.app.model.response.OperationResponse.*;

@RestController
@RequestMapping(value = "/nw", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = {"Order"})
public class OrderStatsController {

  @Autowired private JdbcTemplate jdbcTemplate;
  @Autowired private OrderRepo orderRepo;

  @ApiOperation(value = "Orders by status", response = OrdersByStatusResponse.class)
  @RequestMapping(value = "/orders-by-status", method = RequestMethod.GET)
  public OrdersByStatusResponse getOrdersByStatus() {
    String sql = "select count(*) as count, order_status as status from orders group by order_status";
    String countType = new String();
    long count;
    OrdersByStatusResponse resp = new OrdersByStatusResponse();
    OrdersByStatus ordersByStatus = new OrdersByStatus();

    List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
    for (Map<String, Object> row : list) {
        countType = (String)row.get("status");
        count = (long)row.get("count");

        switch (countType) {
          case "On Hold":
            ordersByStatus.setOnHold(count);
            break;
          case "Shipped":
            ordersByStatus.setShipped(count);
            break;
          case "Complete":
            ordersByStatus.setComplete(count);
            break;
          case "New":
            ordersByStatus.setNewStatus(count);
            break;
        }

    }
    resp.setOperationStatus(ResponseStatusEnum.SUCCESS);
    resp.setOperationMessage("Orders by status");
    resp.setContent(ordersByStatus);
    return resp;
  }





}
