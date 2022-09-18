import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReaderbooksComponent } from './readerbooks.component';

describe('ReaderbooksComponent', () => {
  let component: ReaderbooksComponent;
  let fixture: ComponentFixture<ReaderbooksComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReaderbooksComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReaderbooksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
