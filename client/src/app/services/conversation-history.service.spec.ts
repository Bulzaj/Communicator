import { TestBed, inject } from '@angular/core/testing';

import { ConversationHistoryService } from './conversation-history.service';

describe('ConversationHistoryService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ConversationHistoryService]
    });
  });

  it('should be created', inject([ConversationHistoryService], (service: ConversationHistoryService) => {
    expect(service).toBeTruthy();
  }));
});
