import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReaderhomeComponent } from './readerhome.component';

describe('ReaderhomeComponent', () => {
  let component: ReaderhomeComponent;
  let fixture: ComponentFixture<ReaderhomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReaderhomeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReaderhomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
