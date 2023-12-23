package Test;
import java.io.*;
import java.util.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Pattern;


public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        List<User> arrUser = new ArrayList<>();
        arrUser.add(new User("abcd@gmail.com", "12234253", "Abcd"));
        arrUser.add(new User("adfasdf@gmail.com", "1234123", "Adfdavdsfadf"));
        arrUser.add(new User("dafsdscasd@gmail.com", "5869734", "Dasvadsvfd"));

        convertObjectToJsonFile("list-user.json", arrUser);

        int options=0;
        boolean isQuit = false;
        while (!isQuit) {
            System.out.println("Enter 1: Đăng nhập");
            System.out.println("Enter 2: Đăng ký");
            System.out.println("Enter 3: Quên mật khẩu");
            System.out.println("Nhập lựa 1 trong các lựa trọn");
            options = sc.nextInt();
            sc.nextLine();
            switch (options) {
                case 1: {
                    System.out.println("Nhập email");
                    String email = sc.nextLine();
                    System.out.println("Nhập mật password");
                    String password = sc.nextLine();
                    try {
                        File file=new File("list-user.json");
                        InputStream is=new FileInputStream(file);
                        ObjectInputStream ois=new ObjectInputStream(is);

                        User userData=(User) ois.readObject();
                        ArrayList<String> data=new ArrayList<>();
                        data.add(userData.getEmail());
                        data.add(userData.getPassword());
                        for (int i=0;i<data.size();i++){
                            if (data.get(i).equals(email)&&data.get(i).equals(password)){
                                while(true){
                                    System.out.println("Chào mừng "+userData.getUsername()+ ", bạn có thể thực hiện các công việc sau:");
                                    System.out.println("Enter a: Thay đổi username");
                                    System.out.println("Enter b: Thay đổi email");
                                    System.out.println("Enter c: Thay đổi mật khẩu");
                                    System.out.println("Enter d: Đăng xuất");
                                    System.out.println("Enter e: Thoát chương trình");
                                    String function=sc.nextLine();
                                    switch (function){
                                        case "a":{
                                            System.out.println("Nhập username mới");
                                            String newUsername=sc.nextLine();
                                            System.out.println("Đã thay đổi usename thành: "+newUsername);
                                            convertObjectToJsonFile("list-user.json", newUsername);
                                            break;
                                        }
                                        case "b":{
                                            System.out.println("Nhập email mới");
                                            String changedEmail=sc.nextLine();
                                            for (int j=0;j<arrUser.size();j++){
                                                if (arrUser.get(j).getEmail()!=changedEmail){
                                                    System.out.println("Đã thay đổi email thành: "+changedEmail);
                                                    convertObjectToJsonFile("list-user.json", changedEmail);
                                                }else {
                                                    System.out.println("Đổi email không thành công");
                                                }
                                            }
                                            break;
                                        }
                                        case"c":{
                                            System.out.println("Nhập password mới");
                                            String changedPassword=sc.nextLine();
                                            if (changedPassword.length()>=7&&changedPassword.length()<=15){
                                                System.out.println("Đã thay đổi email thành: "+changedPassword);
                                                convertObjectToJsonFile("list-user.json", changedPassword);
                                            }else {
                                                System.out.println("password không hợp lệ mời nhập lại");
                                            }
                                            break;
                                        }
                                        case"d":{
                                            isQuit = false;
                                            break;
                                        }
                                        case"e":{
                                            isQuit = true;
                                            break;
                                        }
                                        default:{
                                            System.out.println("Lựa chọn không hợp lệ");
                                            break;
                                        }
                                    }
                                }
                            }else {
                                System.out.println("Tài khoản hoặc mật khẩu không chính xác");
                            }
                        }
                        ois.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case 2: {
                    ArrayList<User> newUser = new ArrayList<>();
                    for (int i = 0; i < 1000; i++) {
                        System.out.println("Thêm email user mới");
                        String setEmail = sc.nextLine();
                        try {
                            File file=new File("list-user.json");
                            InputStream is=new FileInputStream(file);
                            ObjectInputStream ois=new ObjectInputStream(is);

                            User userData=(User) ois.readObject();
                            ArrayList<String> emailData=new ArrayList<>();
                            emailData.add(userData.getEmail());
                            for (int j=0;j<emailData.size();j++){
                                Pattern pattern = Pattern.compile("^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$");
                                if (Pattern.matches(String.valueOf(pattern), setEmail) == true&&setEmail!=emailData.get(j)) {
                                    System.out.println(setEmail);
                                } else {
                                    System.out.println("email không hợp lệ mời nhập lại");
                                }
                            }
                            ois.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        System.out.println("Thêm password user mới");
                        String setPassword = sc.nextLine();
                        if (setPassword.length()>=7&&setPassword.length()<=15){
                            System.out.println(setPassword);
                        }else {
                            System.out.println("password không hợp lệ mời nhập lại");
                        }

                        System.out.println("Thêm username mới");
                        String setUsername = sc.nextLine();
                        sc.nextLine();

                        newUser.add(new User(setEmail, setPassword, setUsername));
                        convertObjectToJsonFile("list-user.json", newUser);
                    }
                    break;
                }
                case 3: {
                    System.out.println("Nhập email đã đăng kí");
                    String createdEmail = sc.nextLine();
                    try {
                        File file=new File("list-user.json");
                        InputStream is=new FileInputStream(file);
                        ObjectInputStream ois=new ObjectInputStream(is);

                        User userData=(User) ois.readObject();
                        ArrayList<String> emailData=new ArrayList<>();
                        emailData.add(userData.getEmail());
                        for (int i=0;i<emailData.size();i++){
                            Pattern pattern = Pattern.compile("^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$");
                            if (Pattern.matches(String.valueOf(pattern), createdEmail) == true&&emailData.get(i).equals(createdEmail)) {
                                System.out.println(createdEmail);
                                int n = (int) (Math.random() * 10);
                                String recoveredPassword="A" + n + "bd" + n;
                                System.out.println("password mới của bạn là: "+recoveredPassword);
                                convertObjectToJsonFile("list-user.json", recoveredPassword);
                            } else {
                                System.out.println("email không hợp lệ mời nhập lại");
                            }
                        }
                        ois.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                default: {
                    System.out.println("Lựa chọn không hợp lệ");
                    break;
                }
            }
        }
    }

    public static void checkEmail(String fileName, Object obj) {
        try {
            File file=new File("list-user.json");
            InputStream is=new FileInputStream(file);
            ObjectInputStream ois=new ObjectInputStream(is);

            User emailUser=(User) ois.readObject();
            System.out.println(emailUser.getEmail());
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
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