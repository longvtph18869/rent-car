import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NoComponent } from './no.component';

describe('NoComponent', () => {
  let component: NoComponent;
  let fixture: ComponentFixture<NoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
