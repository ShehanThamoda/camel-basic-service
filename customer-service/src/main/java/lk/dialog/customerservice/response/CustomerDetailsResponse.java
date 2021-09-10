package lk.dialog.customerservice.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerDetailsResponse {

    private String customerId;
    private String customeName;
    private String customerMobile;
}
