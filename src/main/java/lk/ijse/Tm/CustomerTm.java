package lk.ijse.Tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class CustomerTm {
    private String customerId;
    private String customerName;
    private String address;
}
