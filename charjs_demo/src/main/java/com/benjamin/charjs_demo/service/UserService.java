package com.benjamin.charjs_demo.service;

import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    int addUser(MultipartFile file) throws Exception;


}
