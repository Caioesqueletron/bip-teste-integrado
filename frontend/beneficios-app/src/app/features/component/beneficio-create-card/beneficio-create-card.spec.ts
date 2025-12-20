import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BeneficioCreateCard } from './beneficio-create-card';

describe('BeneficioCreateCard', () => {
  let component: BeneficioCreateCard;
  let fixture: ComponentFixture<BeneficioCreateCard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BeneficioCreateCard]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BeneficioCreateCard);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
