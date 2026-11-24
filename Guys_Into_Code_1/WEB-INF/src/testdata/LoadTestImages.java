package testdata;

import db.DBAccess;

import java.io.*;
import java.nio.file.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Loads image files from img/profile (10), img/qa (12), img/sns (10)
 * into the database BLOBs for users.user_image, qa_questions.media, sns_posts.media.
 * Run after executing sql/DeleteAndInsertTestData.sql
 *
 * Usage: run from project root (Guys_Into_Code) with classpath including WEB-INF/classes and ojdbc jar.
 *   java -cp "WEB-INF/classes;path/to/ojdbc11.jar" testdata.LoadTestImages
 */
public class LoadTestImages {

    private static final int PROFILE_COUNT = 10;
    private static final int QA_COUNT = 12;
    private static final int SNS_COUNT = 10;

    private static Path baseDir;

    public static void main(String[] args) {
        baseDir = findImgBase();
        if (baseDir == null) {
            System.err.println("ERROR: img folder not found. Run from project root (Guys_Into_Code) or set -Dimg.base=path/to/Guys_Into_Code");
            System.exit(1);
        }
        System.out.println("Using img base: " + baseDir.toAbsolutePath());

        Connection conn = null;
        try {
            DBAccess db = new DBAccess();
            conn = db.accessOracle();

            loadProfileImages(conn);
            loadQAImages(conn);
            loadSNSImages(conn);

            System.out.println("Done. All test images loaded.");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    /** Returns the project root directory that contains the "img" folder. */
    private static Path findImgBase() {
        String prop = System.getProperty("img.base");
        if (prop != null && !prop.isEmpty()) {
            Path root = Paths.get(prop);
            if (Files.exists(root.resolve("img").resolve("profile"))) return root;
            return root;
        }
        Path cwd = Paths.get(System.getProperty("user.dir"));
        if (Files.isDirectory(cwd.resolve("img").resolve("profile")))
            return cwd;
        if (Files.isDirectory(cwd.resolve("img")) && (Files.exists(cwd.resolve("img").resolve("profile")) || Files.exists(cwd.resolve("img").resolve("qa"))))
            return cwd;
        Path withName = cwd.resolve("Guys_Into_Code");
        if (Files.isDirectory(withName.resolve("img")))
            return withName;
        return null;
    }

    private static byte[] readImageFile(Path path) throws IOException {
        if (!Files.exists(path)) return null;
        return Files.readAllBytes(path);
    }

    /** Resolve image file path; supports .png and .jpg (checks .png first). */
    private static Path imagePath(String subdir, int index) {
        Path dir = baseDir.resolve("img").resolve(subdir);
        Path png = dir.resolve(index + ".png");
        Path jpg = dir.resolve(index + ".jpg");
        if (Files.exists(png)) return png;
        if (Files.exists(jpg)) return jpg;
        return png; // default for "not found" message (e.g. when using .png)
    }

    /** Load profile images by test user name (testuser1..testuser10) so the right users get images regardless of user_id. */
    private static void loadProfileImages(Connection conn) throws SQLException, IOException {
        try (PreparedStatement ps = conn.prepareStatement("UPDATE users SET user_image = ? WHERE user_name = ?")) {
            List<String> userList = new ArrayList<>();
            userList.add("山田 太郎");
            userList.add("佐藤 花子");
            userList.add("鈴木 一郎");
            userList.add("田中美咲");
            userList.add("高橋健太");
            userList.add("伊藤さくら");
            userList.add("渡辺大輔");
            userList.add("中村優子");
            userList.add("小林翔太");
            userList.add("加藤あゆみ");
            for (int i = 0; i < PROFILE_COUNT; i++) {
                String userName = userList.get(i);
                Path f = imagePath("profile", i + 1);
                byte[] bytes = readImageFile(f);
                if (bytes == null || bytes.length == 0) {
                    System.out.println("Skip profile " + (i + 1) + " (file not found: " + f + ")");
                    continue;
                }
                ps.setBytes(1, bytes);
                ps.setString(2, userName);
                int updated = ps.executeUpdate();
                if (updated > 0) {
                    System.out.println("Loaded profile image " + (i + 1) + " -> " + userName);
                } else {
                    System.out.println("Skip profile " + (i + 1) + " (user " + userName + " not found in DB)");
                }
            }
        }
    }

    private static void loadQAImages(Connection conn) throws SQLException, IOException {
        List<Integer> qIds = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement("SELECT question_id FROM qa_questions ORDER BY question_id");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) qIds.add(rs.getInt(1));
        }
        if (qIds.size() < QA_COUNT) {
            System.err.println("WARN: Only " + qIds.size() + " questions found; need " + QA_COUNT + " for QA images.");
        }
        try (PreparedStatement ps = conn.prepareStatement("UPDATE qa_questions SET media = ? WHERE question_id = ?")) {
            for (int i = 0; i < QA_COUNT && i < qIds.size(); i++) {
                Path f = imagePath("qa", i + 1);
                byte[] bytes = readImageFile(f);
                if (bytes == null || bytes.length == 0) {
                    System.out.println("Skip QA " + (i + 1) + " (file not found: " + f + ")");
                    continue;
                }
                ps.setBytes(1, bytes);
                ps.setInt(2, qIds.get(i));
                ps.executeUpdate();
                System.out.println("Loaded QA image " + (i + 1) + " -> question_id " + qIds.get(i));
            }
        }
    }

    private static void loadSNSImages(Connection conn) throws SQLException, IOException {
        List<Integer> postIds = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement("SELECT post_id FROM sns_posts ORDER BY post_id");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) postIds.add(rs.getInt(1));
        }
        if (postIds.size() < SNS_COUNT) {
            System.err.println("WARN: Only " + postIds.size() + " SNS posts found; need " + SNS_COUNT + " for SNS images.");
        }
        try (PreparedStatement ps = conn.prepareStatement("UPDATE sns_posts SET media = ? WHERE post_id = ?")) {
            for (int i = 0; i < SNS_COUNT && i < postIds.size(); i++) {
                Path f = imagePath("sns", i + 1);
                byte[] bytes = readImageFile(f);
                if (bytes == null || bytes.length == 0) {
                    System.out.println("Skip SNS " + (i + 1) + " (file not found: " + f + ")");
                    continue;
                }
                ps.setBytes(1, bytes);
                ps.setInt(2, postIds.get(i));
                ps.executeUpdate();
                System.out.println("Loaded SNS image " + (i + 1) + " -> post_id " + postIds.get(i));
            }
        }
    }
}
