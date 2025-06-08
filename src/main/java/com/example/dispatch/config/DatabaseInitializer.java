package com.example.dispatch.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Component
public class DatabaseInitializer {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void initialize() {
        try {
            // 檢查並添加 current_client 欄位
            
            /*
            jdbcTemplate.execute("""
                DO $$
                BEGIN
                    IF NOT EXISTS (
                        SELECT 1 
                        FROM information_schema.columns 
                        WHERE table_name = 'personnel' 
                        AND column_name = 'current_client'
                    ) THEN
                        ALTER TABLE personnel ADD COLUMN current_client VARCHAR(50);
                    END IF;
                END $$;
            """);
            
            System.out.println("資料庫初始化完成：current_client 欄位已檢查/添加");
            */
            System.out.println("資料庫初始化完成");
        } catch (Exception e) {
            System.err.println("資料庫初始化失敗：" + e.getMessage());
            e.printStackTrace();
        }
    }
} 