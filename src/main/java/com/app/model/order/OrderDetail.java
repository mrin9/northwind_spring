/**
 * Order EXTENDED contains extra details of the order
 * Such as Customer Info (Name, Company , Email)
 */

package com.app.model.order;

import lombok.*;
import java.util.*;
import java.math.*;
import javax.persistence.*;
import io.swagger.annotations.ApiModelProperty;

@Data
@EqualsAndHashCode(callSuper=false)
public class OrderDetail extends Order {

    private String customerFirstName;
    private String customerLastName;
    private String customerEmail;
    private String company;
    private List<OrderLine> orderLine;

    public OrderDetail(){}

    public OrderDetail(int  id, int employeeId, int customerId, Date orderDate   , String orderStatus,
        Date   shippedDate    , String shipName,
        String shipAddress1   , String shipAddress2     , String shipCity        , String shipState    , String shipPostalCode, String shipCountry,
        BigDecimal shippingFee, String customerFirstName, String customerLastName, String customerEmail, String company    , String paymentType, Date paidDate
    ){
        super(id, employeeId, customerId, orderDate, orderStatus, shippedDate ,shipName, shipAddress1,shipAddress2, shipCity, shipState, shipPostalCode, shipCountry, shippingFee, paymentType, paidDate);
        this.customerFirstName = customerFirstName;
        this.customerLastName  = customerLastName;
        this.customerEmail     = customerEmail;
        this.company           = company;
        this.orderLine         = new ArrayList<OrderLine>();
    }

    public void addOrderLine(int productId, String productCode, String productName, String category, BigDecimal quantity, BigDecimal unitPrice, BigDecimal discount, Date dateAllocated, String orderItemStatus){
        OrderLine line = new OrderLine(productId, productCode, productName, category, quantity, unitPrice, discount, dateAllocated, orderItemStatus);
        this.orderLine.add(line);
    }
}


@Data
class OrderLine  {

    private int        productId;
    private String     productCode;
    private String     productName;
    private String     category;
    private BigDecimal quantity;
    private BigDecimal unitPrice;
    private BigDecimal discount;
    private Date       dateAllocated;
    @ApiModelProperty(allowableValues = "On Order, Allocated, No Stock") private String orderItemStatus;


    public OrderLine(int productId, String productCode, String productName, String category, BigDecimal quantity, BigDecimal unitPrice, BigDecimal discount, Date dateAllocated, String orderItemStatus){
        this.productId    = productId;
        this.productCode  = productCode;
        this.productName  = productName;
        this.category     = category;
        this.quantity     = quantity;
        this.unitPrice    = unitPrice;
        this.discount     = discount;
        this.dateAllocated= dateAllocated;
    }

}
