import { TestBed } from '@angular/core/testing';

import { TransferenciaApi } from './transferencia-api';

describe('TransferenciaApi', () => {
  let service: TransferenciaApi;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TransferenciaApi);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
