import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CrudTaskModalComponent } from './crud-task-modal.component';

describe('CrudTaskModalComponent', () => {
  let component: CrudTaskModalComponent;
  let fixture: ComponentFixture<CrudTaskModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CrudTaskModalComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CrudTaskModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
