import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterCarComponent } from './register-car.component';

describe('RegisterCarComponent', () => {
  let component: RegisterCarComponent;
  let fixture: ComponentFixture<RegisterCarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegisterCarComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegisterCarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
