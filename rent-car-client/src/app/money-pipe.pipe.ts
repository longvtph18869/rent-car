import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'moneyPipe',
})
export class MoneyPipePipe implements PipeTransform {
  transform(value: number): string {
    if (value >= 1000000) {
      return (value / 1000000).toFixed(3).replaceAll('.', '') + 'K';
    } else {
      return (value / 1000).toFixed(0) + 'K';
    }
  }
}
