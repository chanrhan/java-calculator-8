package calculator;
import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        StringAdditionCalculator calculator = new StringAdditionCalculator();
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = Console.readLine();

        int result = calculator.calculate(input);
        System.out.println("결과 : "+result);

        // 스캐너 닫기
        Console.close();
    }
}
