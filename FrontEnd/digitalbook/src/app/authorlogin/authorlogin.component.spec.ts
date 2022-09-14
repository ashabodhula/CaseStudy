import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AuthorloginComponent } from './authorlogin.component';

describe('AuthorloginComponent', () => {
  let component: AuthorloginComponent;
  let fixture: ComponentFixture<AuthorloginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AuthorloginComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AuthorloginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
