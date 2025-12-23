import { TestBed } from '@angular/core/testing';

import { BeneficioApi } from './beneficio-api';

describe('BeneficioApi', () => {
  let service: BeneficioApi;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BeneficioApi);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
