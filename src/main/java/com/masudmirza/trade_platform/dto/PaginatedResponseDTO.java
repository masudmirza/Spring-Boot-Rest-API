package com.masudmirza.trade_platform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginatedResponseDTO <T> {
    private List<T> data;
    private long totalElements;
    private int totalPages;
    private int currentPage;
    private int pageSize;
}
