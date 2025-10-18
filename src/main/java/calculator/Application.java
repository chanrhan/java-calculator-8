package calculator;
import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static int calculate(String input) throws IllegalArgumentException{

        return -1;
    }
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        String input = Console.readLine();

        try{
            int result = calculate(input);
            System.out.println(result);
        }catch (IllegalArgumentException e) {
            System.out.println("잘못된 값이 입력되었습니다! : " + e.getMessage());
        }

        // 스캐너 닫기
        Console.close();
    }
}
