import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'seat',
})
export class SeatPipe implements PipeTransform {
  transform(value: string): number {
    let seatCount = 0;
    switch (value) {
      case 'TWO_SEATER':
        seatCount = 2;
        break;
      case 'FOUR_SEATER':
        seatCount = 4;
        break;
      case 'FIVE_SEATER':
        seatCount = 5;
        break;
      case 'SIX_SEATER':
        seatCount = 6;
        break;
      case 'SEVEN_SEATER':
        seatCount = 7;
        break;
    }
    return seatCount;
  }
}
