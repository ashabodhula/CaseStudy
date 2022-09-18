import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AuthorspaceComponent } from './authorspace.component';

describe('AuthorspaceComponent', () => {
  let component: AuthorspaceComponent;
  let fixture: ComponentFixture<AuthorspaceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AuthorspaceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AuthorspaceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
