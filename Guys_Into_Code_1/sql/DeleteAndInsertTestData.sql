-- ============================================================
-- 1) Delete ALL existing data (keep SUBJECTS for QA categories)
--    Run this in SQL Developer connected to your database (info/pro)
-- ============================================================

DELETE FROM qa_answers;
DELETE FROM qa_questions;
DELETE FROM sns_comments;
DELETE FROM sns_posts;
DELETE FROM users;

COMMIT;

-- ============================================================
-- 2) Insert 10 test users (Japanese names & info)
--    Passwords: all use "test1234" for easy login
--    Profile images: load later with LoadTestImages (img/profile/1..10)
-- ============================================================

INSERT INTO users (user_name, password, user_info, full_name, skill, school)
VALUES ('山田 太郎', 'test1234', 'プログラミングが好きなエンジニアです。', '山田 太郎', 'Java, Oracle', '東京工業大学');

INSERT INTO users (user_name, password, user_info, full_name, skill, school)
VALUES ('佐藤 花子', 'test1234', 'Web開発を勉強中です。', '佐藤 花子', 'HTML, CSS, JavaScript', '早稲田大学');

INSERT INTO users (user_name, password, user_info, full_name, skill, school)
VALUES ('鈴木 一郎', 'test1234', 'バックエンドが得意です。', '鈴木 一郎', 'Java, Oracle, C', '京都大学');

INSERT INTO users (user_name, password, user_info, full_name, skill, school)
VALUES ('田中美咲', 'test1234', 'フロントエンドを中心に学習しています。', '田中美咲', 'HTML, CSS, JavaScript', '大阪大学');

INSERT INTO users (user_name, password, user_info, full_name, skill, school)
VALUES ('高橋健太', 'test1234', 'データベースとJavaを学んでいます。', '高橋健太', 'Java, Oracle', '名古屋大学');

INSERT INTO users (user_name, password, user_info, full_name, skill, school)
VALUES ('伊藤さくら', 'test1234', 'C#でゲーム開発に興味あり。', '伊藤さくら', 'C#, C', '九州大学');

INSERT INTO users (user_name, password, user_info, full_name, skill, school)
VALUES ('渡辺大輔', 'test1234', '初心者ですが頑張ります。', '渡辺大輔', 'Java, HTML', '北海道大学');

INSERT INTO users (user_name, password, user_info, full_name, skill, school)
VALUES ('中村優子', 'test1234', 'Oracle資格取得を目指しています。', '中村優子', 'Oracle, SQL', '神戸大学');

INSERT INTO users (user_name, password, user_info, full_name, skill, school)
VALUES ('小林翔太', 'test1234', 'チーム開発の経験を増やしたいです。', '小林翔太', 'Java, JavaScript', '筑波大学');

INSERT INTO users (user_name, password, user_info, full_name, skill, school)
VALUES ('加藤あゆみ', 'test1234', '趣味でプログラミングをしています。', '加藤あゆみ', 'HTML, CSS, JavaScript', '横浜国立大学');

COMMIT;

-- ============================================================
-- 3) Insert 10 SNS posts (Japanese). Images: img/sns/1..10
-- ============================================================

INSERT INTO sns_posts (user_id, p_body, media, p_created_at)
SELECT u.user_id, '今日はJavaの勉強をしました。オブジェクト指向が少しずつ分かってきました！', NULL,
       SYSTIMESTAMP - INTERVAL '9' DAY - INTERVAL '12' HOUR
FROM users u WHERE u.user_name = '山田 太郎';

INSERT INTO sns_posts (user_id, p_body, media, p_created_at)
SELECT u.user_id, 'OracleのPL/SQLでストアドプロシージャを書いてみた。デバッグが大変だった。', NULL,
       SYSTIMESTAMP - INTERVAL '8' DAY - INTERVAL '3' HOUR - INTERVAL '20' MINUTE
FROM users u WHERE u.user_name = '佐藤 花子';

INSERT INTO sns_posts (user_id, p_body, media, p_created_at)
SELECT u.user_id, 'HTMLとCSSでレスポンシブデザインに挑戦中。メディアクエリが便利。', NULL,
       SYSTIMESTAMP - INTERVAL '7' DAY - INTERVAL '18' HOUR
FROM users u WHERE u.user_name = '鈴木 一郎';

INSERT INTO sns_posts (user_id, p_body, media, p_created_at)
SELECT u.user_id, 'C言語のポインタ、やっと理解できた気がする。次は構造体だ。', NULL,
       SYSTIMESTAMP - INTERVAL '6' DAY - INTERVAL '6' HOUR - INTERVAL '5' MINUTE
FROM users u WHERE u.user_name = '田中美咲';

INSERT INTO sns_posts (user_id, p_body, media, p_created_at)
SELECT u.user_id, 'C#で簡単なコンソールゲームを作った。楽しい！', NULL,
       SYSTIMESTAMP - INTERVAL '5' DAY - INTERVAL '11' HOUR
FROM users u WHERE u.user_name = '高橋健太';

INSERT INTO sns_posts (user_id, p_body, media, p_created_at)
SELECT u.user_id, 'JavaScriptの非同期処理、async/await が便利すぎる。', NULL,
       SYSTIMESTAMP - INTERVAL '4' DAY - INTERVAL '2' HOUR - INTERVAL '45' MINUTE
FROM users u WHERE u.user_name = '伊藤さくら';

INSERT INTO sns_posts (user_id, p_body, media, p_created_at)
SELECT u.user_id, 'データベースの正規化について復習した。第三正規形まで理解できた。', NULL,
       SYSTIMESTAMP - INTERVAL '3' DAY - INTERVAL '9' HOUR - INTERVAL '10' MINUTE
FROM users u WHERE u.user_name = '渡辺大輔';

INSERT INTO sns_posts (user_id, p_body, media, p_created_at)
SELECT u.user_id, 'Gitのブランチ戦略、Git Flowを試してみている。', NULL,
       SYSTIMESTAMP - INTERVAL '2' DAY - INTERVAL '19' HOUR
FROM users u WHERE u.user_name = '中村優子';

INSERT INTO sns_posts (user_id, p_body, media, p_created_at)
SELECT u.user_id, 'チームで初めてのコードレビュー。指摘をもらって成長できた。', NULL,
       SYSTIMESTAMP - INTERVAL '1' DAY - INTERVAL '7' HOUR - INTERVAL '30' MINUTE
FROM users u WHERE u.user_name = '小林翔太';

INSERT INTO sns_posts (user_id, p_body, media, p_created_at)
SELECT u.user_id, '週末は趣味のプログラミング。小さなWebアプリを公開した。', NULL,
       SYSTIMESTAMP - INTERVAL '6' HOUR - INTERVAL '12' MINUTE
FROM users u WHERE u.user_name = '加藤あゆみ';

COMMIT;

-- ============================================================
-- 4) Insert 12 QA questions (Japanese). Images: img/qa/1..12
--    subject_id 1=java, 2=oracle, 3=c, 4=c#, 5=html, 6=css, 7=javascript
-- ============================================================

INSERT INTO qa_questions (user_id, subject_id, title, q_body, media, q_created_at)
SELECT u.user_id, 1, 'JavaでNullPointerExceptionが発生する原因を教えてください', 'オブジェクトを扱う際にNullPointerExceptionが出てしまいます。nullチェックのベストプラクティスはありますか？', NULL,
       SYSTIMESTAMP - INTERVAL '10' DAY - INTERVAL '1' HOUR
FROM users u WHERE u.user_name = '山田 太郎';

INSERT INTO qa_questions (user_id, subject_id, title, q_body, media, q_created_at)
SELECT u.user_id, 2, 'Oracleで日付の比較方法について', 'WHERE句で日付範囲を指定したいのですが、SYSDATEとの比較方法を教えてください。', NULL,
       SYSTIMESTAMP - INTERVAL '9' DAY - INTERVAL '20' MINUTE
FROM users u WHERE u.user_name = '佐藤 花子';

INSERT INTO qa_questions (user_id, subject_id, title, q_body, media, q_created_at)
SELECT u.user_id, 3, 'C言語のメモリリークについて', 'mallocで確保した領域をfreeし忘れた場合、プログラム終了後はどうなりますか？', NULL,
       SYSTIMESTAMP - INTERVAL '9' DAY - INTERVAL '6' HOUR
FROM users u WHERE u.user_name = '鈴木 一郎';

INSERT INTO qa_questions (user_id, subject_id, title, q_body, media, q_created_at)
SELECT u.user_id, 5, 'HTMLのセマンティックタグの使い分け', 'header, main, article, section の使い分けの基準を教えてください。', NULL,
       SYSTIMESTAMP - INTERVAL '8' DAY - INTERVAL '14' HOUR - INTERVAL '10' MINUTE
FROM users u WHERE u.user_name = '田中美咲';

INSERT INTO qa_questions (user_id, subject_id, title, q_body, media, q_created_at)
SELECT u.user_id, 6, 'CSSで要素を中央揃えにする方法', 'flexboxとgrid以外で、divを画面中央に配置する方法はありますか？', NULL,
       SYSTIMESTAMP - INTERVAL '7' DAY - INTERVAL '1' HOUR - INTERVAL '55' MINUTE
FROM users u WHERE u.user_name = '高橋健太';

INSERT INTO qa_questions (user_id, subject_id, title, q_body, media, q_created_at)
SELECT u.user_id, 7, 'JavaScriptのクロージャについて', 'クロージャの仕組みと、実際にどんな場面で使うと便利か教えてください。', NULL,
       SYSTIMESTAMP - INTERVAL '6' DAY - INTERVAL '9' HOUR
FROM users u WHERE u.user_name = '伊藤さくら';

INSERT INTO qa_questions (user_id, subject_id, title, q_body, media, q_created_at)
SELECT u.user_id, 1, 'JavaのArrayListとLinkedListの違い', 'どちらもListですが、パフォーマンスの違いと使い分けを教えてください。', NULL,
       SYSTIMESTAMP - INTERVAL '5' DAY - INTERVAL '15' MINUTE
FROM users u WHERE u.user_name = '山田 太郎';

INSERT INTO qa_questions (user_id, subject_id, title, q_body, media, q_created_at)
SELECT u.user_id, 4, 'C#のasync/awaitの基本的な使い方', '非同期メソッドを呼び出すとき、awaitとTask.Runの違いは何ですか？', NULL,
       SYSTIMESTAMP - INTERVAL '4' DAY - INTERVAL '22' HOUR - INTERVAL '30' MINUTE
FROM users u WHERE u.user_name = '鈴木 一郎';

INSERT INTO qa_questions (user_id, subject_id, title, q_body, media, q_created_at)
SELECT u.user_id, 2, 'Oracleのトランザクションの分離レベル', 'READ COMMITTEDとSERIALIZABLEの違いを教えてください。', NULL,
       SYSTIMESTAMP - INTERVAL '4' DAY - INTERVAL '6' HOUR
FROM users u WHERE u.user_name = '中村優子';

INSERT INTO qa_questions (user_id, subject_id, title, q_body, media, q_created_at)
SELECT u.user_id, 5, 'HTMLのフォーム送信でGETとPOSTの違い', 'フォームのmethodでGETとPOSTはどのように使い分ければよいですか？', NULL,
       SYSTIMESTAMP - INTERVAL '3' DAY - INTERVAL '13' HOUR - INTERVAL '5' MINUTE
FROM users u WHERE u.user_name = '田中美咲';

INSERT INTO qa_questions (user_id, subject_id, title, q_body, media, q_created_at)
SELECT u.user_id, 7, 'JavaScriptでJSONをパースする方法', 'JSON.parseとevalの違い、および安全にパースする方法を教えてください。', NULL,
       SYSTIMESTAMP - INTERVAL '2' DAY - INTERVAL '8' HOUR - INTERVAL '40' MINUTE
FROM users u WHERE u.user_name = '小林翔太';

INSERT INTO qa_questions (user_id, subject_id, title, q_body, media, q_created_at)
SELECT u.user_id, 3, 'C言語の構造体のポインタ渡し', '関数に構造体を渡すとき、値渡しとポインタ渡しではどちらが一般的ですか？', NULL,
       SYSTIMESTAMP - INTERVAL '1' DAY - INTERVAL '1' HOUR - INTERVAL '10' MINUTE
FROM users u WHERE u.user_name = '伊藤さくら';

COMMIT;

-- ============================================================
-- 5) Insert SNS comments (Japanese)
-- ============================================================

INSERT INTO sns_comments (post_id, user_id, c_body, c_created_at)
SELECT (SELECT MIN(post_id) FROM sns_posts), u.user_id, 'いいね！私もJava勉強中です。',
       SYSTIMESTAMP - INTERVAL '8' DAY - INTERVAL '2' HOUR
FROM users u WHERE u.user_name = '佐藤 花子';

INSERT INTO sns_comments (post_id, user_id, c_body, c_created_at)
SELECT (SELECT MIN(post_id) FROM sns_posts), u.user_id, '参考になりました。ありがとうございます。',
       SYSTIMESTAMP - INTERVAL '7' DAY - INTERVAL '22' HOUR - INTERVAL '10' MINUTE
FROM users u WHERE u.user_name = '高橋健太';

INSERT INTO sns_comments (post_id, user_id, c_body, c_created_at)
SELECT (SELECT post_id FROM (SELECT post_id, ROW_NUMBER() OVER (ORDER BY post_id) rn FROM sns_posts) WHERE rn = 2), u.user_id, '私も同じところで詰まりました。',
       SYSTIMESTAMP - INTERVAL '6' DAY - INTERVAL '1' HOUR - INTERVAL '35' MINUTE
FROM users u WHERE u.user_name = '渡辺大輔';

COMMIT;

-- ============================================================
-- 6) Insert QA answers (Japanese) - link to first few questions
-- ============================================================

INSERT INTO qa_answers (question_id, user_id, a_body, a_created_at)
SELECT q.question_id, u.user_id, 'nullの可能性があるオブジェクトを使う前に、if (obj != null) でチェックするか、Optionalを使うと安全です。Java 8以降ならOptionalがおすすめです。',
       SYSTIMESTAMP - INTERVAL '9' DAY - INTERVAL '10' HOUR
FROM qa_questions q, users u WHERE u.user_name = '佐藤 花子' AND q.title LIKE '%NullPointerException%' AND ROWNUM = 1;

INSERT INTO qa_answers (question_id, user_id, a_body, a_created_at)
SELECT q.question_id, u.user_id, 'WHERE created_at >= TRUNC(SYSDATE) のようにTRUNCで時刻を切り捨てて比較するか、TO_DATEで文字列を日付に変換して比較できます。',
       SYSTIMESTAMP - INTERVAL '8' DAY - INTERVAL '1' HOUR - INTERVAL '5' MINUTE
FROM qa_questions q, users u WHERE u.user_name = '高橋健太' AND q.title LIKE '%日付%' AND ROWNUM = 1;

INSERT INTO qa_answers (question_id, user_id, a_body, a_created_at)
SELECT q.question_id, u.user_id, 'プログラム終了後はOSがメモリを回収しますが、長時間動かすプログラムではリークが蓄積するので、必ずfreeで解放しましょう。',
       SYSTIMESTAMP - INTERVAL '7' DAY - INTERVAL '17' HOUR - INTERVAL '40' MINUTE
FROM qa_questions q, users u WHERE u.user_name = '田中美咲' AND q.title LIKE '%メモリリーク%' AND ROWNUM = 1;

INSERT INTO qa_answers (question_id, user_id, a_body, a_created_at)
SELECT q.question_id, u.user_id, 'margin: 0 auto; と width を指定する方法や、position: absolute と transform の組み合わせでも中央揃えできます。',
       SYSTIMESTAMP - INTERVAL '6' DAY - INTERVAL '5' HOUR
FROM qa_questions q, users u WHERE u.user_name = '加藤あゆみ' AND q.title LIKE '%中央揃え%' AND ROWNUM = 1;

INSERT INTO qa_answers (question_id, user_id, a_body, a_created_at)
SELECT q.question_id, u.user_id, 'クロージャは関数が定義時のスコープを「覚えておく」仕組みです。プライベート変数のような使い方や、コールバックでよく使います。',
       SYSTIMESTAMP - INTERVAL '5' DAY - INTERVAL '3' HOUR - INTERVAL '25' MINUTE
FROM qa_questions q, users u WHERE u.user_name = '小林翔太' AND q.title LIKE '%クロージャ%' AND ROWNUM = 1;

COMMIT;

-- Done. Next: run LoadTestImages to load profile/qa/sns images from img folder.