import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TreeStationComponent } from './tree-station.component';

describe('TreeStationComponent', () => {
  let component: TreeStationComponent;
  let fixture: ComponentFixture<TreeStationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TreeStationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TreeStationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
