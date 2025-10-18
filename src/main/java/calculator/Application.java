package calculator;
import camp.nextstep.edu.missionutils.Console;
import org.assertj.core.internal.Numbers;

public class Application {
    public static int calculate(String input) throws IllegalArgumentException{
        if(input.equals("")){
            return 0;
        }
        String[] items = input.split("[,:]");
        if(items.length == 0){
           throw new IllegalArgumentException("피연산자가 존재하지 않습니다!");
        }

        try{
            int sum=0;
            for(String item : items){
                sum += Integer.parseInt(item);
            }
            return sum;
        }catch (NumberFormatException e) {
            throw new IllegalArgumentException("피연산자가 숫자 형식이 아닙니다!");
        }
    }

    public static void main(String[] args) {
        // TODO: 프로그램 구현
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = Console.readLine();

        try{
            int result = calculate(input);
            System.out.println(result);
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // 스캐너 닫기
        Console.close();
    }
}
