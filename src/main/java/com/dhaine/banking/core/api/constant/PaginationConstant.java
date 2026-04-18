package com.dhaine.banking.core.api.constant;

/**
 * @author Daudu John
 * @createdOn Mar-16(Mon)-2026
 */
public class PaginationConstant {
  public static final String PAGE_SIZE = "pageSize";
  public static final int PAGE_SIZE_MINIMUM_VALUE = 1;
  public static final int PAGE_SIZE_MAXIMUM_VALUE = 100;
  public static final String DEFAULT_PAGE_SIZE = "10";
  public static final String PAGE_SIZE_MINIMUM_MESSAGE =
      "Page size must be at least " + PAGE_SIZE_MINIMUM_VALUE;
  public static final String PAGE_SIZE_MAXIMUM_MESSAGE =
      "Page size cannot exceed " + PAGE_SIZE_MAXIMUM_VALUE;

  public static final String PAGE_NUMBER = "pageNumber";
  public static final int PAGE_NUMBER_MINIMUM_VALUE = 1;
  public static final String DEFAULT_PAGE_NUMBER = "1";
  public static final String PAGE_NUMBER_MINIMUM_MESSAGE =
      "Page number must be at least " + PAGE_NUMBER_MINIMUM_VALUE;
}
