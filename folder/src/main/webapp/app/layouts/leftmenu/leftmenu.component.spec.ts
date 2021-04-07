import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { LeftmenuComponent } from './leftmenu.component';

describe('LeftmenuComponent', () => {
  let component: LeftmenuComponent;
  let fixture: ComponentFixture<LeftmenuComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LeftmenuComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LeftmenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  it('should render menu in the top navigation', async(() => {
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('nav').textContent).toContain('LinkLink');
  }));
});
