import { Injectable } from '@angular/core';
// import { PersonOutlined, ShoppingCartOutlined, MonetizationOnOutlined, AccountBalanceWalletOutlined, KeyboardArrowUp } from '@material-ui/icons';

@Injectable({
  providedIn: 'root'
})
export class WidgetService {
  //
  // getWidgetData(type: string): any {
  //   let data : any;
  //
  //   // Temporary data
  //   const amount = 100;
  //   const diff = 20;
  //
  //   switch (type) {
  //     case 'user':
  //       data = {
  //         title: 'USERS',
  //         isMoney: false,
  //         link: 'See all users',
  //         icon: new PersonOutlined(),
  //         amount: amount
  //       };
  //       break;
  //     case 'order':
  //       data = {
  //         title: 'ORDERS',
  //         isMoney: false,
  //         link: 'View all orders',
  //         icon: new ShoppingCartOutlined(),
  //         amount: amount
  //       };
  //       break;
  //     case 'earning':
  //       data = {
  //         title: 'EARNINGS',
  //         isMoney: true,
  //         link: 'View net earnings',
  //         icon: new MonetizationOnOutlined(),
  //         amount: amount
  //       };
  //       break;
  //     case 'balance':
  //       data = {
  //         title: 'BALANCE',
  //         isMoney: true,
  //         link: 'See details',
  //         icon: new AccountBalanceWalletOutlined(),
  //         amount: amount
  //       };
  //       break;
  //     default:
  //       break;
  //   }
  //
  //
  //   data.diff = diff;
  //   return data;
  // }
}
