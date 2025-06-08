-- ==============================================
-- 插入基本資料，包含預設用戶
-- ==============================================

-- 1. 預設用戶
INSERT INTO app_user (username, password, name, role)
VALUES
('manager', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', '系統管理員', 'USER'),
('melody', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', '美樂蒂', 'USER')
ON CONFLICT (username) DO NOTHING;

-- 2. 技能分類
INSERT INTO skill_category (name)
VALUES
('程式語言'),
('資料庫'),
('框架'),
('開發工具'),
('作業系統'),
('雲端服務'),
('其他')
ON CONFLICT (name) DO NOTHING;

-- 3. 技能
INSERT INTO skill (category_id, name)
SELECT 
    (SELECT id FROM skill_category WHERE name = '程式語言'),
    'Java'
WHERE NOT EXISTS (
    SELECT 1 FROM skill 
    WHERE category_id = (SELECT id FROM skill_category WHERE name = '程式語言')
    AND name = 'Java'
);

INSERT INTO skill (category_id, name)
SELECT 
    (SELECT id FROM skill_category WHERE name = '資料庫'),
    'PostgreSQL'
WHERE NOT EXISTS (
    SELECT 1 FROM skill 
    WHERE category_id = (SELECT id FROM skill_category WHERE name = '資料庫')
    AND name = 'PostgreSQL'
);

-- 4. 客戶
INSERT INTO customer (name, industry, address, created_by)
SELECT 
    '示例客戶',
    '科技業',
    '台北市信義區信義路五段7號',
    (SELECT id FROM app_user WHERE username = 'manager')
WHERE NOT EXISTS (
    SELECT 1 FROM customer WHERE name = '示例客戶'
);

-- 5. 客戶聯絡人
INSERT INTO customer_contact (customer_id, name, phone_ext, mobile)
SELECT 
    (SELECT id FROM customer WHERE name = '示例客戶'),
    '王小明',
    '1234',
    '0912345678'
WHERE NOT EXISTS (
    SELECT 1 FROM customer_contact 
    WHERE customer_id = (SELECT id FROM customer WHERE name = '示例客戶')
    AND name = '王小明'
);

-- 6. 預設人員（5筆範例）
INSERT INTO personnel (
    name, english_name, gender, birth_year, age, phone, email, 
    education, major, school, graduation_year, status, current_client, current_position,
    experience, current_salary, expected_salary, interview_skill, appearance, 
    technical_ability, adaptability, communication_skill, problem_solving, 
    learning_ability, teamwork, rating, introduction, join_date, 
    dispatch_count, success_rate, created_by
) VALUES 
('張志偉', 'Michael', '男', 1990, 34, '0912-345-678', 'michael.chang@example.com', 
 '碩士', '資訊工程', '台灣大學', 2012, '已派遣', '台灣科技股份有限公司', '後端開發工程師',
 5, 75000, '75,000-90,000', 8, 8, 9, 8, 8, 9, 8, 8, 
 8.3, '資深Java後端開發工程師，具備豐富的企業級系統開發經驗', DATE '2024-01-15', 
 2, 100, 1),
 
('李美慧', 'Amy', '女', 1988, 36, '0923-456-789', 'amy.lee@example.com',
 '學士', '資訊管理', '政治大學', 2010, '可派遣', NULL, NULL,
 7, 68000, '70,000-85,000', 9, 8, 8, 8, 9, 8, 8, 9,
 8.4, '前端開發專家，擅長React和現代化前端技術', DATE '2023-08-01',
 1, 100, 1),
 
('王建宏', 'Kevin', '男', 1985, 39, '0934-567-890', 'kevin.wang@example.com',
 '碩士', '電腦科學', '清華大學', 2007, '已派遣', '創新軟體開發公司', '全端開發工程師',
 9, 85000, '85,000-110,000', 8, 8, 9, 9, 8, 9, 9, 9,
 8.6, '全端開發工程師，具備完整的軟體開發生命週期經驗', DATE '2024-02-01',
 3, 100, 2),
 
('陳淑雯', 'Susan', '女', 1992, 32, '0945-678-901', 'susan.chen@example.com',
 '學士', '資訊工程', '交通大學', 2014, '可派遣', NULL, NULL,
 3, 55000, '55,000-70,000', 7, 8, 7, 8, 8, 7, 8, 8,
 7.6, '新興前端開發人才，學習能力強，適應性佳', DATE '2024-05-15',
 0, 0, 1),
 
('林俊男', 'Frank', '男', 1987, 37, '0956-789-012', 'frank.lin@example.com',
 '碩士', '軟體工程', '成功大學', 2009, '可派遣', NULL, NULL,
 8, 80000, '80,000-100,000', 8, 8, 9, 8, 8, 9, 9, 8,
 8.4, '系統架構師，具備大型分散式系統設計經驗', DATE '2023-06-01',
 1, 100, 2);

-- 7. 預設專案
INSERT INTO project (customer_id, name, start_date, end_date, description, required_headcount, remarks, created_by) 
VALUES 
(1, 'ERP系統升級專案', '2024-01-15', '2024-06-30', '將現有ERP系統從舊版本升級到最新版本，包含功能增強和介面改善', 5, '高優先級專案，需要經驗豐富的Java開發人員', 1),
(2, '電商平台建置', '2024-02-01', '2024-10-15', '建立完整的電子商務平台，包含前台購物網站和後台管理系統', 6, '需要全端開發人員和UI/UX設計師', 1),
(3, '智慧工廠系統', '2024-05-01', '2024-12-31', '開發工廠自動化管理系統，整合IoT設備和生產流程', 7, '長期專案，需要多種技術專長', 1);

-- 8. 預設人員技能
INSERT INTO personnel_skill (personnel_id, skill_id, years_of_experience, proficiency) 
VALUES 
-- 張志偉 (Java後端專家)
(1, 6, 5.0, 9),   -- Java
(1, 7, 4.5, 9),   -- Spring Boot
(1, 11, 3.5, 8),  -- SQL Server
(1, 12, 3.0, 7),  -- MySQL
-- 李美慧 (前端開發專家)
(2, 1, 4.0, 8),   -- ReactJS
(2, 3, 4.5, 8),   -- JavaScript
(2, 4, 5.0, 9),   -- HTML5
(2, 5, 5.0, 9),   -- CSS3
-- 王建宏 (全端開發)
(3, 6, 6.0, 9),   -- Java
(3, 7, 5.5, 9),   -- Spring Boot
(3, 1, 3.5, 8),   -- ReactJS
(3, 12, 4.0, 8),  -- MySQL
-- 陳淑雯 (前端工程師)
(4, 2, 3.5, 7),   -- Vue.js
(4, 3, 4.0, 7),   -- JavaScript
(4, 4, 4.5, 8),   -- HTML5
(4, 5, 4.5, 8),   -- CSS3
-- 林俊男 (後端架構師)
(5, 6, 7.0, 9),   -- Java
(5, 7, 6.5, 9),   -- Spring Boot
(5, 11, 5.0, 9),  -- SQL Server
(5, 14, 4.5, 8),  -- Oracle
(5, 17, 3.5, 8);  -- Docker

-- 9. 預設專案技能需求
INSERT INTO project_skill (project_id, skill_id, importance) 
VALUES 
-- ERP系統升級專案
(1, 6, 5),   -- Java (必要)
(1, 7, 5),   -- Spring Boot (必要)
(1, 11, 4),  -- SQL Server (重要)
-- 電商平台建置
(2, 6, 5),   -- Java (必要)
(2, 1, 5),   -- ReactJS (必要)
(2, 12, 4),  -- MySQL (重要)
-- 智慧工廠系統
(3, 6, 5),   -- Java (必要)
(3, 17, 4),  -- Docker (重要)
(3, 11, 4);  -- SQL Server (重要)

-- 10. 預設人員專案經歷
INSERT INTO personnel_project (personnel_id, project_id, title, responsibilities, start_date, end_date) 
VALUES 
-- ERP系統升級專案的人員分配
(1, 1, '後端開發工程師', '負責ERP核心模組開發和資料庫設計', '2024-01-15', '2024-06-30'),
-- 電商平台建置的人員分配
(3, 2, '全端開發工程師', '負責前後端開發和系統整合', '2024-02-01', '2024-10-15'),
(2, 2, '前端開發工程師', '負責電商前台介面開發', '2024-02-01', '2024-10-15');

-- 11. 預設人員語言能力
INSERT INTO personnel_languages (personnel_id, language, level) VALUES 
(1, '中文', '母語'),
(1, '英文', '中等'),
(2, '中文', '母語'),
(2, '英文', '流利'),
(3, '中文', '母語'),
(3, '英文', '流利'),
(4, '中文', '母語'),
(4, '英文', '中等'),
(5, '中文', '母語'),
(5, '英文', '流利');

-- 12. 預設人員產業經驗
INSERT INTO personnel_industries (personnel_id, industry, years, description) VALUES 
(1, '資訊科技', 5, '企業級軟體開發'),
(1, '金融服務', 2, '金融系統開發'),
(2, '電子商務', 4, '電商平台前端開發'),
(3, '製造業', 5, '智慧製造系統開發'),
(3, '軟體服務', 4, '企業級應用開發'),
(4, '新創公司', 2, '前端開發'),
(5, '電信業', 4, '大型系統架構設計');

-- 13. 預設員工資料（3筆範例）
INSERT INTO employees (
    name, english_name, gender, birth_year, age, phone, email,
    education, school, department, graduation_year,
    status, experience, current_salary, expected_salary,
    interview_skill, appearance, technical_ability, adaptability,
    communication_skill, problem_solving, learning_ability, teamwork,
    rating, introduction, join_date, dispatch_count, success_rate
) VALUES 
('林春宏', 'Frank', '男', 1985, 39, '0912-345-678', 'frank.lin@example.com',
 '碩士', '雲林科技大學', '資訊管理系', 2011,
 '可派遣', 13, 95000, '90,000-120,000',
 8, 8, 9, 9, 8, 9, 9, 8,
 8.5, '具備13年以上軟體開發經驗，熟悉多種程式語言與框架，從傳統VB6到現代React都有實戰經驗。參與過半導體、金融、製造業等大型企業專案，擅長系統架構設計與技術整合。',
 '2024-07-15', 0, 0),

('陳小明', 'Kevin', '男', 1990, 34, '0923-456-789', 'kevin.chen@example.com',
 '大學', '國立台灣大學', '資訊工程系', 2016,
 '已派遣', 8, 75000, '70,000-90,000',
 8, 8, 9, 8, 8, 9, 8, 9,
 8.3, '8年軟體開發經驗，專精於Java後端開發與微服務架構。具備大型系統設計經驗，擅長高併發系統優化。',
 '2023-06-01', 3, 100),

('王雅婷', 'Emily', '女', 1992, 32, '0934-567-890', 'emily.wang@example.com',
 '大學', '政治大學', '會計系', 2017,
 '可派遣', 7, 68000, '65,000-80,000',
 9, 8, 7, 8, 9, 8, 7, 8,
 8.0, '7年財務會計經驗，具備會計師執照。熟悉ERP系統導入與財務流程優化，擅長成本分析與預算編製。',
 '2023-09-01', 1, 100);

-- 14. 預設員工技能
INSERT INTO employee_skills (employee_id, category, skill_name, proficiency, experience) VALUES 
-- 林春宏的技能
(1, '程式語言', 'C#.NET', 9, 8),
(1, '程式語言', 'Java', 8, 6),
(1, '前端技術', 'React', 8, 4),
(1, '資料庫', 'MS SQL', 9, 10),
-- 陳小明的技能
(2, '程式語言', 'Java', 9, 8),
(2, '前端技術', 'Vue.js', 8, 4),
(2, '資料庫', 'PostgreSQL', 8, 5),
-- 王雅婷的技能
(3, '工具', 'Excel', 9, 7),
(3, '工具', 'SAP', 8, 4),
(3, '其他', '財務分析', 9, 7);

-- 15. 預設員工語言能力
INSERT INTO employee_languages (employee_id, language, level) VALUES 
(1, '中文', '母語'),
(1, '英文', '中等'),
(2, '中文', '母語'),
(2, '英文', '流利'),
(3, '中文', '母語'),
(3, '英文', '中等');

-- 16. 預設員工產業經驗
INSERT INTO employee_industries (employee_id, industry, years) VALUES 
(1, '半導體製造', 3),
(1, '金融服務', 2),
(2, '軟體服務', 3),
(2, '電子商務', 2),
(3, '製造業', 4),
(3, '零售業', 3);
