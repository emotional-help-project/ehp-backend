package com.epam.rd.service;

import com.epam.rd.payload.request.AdviceAdminRequest;
import com.epam.rd.payload.request.QuestionAnswerAdminRequest;
import com.epam.rd.payload.response.NewQuestionResponse;

public interface AdminService {
    
    NewQuestionResponse addQuestionAnswersForTest(QuestionAnswerAdminRequest request);

    String addAdviceWithLinksForTest(AdviceAdminRequest request);
}
