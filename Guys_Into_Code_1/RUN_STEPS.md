# How to Run Test Data (Japanese + Your Images)

Follow these steps with **SQL Developer** connected to your database (user: **info**, password: **pro**).

---

## Step 1: Put your images in the `img` folder

Place your image files in these folders (use `.jpg` or `.png`):

| Folder        | Files to add        | Used for              |
|---------------|---------------------|------------------------|
| `img/profile/`| `1.jpg` … `10.jpg`  | 10 test user avatars   |
| `img/qa/`     | `1.jpg` … `12.jpg`  | 12 Q&A post images     |
| `img/sns/`    | `1.jpg` … `10.jpg`  | 10 SNS post images     |

So you need: **10 profile images**, **12 Q&A images**, **10 SNS images**.

---

## Step 2: Run the SQL script in SQL Developer

1. Open **SQL Developer** and connect to your database (e.g. user **info** / password **pro**).
2. Open the script:  
   `Guys_Into_Code/sql/DeleteAndInsertTestData.sql`
3. Run the **entire script** (F5 or Execute Script).
   - This will:
     - **Delete** all existing data in: `qa_answers`, `qa_questions`, `sns_comments`, `sns_posts`, `users`
     - **Insert** 10 test users (Japanese names, password `test1234`)
     - **Insert** 10 SNS posts and 12 Q&A questions (all Japanese text)
     - Insert some SNS comments and QA answers (Japanese)
4. Check the **Script Output** tab: you should see "Commit complete" and no errors.

---

## Step 3: Load images into the database (Java loader)

The SQL script does **not** load image files; it only inserts text and leaves image columns empty.  
You must run the Java tool **LoadTestImages** so that your files in `img/` are read and stored in the DB (user profile pics, Q&A media, SNS media).

### Option A: Run from command line

1. Open a terminal and go to the **project root** (where the `img` folder is):
   ```
   cd c:\tomcat10\webapps\Guys_Into_Code
   ```
2. Compile (use your Oracle JDBC path; adjust the path to `ojdbc` if needed):
   ```
   javac -encoding UTF-8 -cp "WEB-INF/classes;WEB-INF/lib/ojdbc11.jar" -d WEB-INF/classes WEB-INF/src/testdata/LoadTestImages.java WEB-INF/src/db/DBAccess.java
   ```
   If your `ojdbc` JAR is elsewhere (e.g. Tomcat lib), use that path instead of `WEB-INF/lib/ojdbc11.jar`.
3. Run the loader:
   ```
   java -cp "WEB-INF/classes;WEB-INF/lib/ojdbc11.jar" testdata.LoadTestImages
   ```
   You should see lines like: `Loaded profile image 1 -> user_id ...`, etc.

### Option B: Run from your IDE

1. Set the **working directory** to: `c:\tomcat10\webapps\Guys_Into_Code`
2. Add to the classpath: `WEB-INF/classes` and your **Oracle JDBC JAR** (e.g. `ojdbc11.jar`).
3. Run the main class: `testdata.LoadTestImages`

If the loader cannot find `img`, set VM option: `-Dimg.base=c:\tomcat10\webapps\Guys_Into_Code`

---

## Step 4: Check that images show on the web

1. Start **Tomcat** (if not already running) and open your app in the browser (e.g. `http://localhost:8080/Guys_Into_Code/`).
2. Log in with any test user, for example:
   - User name: `testuser1`
   - Password: `test1234`
3. Check:
   - **Profile**: Your profile picture (from `img/profile/`) appears on the top bar and on your profile page.
   - **Timeline**: SNS posts show their images (from `img/sns/`); Q&A previews show images (from `img/qa/`).
   - Open a **Q&A post** and an **SNS post**: the post images should display.

If something does not show:

- Confirm the SQL script ran without errors and the loader printed "Loaded ..." for the expected counts.
- Confirm image columns are not null: in SQL Developer run  
  `SELECT user_id, user_name, CASE WHEN user_image IS NULL THEN 'NULL' ELSE 'has image' END FROM users;`
- Check browser dev tools (F12) → Network: requests to `UserImage?user_id=...`, `QAPostImage?qid=...`, `SNSPostImage?post_id=...` should return status 200 and type image.

---

## Summary

| Step | What you do |
|------|----------------|
| 1    | Put 10 profile, 12 QA, 10 SNS images in `img/profile/`, `img/qa/`, `img/sns/` as `1.jpg` … |
| 2    | In SQL Developer: run `sql/DeleteAndInsertTestData.sql` (delete + insert test data) |
| 3    | Run `testdata.LoadTestImages` from project root with DB and `img` available |
| 4    | Open the app in the browser, log in as `testuser1` / `test1234`, and verify all images |

All test data (users, posts, questions, answers, comments) is in **Japanese**. Images are served from the database so they will display correctly as long as Step 2 and Step 3 completed without errors.
