package lk.ijse.Tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode

public class ItemTm {
    private String code;
    private String description;
    private int unitPrice;
    private int qty;
}
