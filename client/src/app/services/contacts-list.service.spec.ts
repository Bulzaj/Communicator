import { TestBed, inject } from '@angular/core/testing';

import { ContactsListService } from './contacts-list.service';

describe('ContactsListService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ContactsListService]
    });
  });

  it('should be created', inject([ContactsListService], (service: ContactsListService) => {
    expect(service).toBeTruthy();
  }));
});
