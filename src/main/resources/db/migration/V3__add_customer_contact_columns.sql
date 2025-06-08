-- 添加 customer_contact 表格缺少的欄位
ALTER TABLE customer_contact
ADD COLUMN IF NOT EXISTS phone_ext VARCHAR(20),
ADD COLUMN IF NOT EXISTS title VARCHAR(100),
ADD COLUMN IF NOT EXISTS gender VARCHAR(10),
ADD COLUMN IF NOT EXISTS mobile VARCHAR(20);

-- 驗證欄位是否成功添加
DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'customer_contact' 
        AND column_name = 'phone_ext'
    ) THEN
        RAISE EXCEPTION 'phone_ext 欄位添加失敗';
    END IF;
    
    IF NOT EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'customer_contact' 
        AND column_name = 'title'
    ) THEN
        RAISE EXCEPTION 'title 欄位添加失敗';
    END IF;
    
    IF NOT EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'customer_contact' 
        AND column_name = 'gender'
    ) THEN
        RAISE EXCEPTION 'gender 欄位添加失敗';
    END IF;

    IF NOT EXISTS (
        SELECT 1 
        FROM information_schema.columns 
        WHERE table_name = 'customer_contact' 
        AND column_name = 'mobile'
    ) THEN
        RAISE EXCEPTION 'mobile 欄位添加失敗';
    END IF;
END $$; 