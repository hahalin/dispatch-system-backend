-- 檢查並添加缺失的欄位
DO $$
BEGIN
    RAISE NOTICE '開始執行遷移：添加必要欄位';
    
    -- 添加 english_name 欄位
    IF NOT EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'english_name'
    ) THEN
        RAISE NOTICE '添加 english_name 欄位';
        ALTER TABLE personnel ADD COLUMN english_name VARCHAR(50);
        RAISE NOTICE 'english_name 欄位添加成功';
    ELSE
        RAISE NOTICE 'english_name 欄位已存在';
    END IF;

    -- 添加 gender 欄位
    IF NOT EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'gender'
    ) THEN
        RAISE NOTICE '添加 gender 欄位';
        ALTER TABLE personnel ADD COLUMN gender VARCHAR(10);
        RAISE NOTICE 'gender 欄位添加成功';
    ELSE
        RAISE NOTICE 'gender 欄位已存在';
    END IF;

    -- 添加 birth_year 欄位
    IF NOT EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'birth_year'
    ) THEN
        RAISE NOTICE '添加 birth_year 欄位';
        ALTER TABLE personnel ADD COLUMN birth_year INT;
        RAISE NOTICE 'birth_year 欄位添加成功';
    ELSE
        RAISE NOTICE 'birth_year 欄位已存在';
    END IF;

    -- 添加 age 欄位
    IF NOT EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'age'
    ) THEN
        RAISE NOTICE '添加 age 欄位';
        ALTER TABLE personnel ADD COLUMN age INTEGER;
        RAISE NOTICE 'age 欄位添加成功';
    ELSE
        RAISE NOTICE 'age 欄位已存在';
    END IF;

    -- 添加 phone 欄位
    IF NOT EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'phone'
    ) THEN
        RAISE NOTICE '添加 phone 欄位';
        ALTER TABLE personnel ADD COLUMN phone VARCHAR(20);
        RAISE NOTICE 'phone 欄位添加成功';
    ELSE
        RAISE NOTICE 'phone 欄位已存在';
    END IF;

    -- 添加 email 欄位
    IF NOT EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'email'
    ) THEN
        RAISE NOTICE '添加 email 欄位';
        ALTER TABLE personnel ADD COLUMN email VARCHAR(100);
        RAISE NOTICE 'email 欄位添加成功';
    ELSE
        RAISE NOTICE 'email 欄位已存在';
    END IF;

    -- 添加 education 欄位
    IF NOT EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'education'
    ) THEN
        RAISE NOTICE '添加 education 欄位';
        ALTER TABLE personnel ADD COLUMN education VARCHAR(100);
        RAISE NOTICE 'education 欄位添加成功';
    ELSE
        RAISE NOTICE 'education 欄位已存在';
    END IF;

    -- 添加 major 欄位
    IF NOT EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'major'
    ) THEN
        RAISE NOTICE '添加 major 欄位';
        ALTER TABLE personnel ADD COLUMN major VARCHAR(100);
        RAISE NOTICE 'major 欄位添加成功';
    ELSE
        RAISE NOTICE 'major 欄位已存在';
    END IF;

    -- 添加 school 欄位
    IF NOT EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'school'
    ) THEN
        RAISE NOTICE '添加 school 欄位';
        ALTER TABLE personnel ADD COLUMN school VARCHAR(100);
        RAISE NOTICE 'school 欄位添加成功';
    ELSE
        RAISE NOTICE 'school 欄位已存在';
    END IF;

    -- 添加 graduation_year 欄位
    IF NOT EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'graduation_year'
    ) THEN
        RAISE NOTICE '添加 graduation_year 欄位';
        ALTER TABLE personnel ADD COLUMN graduation_year INTEGER;
        RAISE NOTICE 'graduation_year 欄位添加成功';
    ELSE
        RAISE NOTICE 'graduation_year 欄位已存在';
    END IF;

    -- 添加 status 欄位
    IF NOT EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'status'
    ) THEN
        RAISE NOTICE '添加 status 欄位';
        ALTER TABLE personnel ADD COLUMN status VARCHAR(20);
        RAISE NOTICE 'status 欄位添加成功';
    ELSE
        RAISE NOTICE 'status 欄位已存在';
    END IF;

    -- 添加 current_client 欄位
    IF NOT EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'current_client'
    ) THEN
        RAISE NOTICE '添加 current_client 欄位';
        ALTER TABLE personnel ADD COLUMN current_client VARCHAR(50);
        RAISE NOTICE 'current_client 欄位添加成功';
    ELSE
        RAISE NOTICE 'current_client 欄位已存在';
    END IF;

    -- 添加 current_position 欄位
    IF NOT EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'current_position'
    ) THEN
        RAISE NOTICE '添加 current_position 欄位';
        ALTER TABLE personnel ADD COLUMN current_position VARCHAR(50);
        RAISE NOTICE 'current_position 欄位添加成功';
    ELSE
        RAISE NOTICE 'current_position 欄位已存在';
    END IF;

    -- 添加 experience 欄位
    IF NOT EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'experience'
    ) THEN
        RAISE NOTICE '添加 experience 欄位';
        ALTER TABLE personnel ADD COLUMN experience INTEGER;
        RAISE NOTICE 'experience 欄位添加成功';
    ELSE
        RAISE NOTICE 'experience 欄位已存在';
    END IF;

    -- 添加 current_salary 欄位
    IF NOT EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'current_salary'
    ) THEN
        RAISE NOTICE '添加 current_salary 欄位';
        ALTER TABLE personnel ADD COLUMN current_salary INTEGER;
        RAISE NOTICE 'current_salary 欄位添加成功';
    ELSE
        RAISE NOTICE 'current_salary 欄位已存在';
    END IF;

    -- 添加 expected_salary 欄位
    IF NOT EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'expected_salary'
    ) THEN
        RAISE NOTICE '添加 expected_salary 欄位';
        ALTER TABLE personnel ADD COLUMN expected_salary VARCHAR(50);
        RAISE NOTICE 'expected_salary 欄位添加成功';
    ELSE
        RAISE NOTICE 'expected_salary 欄位已存在';
    END IF;

    -- 添加 interview_skill 欄位
    IF NOT EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'interview_skill'
    ) THEN
        RAISE NOTICE '添加 interview_skill 欄位';
        ALTER TABLE personnel ADD COLUMN interview_skill INTEGER CHECK (interview_skill >= 1 AND interview_skill <= 10);
        RAISE NOTICE 'interview_skill 欄位添加成功';
    ELSE
        RAISE NOTICE 'interview_skill 欄位已存在';
    END IF;

    -- 添加 appearance 欄位
    IF NOT EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'appearance'
    ) THEN
        RAISE NOTICE '添加 appearance 欄位';
        ALTER TABLE personnel ADD COLUMN appearance INTEGER CHECK (appearance >= 1 AND appearance <= 10);
        RAISE NOTICE 'appearance 欄位添加成功';
    ELSE
        RAISE NOTICE 'appearance 欄位已存在';
    END IF;

    -- 添加 technical_ability 欄位
    IF NOT EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'technical_ability'
    ) THEN
        RAISE NOTICE '添加 technical_ability 欄位';
        ALTER TABLE personnel ADD COLUMN technical_ability INTEGER CHECK (technical_ability >= 1 AND technical_ability <= 10);
        RAISE NOTICE 'technical_ability 欄位添加成功';
    ELSE
        RAISE NOTICE 'technical_ability 欄位已存在';
    END IF;

    -- 添加 adaptability 欄位
    IF NOT EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'adaptability'
    ) THEN
        RAISE NOTICE '添加 adaptability 欄位';
        ALTER TABLE personnel ADD COLUMN adaptability INTEGER CHECK (adaptability >= 1 AND adaptability <= 10);
        RAISE NOTICE 'adaptability 欄位添加成功';
    ELSE
        RAISE NOTICE 'adaptability 欄位已存在';
    END IF;

    -- 添加 communication_skill 欄位
    IF NOT EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'communication_skill'
    ) THEN
        RAISE NOTICE '添加 communication_skill 欄位';
        ALTER TABLE personnel ADD COLUMN communication_skill INTEGER CHECK (communication_skill >= 1 AND communication_skill <= 10);
        RAISE NOTICE 'communication_skill 欄位添加成功';
    ELSE
        RAISE NOTICE 'communication_skill 欄位已存在';
    END IF;

    -- 添加 problem_solving 欄位
    IF NOT EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'problem_solving'
    ) THEN
        RAISE NOTICE '添加 problem_solving 欄位';
        ALTER TABLE personnel ADD COLUMN problem_solving INTEGER CHECK (problem_solving >= 1 AND problem_solving <= 10);
        RAISE NOTICE 'problem_solving 欄位添加成功';
    ELSE
        RAISE NOTICE 'problem_solving 欄位已存在';
    END IF;

    -- 添加 learning_ability 欄位
    IF NOT EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'learning_ability'
    ) THEN
        RAISE NOTICE '添加 learning_ability 欄位';
        ALTER TABLE personnel ADD COLUMN learning_ability INTEGER CHECK (learning_ability >= 1 AND learning_ability <= 10);
        RAISE NOTICE 'learning_ability 欄位添加成功';
    ELSE
        RAISE NOTICE 'learning_ability 欄位已存在';
    END IF;

    -- 添加 teamwork 欄位
    IF NOT EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'teamwork'
    ) THEN
        RAISE NOTICE '添加 teamwork 欄位';
        ALTER TABLE personnel ADD COLUMN teamwork INTEGER CHECK (teamwork >= 1 AND teamwork <= 10);
        RAISE NOTICE 'teamwork 欄位添加成功';
    ELSE
        RAISE NOTICE 'teamwork 欄位已存在';
    END IF;

    -- 添加 rating 欄位
    IF NOT EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'rating'
    ) THEN
        RAISE NOTICE '添加 rating 欄位';
        ALTER TABLE personnel ADD COLUMN rating DOUBLE PRECISION;
        RAISE NOTICE 'rating 欄位添加成功';
    ELSE
        RAISE NOTICE 'rating 欄位已存在';
    END IF;

    -- 添加 introduction 欄位
    IF NOT EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'introduction'
    ) THEN
        RAISE NOTICE '添加 introduction 欄位';
        ALTER TABLE personnel ADD COLUMN introduction TEXT;
        RAISE NOTICE 'introduction 欄位添加成功';
    ELSE
        RAISE NOTICE 'introduction 欄位已存在';
    END IF;

    -- 添加 join_date 欄位
    IF NOT EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'join_date'
    ) THEN
        RAISE NOTICE '添加 join_date 欄位';
        ALTER TABLE personnel ADD COLUMN join_date DATE;
        RAISE NOTICE 'join_date 欄位添加成功';
    ELSE
        RAISE NOTICE 'join_date 欄位已存在';
    END IF;

    -- 添加 dispatch_count 欄位
    IF NOT EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'dispatch_count'
    ) THEN
        RAISE NOTICE '添加 dispatch_count 欄位';
        ALTER TABLE personnel ADD COLUMN dispatch_count INTEGER DEFAULT 0;
        RAISE NOTICE 'dispatch_count 欄位添加成功';
    ELSE
        RAISE NOTICE 'dispatch_count 欄位已存在';
    END IF;

    -- 添加 success_rate 欄位
    IF NOT EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'success_rate'
    ) THEN
        RAISE NOTICE '添加 success_rate 欄位';
        ALTER TABLE personnel ADD COLUMN success_rate INTEGER DEFAULT 0;
        RAISE NOTICE 'success_rate 欄位添加成功';
    ELSE
        RAISE NOTICE 'success_rate 欄位已存在';
    END IF;

    -- 添加 created_by 欄位
    IF NOT EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'created_by'
    ) THEN
        RAISE NOTICE '添加 created_by 欄位';
        ALTER TABLE personnel ADD COLUMN created_by INT REFERENCES app_user(id);
        RAISE NOTICE 'created_by 欄位添加成功';
    ELSE
        RAISE NOTICE 'created_by 欄位已存在';
    END IF;

    -- 驗證所有欄位是否成功添加
    IF EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'english_name'
    ) AND EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'gender'
    ) AND EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'birth_year'
    ) AND EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'age'
    ) AND EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'phone'
    ) AND EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'email'
    ) AND EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'education'
    ) AND EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'major'
    ) AND EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'school'
    ) AND EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'graduation_year'
    ) AND EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'status'
    ) AND EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'current_client'
    ) AND EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'current_position'
    ) AND EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'experience'
    ) AND EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'current_salary'
    ) AND EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'expected_salary'
    ) AND EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'interview_skill'
    ) AND EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'appearance'
    ) AND EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'technical_ability'
    ) AND EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'adaptability'
    ) AND EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'communication_skill'
    ) AND EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'problem_solving'
    ) AND EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'learning_ability'
    ) AND EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'teamwork'
    ) AND EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'rating'
    ) AND EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'introduction'
    ) AND EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'join_date'
    ) AND EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'dispatch_count'
    ) AND EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'success_rate'
    ) AND EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'personnel' 
        AND column_name = 'created_by'
    ) THEN
        RAISE NOTICE '驗證成功：所有必要欄位都已存在';
    ELSE
        RAISE EXCEPTION '驗證失敗：部分必要欄位未成功添加';
    END IF;
END $$; 