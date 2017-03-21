package com.app.model.order.stats;

import lombok.*;
import java.util.*;
import java.math.*;
import javax.persistence.*;
import io.swagger.annotations.ApiModelProperty;
@Data
public class OrdersByPaymentType  {
  private long cash;
  private long check;
  private long card;
}
