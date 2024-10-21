package io.github.yasirmaulana.mimi_store.util;

import io.github.yasirmaulana.mimi_store.dto.ResultPageResponseDTO;
import org.springframework.data.domain.Sort;

import java.util.List;

public class PaginationUtil {

    public static <T> ResultPageResponseDTO<T> createResultPageDTO(List<T> dtos, Long totalElements, Long pages) {
        ResultPageResponseDTO<T> result = new ResultPageResponseDTO<>();
        result.setPage(pages);
        result.setElements(totalElements);
        result.setResult(dtos);
        return result;
    }

    public static Sort.Direction getSortBy(String sortBy) {
        if(sortBy.equalsIgnoreCase("asc")) {
            return Sort.Direction.ASC;
        } else {
            return Sort.Direction.DESC;
        }
    }
}
