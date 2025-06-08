package com.example.dispatch.config;

import com.example.dispatch.model.AppUser;
import com.example.dispatch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

// @Component  // 暫時禁用，避免啟動時權限問題
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // 檢查是否已有用戶數據
        if (userRepository.count() == 0) {
            // 創建默認管理員用戶
            AppUser adminUser = new AppUser();
            adminUser.setUsername("admin");
            adminUser.setPassword(passwordEncoder.encode("password"));
            adminUser.setName("系統管理員");
            adminUser.setRole("ADMIN");
            userRepository.save(adminUser);

            // 創建測試用戶
            AppUser testUser = new AppUser();
            testUser.setUsername("user");
            testUser.setPassword(passwordEncoder.encode("password"));
            testUser.setName("測試用戶");
            testUser.setRole("USER");
            userRepository.save(testUser);

            System.out.println("Default users created:");
            System.out.println("- admin/password (ADMIN)");
            System.out.println("- user/password (USER)");
        }
    }
}
