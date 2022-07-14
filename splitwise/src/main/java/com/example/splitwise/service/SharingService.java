package com.example.splitwise.service;

import com.example.splitwise.request.SharingRequest;
import com.example.splitwise.response.BaseResponse;

public interface SharingService {

    public void shareAmount(SharingRequest sharingRequest, BaseResponse baseResponse);
}
