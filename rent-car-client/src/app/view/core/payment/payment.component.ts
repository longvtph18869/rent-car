import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';
import { PaymentService } from 'src/app/service/payment.service';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css'],
})
export class PaymentComponent implements OnInit {
  isSuccess: boolean = false;
  constructor(
    private route: ActivatedRoute,
    private authService: AuthService,
    private paymentService: PaymentService
  ) {}
  ngOnInit(): void {
    this.route.queryParamMap.subscribe((params: ParamMap) => {
      const vnpAmount = +params.get('vnp_Amount')!;
      const vnpBankCode = params.get('vnp_BankCode');
      const vnpOrderInfo = params.get('vnp_OrderInfo');
      const id = parseInt(vnpOrderInfo!.split(' ')[4]);
      const vnpResponseCode = params.get('vnp_ResponseCode');
      if (vnpResponseCode === '00') {
        this.isSuccess = true;
        this.authService.getUser().subscribe((user) => {
          const payment = {
            orderCode: id,
            amount: vnpAmount,
            bankCode: vnpBankCode,
            userId: user.id,
          };
          console.log(payment);

          this.paymentService.save(payment).subscribe();
        });
      }
    });
  }
}
