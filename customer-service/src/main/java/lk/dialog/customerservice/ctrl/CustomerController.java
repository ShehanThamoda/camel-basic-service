package lk.dialog.customerservice.ctrl;

import lk.dialog.customerservice.dto.PaymentDetails;
import lk.dialog.customerservice.response.CustomerDetailsResponse;
import lk.dialog.customerservice.response.PaymentDetailsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    @GetMapping(value = "/getCustomer/{customer_id}")
    public ResponseEntity<CustomerDetailsResponse> getCustomerDetails(@PathVariable("customer_id") String id) {
        System.out.println("Called to the customer service: Get Customer details. Id:"+id);
        CustomerDetailsResponse employeeAddResponse = new CustomerDetailsResponse(id,"Banula","0778899876");
        System.out.println("Response:"+employeeAddResponse.toString());
        return ResponseEntity.status(HttpStatus.OK).body(employeeAddResponse);
    }

    @PostMapping(value = "/payments")
    public ResponseEntity<PaymentDetailsResponse> sendPayments(@RequestBody PaymentDetails paymentDetails) {
        System.out.println("Called to the payment service: Body:"+paymentDetails.toString());
        PaymentDetailsResponse paymentDetailsResponse;
        if(paymentDetails.getCardNumber()!=null){
            paymentDetailsResponse = new PaymentDetailsResponse(4,"SUCCESS");
        }else{
            paymentDetailsResponse = new PaymentDetailsResponse(4,"FAIL");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentDetailsResponse);
    }
}
