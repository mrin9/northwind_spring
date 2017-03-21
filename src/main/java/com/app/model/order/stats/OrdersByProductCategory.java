package com.app.model.order.stats;

import lombok.*;
import java.util.*;
import java.math.*;
import javax.persistence.*;
import io.swagger.annotations.ApiModelProperty;
@Data
public class OrdersByProductCategory  {
  private long camera;
  private long laptop;
  private long tablet;
  private long phone;
}
