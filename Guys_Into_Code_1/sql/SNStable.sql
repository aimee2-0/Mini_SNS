-- ���[�U�[����o�^����e�[�u��
CREATE TABLE users (								
  user_id NUMBER(10) GENERATED ALWAYS AS IDENTITY								
    CONSTRAINT pk_users PRIMARY KEY,								
  user_name VARCHAR2(60) NOT NULL								
    CONSTRAINT uk_users_user_name UNIQUE,								
  password VARCHAR2(60) NOT NULL,								
  user_image BLOB,								
  user_info  VARCHAR2(1000),								
  number_of_questions NUMBER(10) DEFAULT 0 NOT NULL								
    CONSTRAINT ck_users_questions_nonneg CHECK (number_of_questions >= 0),								
  number_of_answers   NUMBER(10) DEFAULT 0 NOT NULL								
    CONSTRAINT ck_users_answers_nonneg CHECK (number_of_answers >= 0),								
  full_name VARCHAR2(60),								
  skill     VARCHAR2(250),								
  school    VARCHAR2(100)								
);								
								
								

-- QA�̃W��������o�^����e�[�u��						
CREATE TABLE subjects (								
  subject_id NUMBER(10) GENERATED ALWAYS AS IDENTITY								
    CONSTRAINT pk_subjects PRIMARY KEY,								
								
  subject_name VARCHAR2(50) NOT NULL								
    CONSTRAINT uk_subjects_name UNIQUE								
);								
								
								
-- SNS�̃|�X�g��ۑ�����e�[�u��										
CREATE TABLE sns_posts (								
  post_id NUMBER(10) GENERATED ALWAYS AS IDENTITY								
    CONSTRAINT pk_sns_posts PRIMARY KEY,								
  user_id NUMBER(10) NOT NULL,								
  media   BLOB,								
  p_body  VARCHAR2(3000)  NOT NULL								
  CONSTRAINT ck_sns_posts_body_not_blank  CHECK (LENGTH(TRIM(p_body)) > 0),								
  p_created_at TIMESTAMP DEFAULT SYSTIMESTAMP NOT NULL,								
  CONSTRAINT fk_sns_posts_user								
    FOREIGN KEY (user_id) REFERENCES users(user_id),								
  CONSTRAINT ck_sns_posts_content								
    CHECK (p_body IS NOT NULL OR media IS NOT NULL)								
);								
								
								
-- SNS�̃R�����g��ۑ�����e�[�u��													
CREATE TABLE sns_comments (								
  comment_id NUMBER(10) GENERATED ALWAYS AS IDENTITY								
    CONSTRAINT pk_sns_comments PRIMARY KEY,								
  post_id NUMBER(10) NOT NULL,								
  user_id NUMBER(10) NOT NULL,								
  c_body  VARCHAR2(3000) NOT NULL,								
  c_created_at TIMESTAMP DEFAULT SYSTIMESTAMP NOT NULL,								
  CONSTRAINT fk_sns_comments_post								
    FOREIGN KEY (post_id) REFERENCES  sns_posts(post_id) ON DELETE CASCADE,								
  CONSTRAINT fk_sns_comments_user								
    FOREIGN KEY (user_id) REFERENCES users(user_id),								
  CONSTRAINT ck_sns_comments_body_not_blank								
    CHECK (LENGTH(TRIM(c_body)) > 0)								
);								
								
								
-- �����ۑ�����e�[�u��				
CREATE TABLE qa_questions (								
  question_id NUMBER(10) GENERATED ALWAYS AS IDENTITY								
    CONSTRAINT pk_qa_questions PRIMARY KEY,								
  user_id    NUMBER(10) NOT NULL,								
  subject_id NUMBER(10) NOT NULL,								
  title  VARCHAR2(250) NOT NULL,								
  media  BLOB,								
  q_body VARCHAR2(3000) NOT NULL,								
  q_created_at  TIMESTAMP DEFAULT SYSTIMESTAMP NOT NULL,								
  CONSTRAINT fk_qa_questions_user								
    FOREIGN KEY (user_id) REFERENCES users(user_id),								
  CONSTRAINT fk_qa_questions_subject								
    FOREIGN KEY (subject_id) REFERENCES subjects(subject_id),								
  CONSTRAINT ck_qa_questions_body_not_blank								
    CHECK (LENGTH(TRIM(q_body)) > 0)								
);								
								
								
-- �񓚂�ۑ�����e�[�u��											
CREATE TABLE qa_answers (								
  answer_id NUMBER(10) GENERATED ALWAYS AS IDENTITY								
    CONSTRAINT pk_qa_answers PRIMARY KEY,								
  question_id NUMBER(10) NOT NULL,								
  user_id     NUMBER(10) NOT NULL,								
  a_body VARCHAR2(3500) NOT NULL,								
  a_created_at TIMESTAMP DEFAULT SYSTIMESTAMP NOT NULL,								
  CONSTRAINT fk_qa_answers_question								
    FOREIGN KEY (question_id) REFERENCES qa_questions(question_id) ON DELETE CASCADE,								
  CONSTRAINT fk_qa_answers_user								
    FOREIGN KEY (user_id) REFERENCES users(user_id),								
  CONSTRAINT ck_qa_answers_body_not_blank								
    CHECK (LENGTH(TRIM(a_body)) > 0)								
);								

-- �W�������̃f�[�^
insert into subjects (subject_name)
values('java');
insert into subjects (subject_name)
values('oracle');
insert into subjects (subject_name)
values('c');
insert into subjects (subject_name)
values('c#');
insert into subjects (subject_name)
values('html');
insert into subjects (subject_name)
values('css');
insert into subjects (subject_name)
values('javascript');