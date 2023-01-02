package kakao.sollyj.streamex;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializableMain {
    public static void main(String[] args) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("sample.dat"))) {
            Data data = new Data(1, "adam", "군계");
            oos.writeObject(data);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("sample.dat"))) {
            // 기록된 인스턴스 읽어오기
            Data data = (Data)ois.readObject();
            System.out.println(data);
        } catch(Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
