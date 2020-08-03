package bsuir.model.pageModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page {
    private int page;
    private int size;
    private long totalElements;
    private boolean direction;
    private String parameter;
}
