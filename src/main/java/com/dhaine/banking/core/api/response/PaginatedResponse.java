package com.dhaine.banking.core.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Daudu John
 * @createdOn Apr-12(Sun)-2026
 */
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaginatedResponse<T> {
  private List<T> content;
  private int currentPage;
  private long totalPages;
  private long totalItems;
  private Boolean isFirstPage;
  private Boolean isLastPage;
}
