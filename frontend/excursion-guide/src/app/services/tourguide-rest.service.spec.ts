import { TestBed } from '@angular/core/testing';

import { TourguideRestService } from './tourguide-rest.service';

describe('TourguideRestService', () => {
  let service: TourguideRestService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TourguideRestService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
