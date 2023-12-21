package Test;
import java.io.IOException;
import java.util.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.Writer;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Pattern;
import com.google.gson.annotations.SerializedName;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<User> arrUser = new ArrayList<>();
        arrUser.add(new User("abcd@gmail.com", "12234253", "Abcd"));
        arrUser.add(new User("adfasdf@gmail.com", "1234123", "Adfdavdsfadf"));
        arrUser.add(new User("dafsdscasd@gmail.com", "5869734", "Dasvadsvfd"));

        convertObjectToJsonFile("list-user.json", arrUser);

        while (true) {
            System.out.println("Nhập lựa 1 trong các lựa trọn");
            System.out.println("Enter 1: Đăng nhập");
            System.out.println("Enter 2: Đăng ký");
            System.out.println("Enter 3: Quên mật khẩu");
            String options = sc.nextLine();
            switch (options) {
                case "1": {
                    System.out.println("Nhập email");
                    String email = sc.nextLine();
                    System.out.println("Nhập mật password");
                    String password = sc.nextLine();
                    if (email == @SerializedName("Email"));
                }
                case "2": {
                    System.out.println("Nhập email");
                    String newEmail = sc.nextLine();
                    Pattern pattern = Pattern.compile("^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$");
                    if (Pattern.matches(String.valueOf(pattern), newEmail) == true && newEmail !=getListObjectFromJsonFile(email)) {
                        System.out.println(newEmail);
                    } else {
                        System.out.println("email không hợp lệ mời nhập lại");
                    }
                    ArrayList<User> newUser = new ArrayList<>();
                    for (int i = 0; i < 1000; i++) {
                        System.out.println("Thêm email user mới");
                        String setEmail = sc.nextLine();
                        System.out.println("Thêm password user mới");
                        String setPassword = sc.nextLine();
                        System.out.println("Thêm username mới");
                        String setUsername = sc.nextLine();

                        sc.nextLine();
                        newUser.add(new User(setEmail, setPassword, setUsername));
                        convertObjectToJsonFile("list-user.json", newUser);
                    }
                }
                case "3": {
                    System.out.println("Nhập email đã đăng kí");
                    String createdEmail = sc.nextLine();
                    Pattern pattern = Pattern.compile("^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$");
                    if (Pattern.matches(String.valueOf(pattern), createdEmail) == true) {
                        System.out.println(createdEmail);
                        int n = (int) (Math.random() * 10);
                        System.out.println("password mới của bạn là: " + "A" + n + "bd" + n);
                    } else {
                        System.out.println("email không hợp lệ mời nhập lại");
                    }
                }
            }
        }
    }

    public static void convertObjectToJsonFile(String fileName, Object obj) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            Writer writer = Files.newBufferedWriter(Paths.get(fileName));

            gson.toJson(obj, writer);

            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}