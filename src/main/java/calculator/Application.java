package calculator;
import camp.nextstep.edu.missionutils.Console;

public class Application {
    private static final int[] regexMetaCharset = new int[]{
        36,39,40,41,42,43,46,63,91,92,93,94,123,124,125
    };
    public static int calculate(String input) throws IllegalArgumentException{
        if(input == null){
            return 0;
        }

        StringBuilder regexStr = new StringBuilder("[,:");
        if(input.length() >= 5 && input.matches("^/{2}.\\\\n.*")){
            char customSeparator = input.charAt(2);
            if((customSeparator <= 32 || customSeparator == 127)
                    || (customSeparator >= 48 && customSeparator <= 57)){ // 커스텀 문자가 0~9 사이의 숫자일 경우
                throw new IllegalArgumentException("커스텀 구분자에는 숫자 또는 공백,제어 문자가 올 수 없습니다! : " + customSeparator);
            }
            for(int meta : regexMetaCharset){
                if(customSeparator == meta){
                    regexStr.append("\\");
                    break;
                }
            }
            regexStr.append(customSeparator);
            input =  input.substring(5);
        }
        if(input.isEmpty()){
            return 0;
        }
        regexStr.append("]");

        String[] items = input.split(regexStr.toString());
        if(items.length == 0){
            throw new IllegalArgumentException("피연산자가 존재하지 않습니다!");
        }

        try{
            int sum=0, num;
            for(String item : items){
                num = Integer.parseInt(item);
                if(num <= 0){
                    throw new IllegalArgumentException("피연산자는 양수여야 합니다! : " + num);
                }
                sum += num;
            }
            if(sum < 0){
                throw new IllegalArgumentException("숫자 합이 정수의 최댓값을 초과했습니다! : " + sum);
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

        int result = calculate(input);
        System.out.println("결과 : "+result);

        // 스캐너 닫기
        Console.close();
    }
}
