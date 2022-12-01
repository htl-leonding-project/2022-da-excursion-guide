import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UnlockActivityComponent } from './unlock-activity.component';

describe('UnlockActivityComponent', () => {
  let component: UnlockActivityComponent;
  let fixture: ComponentFixture<UnlockActivityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UnlockActivityComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UnlockActivityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
