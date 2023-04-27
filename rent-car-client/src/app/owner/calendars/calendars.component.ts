import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MessageService } from 'primeng/api';
import { DialogLoadingComponent } from 'src/app/dialog/loading/loading.component';
import { AuthService } from 'src/app/service/auth.service';
import { OwnerService } from 'src/app/service/owner.service';
import { ScheduleService } from 'src/app/service/schedule.service';

@Component({
  selector: 'app-calendars',
  templateUrl: './calendars.component.html',
  styleUrls: ['./calendars.component.css'],
})
export class CalendarsComponent implements OnInit {
  month!: string;
  monthCurrent!: number;
  year!: number;
  selectedCar: any;
  isClicked: boolean = false;
  clickedIndex!: number;
  dialogLoading: MatDialogRef<any> | undefined;
  constructor(
    private scheduleService: ScheduleService,
    private authService: AuthService,
    private ownerService: OwnerService,
    private dialog: MatDialog,
    private messageService: MessageService
  ) {}
  mycars: any[] = [];
  filteredCars: any[] = [];
  daysOfWeek = [
    'Thứ 2',
    'Thứ 3',
    'Thứ 4',
    'Thứ 5',
    'Thứ 6',
    'Thứ 7',
    'Chủ nhật',
  ];
  calendarDays: number[] = [];
  calendarCars: number[] = [];
  scheduleLists: any[] = [];
  schedules: Date[] = [];
  yearDropdownVisible = false;
  years: number[] = [];

  ngOnInit() {
    this.dialogLoading = this.dialog.open(DialogLoadingComponent, {
      disableClose: true,
    });
    const currentDate = new Date();
    this.month = this.getMonthName(currentDate.getMonth());
    this.monthCurrent = currentDate.getMonth();
    this.year = currentDate.getFullYear();
    const id = this.authService.getUserId();
    this.ownerService.myCars(id).subscribe({
      next: (res) => {
        this.mycars = res;
        this.filteredCars = res;
        this.selectedCar = this.mycars[0];
        this.scheduleService.findByCar(this.mycars[0].id).subscribe({
          next: (res) => {
            this.scheduleLists = res;
            for (let x of this.scheduleLists) {
              const date = new Date(x.date);
              date.setHours(0, 0, 0, 0);
              this.schedules.push(date);
            }
            this.getDayByMonthAndYear(this.monthCurrent, this.year);
            console.log(this.calendarCars);
            this.dialogLoading?.close();
          },
          error: (err) => {
            console.log(err);
          },
        });
      },
      error: (err) => {
        console.log(err);
      },
    });
    this.generateCalendar(currentDate.getFullYear(), currentDate.getMonth());
    this.generateYears(currentDate.getFullYear());
  }
  getDayByMonthAndYear(month: number, year: number) {
    for (let x of this.schedules) {
      if (x.getMonth() == month && x.getFullYear() == year)
        this.calendarCars.push(x.getDate());
    }
  }
  isDayRented(day: number): boolean {
    for (let rentedDay of this.calendarCars) {
      if (rentedDay == day) {
        return true;
      }
    }
    return false;
  }
  onDayClick(day: number) {
    if (day) {
      const selectedDate = new Date(this.year, this.monthCurrent, day);
      selectedDate.setHours(0, 0, 0, 0);
      const index = this.schedules.findIndex(
        (date) => date.getTime() === selectedDate.getTime()
      );
      if (index !== -1) {
        this.schedules.splice(index, 1);
      } else {
        this.schedules.push(selectedDate);
      }
    }
    const carIndex = this.calendarCars.indexOf(day);
    if (carIndex !== -1) {
      this.calendarCars.splice(carIndex, 1);
    } else {
      this.calendarCars.push(day);
    }
  }
  private generateCalendar(year: number, month: number): void {
    this.calendarCars.length = 0;
    this.getDayByMonthAndYear(this.monthCurrent, this.year);
    this.calendarDays = [];
    const firstDayOfMonth = new Date(year, month, 1).getDay() - 1;
    const totalDaysInMonth = new Date(year, month + 1, 0).getDate();
    for (let i = 0; i < firstDayOfMonth; i++) {
      this.calendarDays.push(null!);
    }

    for (let i = 1; i <= totalDaysInMonth; i++) {
      this.calendarDays.push(i);
    }

    const lastDayOfMonth = new Date(year, month + 1, 0).getDay() - 1;
    if (lastDayOfMonth >= 0) {
      for (let i = lastDayOfMonth; i < 6; i++) {
        this.calendarDays.push(null!);
      }
    }
  }

  private generateYears(currentYear: number): void {
    for (let i = currentYear - 10; i <= currentYear + 10; i++) {
      this.years.push(i);
    }
  }
  updateCalendar() {
    this.generateCalendar(this.year, this.getMonthIndex());
  }

  previousMonth() {
    const currentMonthIndex = this.getMonthIndex();
    const previousMonthIndex =
      currentMonthIndex === 0 ? 11 : currentMonthIndex - 1;
    this.monthCurrent = previousMonthIndex;
    this.month = this.getMonthName(previousMonthIndex);
    if (previousMonthIndex === 11) {
      this.year--;
    }
    this.updateCalendar();
  }

  nextMonth() {
    const currentMonthIndex = this.getMonthIndex();
    const nextMonthIndex = currentMonthIndex === 11 ? 0 : currentMonthIndex + 1;
    this.month = this.getMonthName(nextMonthIndex);
    this.monthCurrent = nextMonthIndex;
    if (nextMonthIndex === 0) {
      this.year++;
    }
    this.updateCalendar();
  }

  private getMonthIndex(): number {
    return this.getMonthNames().indexOf(this.month);
  }
  private getMonthNames(): string[] {
    const monthNames = [
      'Tháng 1',
      'Tháng 2',
      'Tháng 3',
      'Tháng 4',
      'Tháng 5',
      'Tháng 6',
      'Tháng 7',
      'Tháng 8',
      'Tháng 9',
      'Tháng 10',
      'Tháng 11',
      'Tháng 12',
    ];
    return monthNames;
  }
  private getMonthName(monthIndex: number): string {
    return this.getMonthNames()[monthIndex];
  }

  toggleYearDropdown() {
    this.yearDropdownVisible = !this.yearDropdownVisible;
  }

  selectYear(year: number) {
    this.year = year;
    this.generateCalendar(year, new Date(`${this.month} 1 ${year}`).getMonth());
    this.toggleYearDropdown();
  }

  onCarChange(event: any) {
    this.schedules.length = 0;
    this.scheduleLists.length = 0;
    const currentDate = new Date();
    this.month = this.getMonthName(currentDate.getMonth());
    this.monthCurrent = currentDate.getMonth();
    this.year = currentDate.getFullYear();
    this.selectedCar = event.value;
    this.scheduleService.findByCar(this.selectedCar.id).subscribe({
      next: (res) => {
        this.scheduleLists = res;
        for (let x of this.scheduleLists) {
          const date = new Date(x.date);
          date.setHours(0, 0, 0, 0);
          this.schedules.push(date);
        }
        this.getDayByMonthAndYear(this.monthCurrent, this.year);
        console.log(this.calendarCars);
      },
      error: (err) => {
        console.log(err);
      },
    });
    this.generateCalendar(currentDate.getFullYear(), currentDate.getMonth());
    this.generateYears(currentDate.getFullYear());
  }

  saveSchedule() {
    this.dialogLoading = this.dialog.open(DialogLoadingComponent, {
      disableClose: true,
    });
    this.scheduleLists.length = 0;
    for (let x of this.schedules) {
      const year = x.getFullYear();
      const month = ('0' + (x.getMonth() + 1)).slice(-2);
      const day = ('0' + x.getDate()).slice(-2);
      const dateString = `${year}-${month}-${day}`;
      console.log(dateString);

      const isDateDuplicate = this.scheduleLists.some(
        (s) => s.date === dateString
      );
      if (!isDateDuplicate) {
        this.scheduleLists.push({
          date: dateString,
          available: true,
          carId: this.selectedCar.id,
        });
      }
    }
    if (this.scheduleLists.length === 0) {
      this.scheduleLists.push({
        date: null,
        available: null,
        carId: this.selectedCar.id,
      });
    }
    this.scheduleService.saveSchedule(this.scheduleLists).subscribe({
      next: (res) => {
        this.dialogLoading?.close();
        this.messageService.add({
          severity: 'success',
          summary: 'Thành công',
          detail: 'Bạn đã cập nhật lịch thành công',
        });
      },
      error: (err) => {
        this.messageService.add({
          severity: 'error',
          summary: 'Lỗi',
          detail: 'Vui lòng thử lại',
        });
        console.log(err);
      },
    });
  }
  handleSearch(searchTerm: string) {
    this.filteredCars = this.mycars.filter((car) => {
      const nameMatch = car.name
        .toLowerCase()
        .includes(searchTerm.toLowerCase());
      const plateMatch = car.licensePlates
        .toLowerCase()
        .includes(searchTerm.toLowerCase());
      return nameMatch || plateMatch;
    });
  }
}
