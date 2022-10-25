import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TourCreationComponent } from './tour-creation.component';

describe('TourCreationComponent', () => {
  let component: TourCreationComponent;
  let fixture: ComponentFixture<TourCreationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TourCreationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TourCreationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
