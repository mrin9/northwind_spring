package com.app.model.order.stats;

import lombok.*;
import io.swagger.annotations.ApiModelProperty;
import com.app.model.response.*;

@Data
@EqualsAndHashCode(callSuper=false)
public class OrdersByProductCategoryResponse  extends OperationResponse {
  @ApiModelProperty(required = true, value = "")
  private OrdersByProductCategory content;
}
