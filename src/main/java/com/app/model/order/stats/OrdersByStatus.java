package com.app.model.order.stats;

import lombok.*;
import java.util.*;
import java.math.*;
import javax.persistence.*;
import io.swagger.annotations.ApiModelProperty;
@Data
public class OrdersByStatus  {
  private long onHold;
  private long shipped;
  private long complete;
  private long newStatus;
}
