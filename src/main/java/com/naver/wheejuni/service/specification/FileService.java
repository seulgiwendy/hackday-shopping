package com.naver.wheejuni.service.specification;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    void uploadFile(MultipartFile file) throws FileProcessingException;

}
