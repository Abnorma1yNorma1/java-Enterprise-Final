package by.it_academy.jd2.Mk_jd2_111_25.user_service.common.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@Builder
public class ResponsePage<T> {
    @NotNull
    private Integer number;
    @NotNull
    private Integer size;
    @NotNull
    private Integer totalPages;
    @NotNull
    private Long totalElements;
    @NotNull
    private Boolean first;
    @NotNull
    private Integer numberOfElements;
    @NotNull
    private Boolean last;
    @NotNull
    private List<T> content;

    public static <T> ResponsePage<T> fromSpringPage(Page<T> page){
        return new ResponsePage<>(
                page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.isFirst(),
                page.getNumberOfElements(),
                page.isLast(),
                page.getContent()
        );
    }
}
