-- ==============================================
-- 派遣系統資料庫結構初始化
-- ==============================================

-- 用戶表
CREATE TABLE app_user (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 技能分類表
CREATE TABLE skill_category (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 技能表
CREATE TABLE skill (
    id SERIAL PRIMARY KEY,
    category_id INT REFERENCES skill_category(id),
    name VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (category_id, name)
);

-- 客戶表
CREATE TABLE customer (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    industry VARCHAR(100),
    address TEXT,
    created_by INT REFERENCES app_user(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 客戶聯絡人表
CREATE TABLE customer_contact (
    id SERIAL PRIMARY KEY,
    customer_id INT REFERENCES customer(id) ON DELETE CASCADE,
    name VARCHAR(100) NOT NULL,
    gender VARCHAR(10),
    department VARCHAR(100),
    title VARCHAR(100),
    phone_ext VARCHAR(20),
    mobile VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 人員表（完整版本）
CREATE TABLE personnel (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    english_name VARCHAR(100),
    gender VARCHAR(10),
    birth_year INTEGER,
    age INTEGER,
    phone VARCHAR(20),
    email VARCHAR(100),
    education VARCHAR(50),
    major VARCHAR(100),
    school VARCHAR(100),
    graduation_year INTEGER,
    status VARCHAR(20),
    current_client VARCHAR(100),
    current_position VARCHAR(100),
    dispatch_start_date DATE,
    experience INTEGER,
    current_salary INTEGER,
    expected_salary VARCHAR(100),
    interview_skill INTEGER,
    appearance INTEGER,
    technical_ability INTEGER,
    adaptability INTEGER,
    communication_skill INTEGER,
    problem_solving INTEGER,
    learning_ability INTEGER,
    teamwork INTEGER,
    rating DOUBLE PRECISION,
    introduction TEXT,
    notes TEXT,
    join_date DATE,
    last_dispatch_date DATE,
    dispatch_count INTEGER,
    success_rate INTEGER,
    created_by INTEGER REFERENCES app_user(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 專案表
CREATE TABLE project (
    id SERIAL PRIMARY KEY,
    customer_id INT REFERENCES customer(id),
    name VARCHAR(200) NOT NULL,
    start_date DATE,
    end_date DATE,
    description TEXT,
    required_headcount INT,
    remarks TEXT,
    created_by INT REFERENCES app_user(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 人員技能表（多對多關係）
CREATE TABLE personnel_skill (
    id SERIAL PRIMARY KEY,
    personnel_id INT REFERENCES personnel(id) ON DELETE CASCADE,
    skill_id INT REFERENCES skill(id) ON DELETE CASCADE,
    years_of_experience NUMERIC(5,2),
    proficiency INTEGER CHECK (proficiency >= 1 AND proficiency <= 10),
    description VARCHAR(500),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (personnel_id, skill_id)
);

-- 專案技能需求表（多對多關係）
CREATE TABLE project_skill (
    id SERIAL PRIMARY KEY,
    project_id INT REFERENCES project(id) ON DELETE CASCADE,
    skill_id INT REFERENCES skill(id) ON DELETE CASCADE,
    importance INT DEFAULT 1, -- 1-5 表示重要性
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (project_id, skill_id)
);

-- 人員專案經歷表（多對多關係）
CREATE TABLE personnel_project (
    id SERIAL PRIMARY KEY,
    personnel_id INT REFERENCES personnel(id) ON DELETE CASCADE,
    project_id INT REFERENCES project(id) ON DELETE CASCADE,
    title VARCHAR(100), -- 職稱
    responsibilities TEXT, -- 工作內容
    start_date DATE,
    end_date DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 人員語言表
CREATE TABLE personnel_languages (
    id BIGSERIAL PRIMARY KEY,
    personnel_id BIGINT NOT NULL,
    language VARCHAR(50) NOT NULL,
    level VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (personnel_id) REFERENCES personnel(id) ON DELETE CASCADE
);

-- 人員產業經驗表
CREATE TABLE personnel_industries (
    id BIGSERIAL PRIMARY KEY,
    personnel_id BIGINT NOT NULL,
    industry VARCHAR(100) NOT NULL,
    years INTEGER,
    description VARCHAR(500),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (personnel_id) REFERENCES personnel(id) ON DELETE CASCADE
);

-- 員工表（獨立表）
CREATE TABLE employees (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    english_name VARCHAR(50),
    gender VARCHAR(10),
    birth_year INTEGER,
    age INTEGER,
    phone VARCHAR(20),
    email VARCHAR(100),
    education VARCHAR(50),
    school VARCHAR(100),
    department VARCHAR(100),
    graduation_year INTEGER,
    status VARCHAR(20),
    current_client VARCHAR(50),
    current_position VARCHAR(50),
    dispatch_start_date DATE,
    experience INTEGER,
    current_salary INTEGER,
    expected_salary VARCHAR(50),
    interview_skill INTEGER CHECK (interview_skill >= 1 AND interview_skill <= 10),
    appearance INTEGER CHECK (appearance >= 1 AND appearance <= 10),
    technical_ability INTEGER CHECK (technical_ability >= 1 AND technical_ability <= 10),
    adaptability INTEGER CHECK (adaptability >= 1 AND adaptability <= 10),
    communication_skill INTEGER CHECK (communication_skill >= 1 AND communication_skill <= 10),
    problem_solving INTEGER CHECK (problem_solving >= 1 AND problem_solving <= 10),
    learning_ability INTEGER CHECK (learning_ability >= 1 AND learning_ability <= 10),
    teamwork INTEGER CHECK (teamwork >= 1 AND teamwork <= 10),
    rating DOUBLE PRECISION,
    introduction TEXT,
    notes TEXT,
    join_date DATE,
    last_dispatch_date DATE,
    dispatch_count INTEGER DEFAULT 0,
    success_rate INTEGER DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 員工技能表
CREATE TABLE employee_skills (
    id BIGSERIAL PRIMARY KEY,
    employee_id BIGINT NOT NULL,
    category VARCHAR(50) NOT NULL,
    skill_name VARCHAR(100) NOT NULL,
    proficiency INTEGER CHECK (proficiency >= 1 AND proficiency <= 10),
    experience INTEGER,
    description VARCHAR(500),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE CASCADE
);

-- 員工專案表
CREATE TABLE employee_projects (
    id BIGSERIAL PRIMARY KEY,
    employee_id BIGINT NOT NULL,
    company VARCHAR(100) NOT NULL,
    project_name VARCHAR(200) NOT NULL,
    start_date DATE,
    end_date DATE,
    role VARCHAR(50),
    team_size INTEGER,
    description TEXT,
    responsibilities TEXT,
    achievements TEXT,
    technologies TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE CASCADE
);

-- 員工語言表
CREATE TABLE employee_languages (
    id BIGSERIAL PRIMARY KEY,
    employee_id BIGINT NOT NULL,
    language VARCHAR(50) NOT NULL,
    level VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE CASCADE
);

-- 員工產業經驗表
CREATE TABLE employee_industries (
    id BIGSERIAL PRIMARY KEY,
    employee_id BIGINT NOT NULL,
    industry VARCHAR(100) NOT NULL,
    years INTEGER,
    description VARCHAR(500),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE CASCADE
);

-- ==============================================
-- 創建索引
-- ==============================================

-- Personnel 相關索引
CREATE INDEX idx_personnel_status ON personnel(status);
CREATE INDEX idx_personnel_current_client ON personnel(current_client);
CREATE INDEX idx_personnel_english_name ON personnel(english_name);
CREATE INDEX idx_personnel_languages_personnel_id ON personnel_languages(personnel_id);
CREATE INDEX idx_personnel_industries_personnel_id ON personnel_industries(personnel_id);
CREATE INDEX idx_personnel_industries_industry ON personnel_industries(industry);

-- Employee 相關索引
CREATE INDEX idx_employees_status ON employees(status);
CREATE INDEX idx_employees_name ON employees(name);
CREATE INDEX idx_employees_current_client ON employees(current_client);
CREATE INDEX idx_employee_skills_employee_id ON employee_skills(employee_id);
CREATE INDEX idx_employee_skills_skill_name ON employee_skills(skill_name);
CREATE INDEX idx_employee_projects_employee_id ON employee_projects(employee_id);
CREATE INDEX idx_employee_languages_employee_id ON employee_languages(employee_id);
CREATE INDEX idx_employee_industries_employee_id ON employee_industries(employee_id);
CREATE INDEX idx_employee_industries_industry ON employee_industries(industry);
