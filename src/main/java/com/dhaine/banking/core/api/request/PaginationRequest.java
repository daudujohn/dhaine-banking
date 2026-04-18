package com.dhaine.banking.core.api.request;

import static com.dhaine.banking.core.api.constant.PaginationConstant.*;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Daudu John
 * @createdOn Apr-12(Sun)-2026
 */
@Getter
@Setter
public class PaginationRequest {

  @Schema(defaultValue = DEFAULT_PAGE_NUMBER)
  @Min(value = PAGE_NUMBER_MINIMUM_VALUE, message = PAGE_NUMBER_MINIMUM_MESSAGE)
  private int pageNumber = Integer.parseInt(DEFAULT_PAGE_NUMBER);

  @Schema(defaultValue = DEFAULT_PAGE_SIZE)
  @Min(value = PAGE_SIZE_MINIMUM_VALUE, message = PAGE_SIZE_MINIMUM_MESSAGE)
  @Max(value = PAGE_SIZE_MAXIMUM_VALUE, message = PAGE_SIZE_MAXIMUM_MESSAGE)
  private int pageSize = Integer.parseInt(DEFAULT_PAGE_SIZE);

  public int getPageStart() {
    return (this.getPageNumber() - 1) * this.getPageSize();
  }

  public int getPageEnd(int totalItems) {
    return Math.min(totalItems, this.getPageStart() + this.getPageSize());
  }

  public boolean isFirstPage() {
    return this.getPageStart() == 0;
  }

  public boolean isLastPage(int totalItems) {
    if (totalItems == 0) return true;
    return this.getPageEnd(totalItems) == totalItems;
  }

  public int getTotalPages(int totalItems) {
    if (totalItems == 0) return 1;
    return Math.min(1, totalItems / pageSize);
  }
}
